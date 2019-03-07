package tech.r7chakra.abswapclient.util

import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class BlockedSingleThreadExecutor @JvmOverloads constructor(
    // The threadPoolExecutor is used to handle the single background thread in use
    private val threadPoolExecutor: Executor = Executors.newFixedThreadPool(1),
    private val isBlocked: () -> Boolean
) : Executor {
    private val tasks = ArrayDeque<Runnable>()
    private var active: Runnable? = null

    /**
     * Adds {@param runnable} to the queue of tasks to execute.
     * This will execute after all current tasks have finished executing and if the execution is
     * not blocked by {@link isBlocked}
     */
    override fun execute(runnable: Runnable?) {
        if (runnable == null) throw IllegalStateException("Runnable must not be null")

        tasks.add(Runnable {
            try {
                runnable.run()
            } finally {
                scheduleNext()
            }
        })

        // If no task is going on right now, starts a new one
        if (active == null) {
            scheduleNext()
        }
    }

    /**
     * Starts the sequential execution of tasks again
     * The execution could have reached a pause if isBlocked returned true
     */
    fun finishPendingTasks() = scheduleNext()

    /**
     * Skips the next n jobs
     * NOTE: Only use if certain about what the next jobs are
     *
     * @param n number of jobs to skip
     */
    fun skipJobs(n: Int) {
        for (i in 1..n) {
            tasks.poll()
        }
    }

    private fun scheduleNext() {
        if (isBlocked()) return

        active = tasks.poll()
        if (active != null) {
            threadPoolExecutor.execute(active)
        }
    }
}