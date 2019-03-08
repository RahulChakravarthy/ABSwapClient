package tech.r7chakra.abswapclient.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.newFixedThreadPoolContext

//val bgPool: CoroutineDispatcher by lazy {
//    val numProcessors = Runtime.getRuntime().availableProcessors()
//    when {
//        numProcessors <= 2 -> newFixedThreadPoolContext(2, "background")
//        else ->
//    }
//}
