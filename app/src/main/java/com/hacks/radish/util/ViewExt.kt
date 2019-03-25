package com.hacks.radish.util

import android.view.View
import android.view.ViewPropertyAnimator

fun View.fadeIn(duration: Long = 200L): ViewPropertyAnimator {
    this.visibility = View.VISIBLE
    this.alpha = 0f

    val animator = this.animate()
    animator.withEndAction(null)
    animator.duration = duration
    animator.alpha(1f)
    animator.start()

    return animator
}

fun View.fadeOut(duration: Long = 200L): ViewPropertyAnimator {
    this.visibility = View.VISIBLE

    val animator = this.animate()
    animator.duration = duration
    animator.alpha(0f)
    animator.start()

    animator.withEndAction {
        this@fadeOut.visibility = View.GONE
    }

    return animator
}