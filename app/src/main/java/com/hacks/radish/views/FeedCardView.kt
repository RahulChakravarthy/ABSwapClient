package com.hacks.radish.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import com.hacks.radish.R
import com.hacks.radish.views.SplitImageView.Companion.CUT_LEFT
import com.hacks.radish.views.SplitImageView.Companion.CUT_RIGHT
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_feed_card.view.*

class FeedCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var cutPercentage: Int = 50
        set(value) {
            field = value
            onWeightChanged()
        }
        get() {
            return Math.min(100, field)
        }

    private var imageCutAnimator: ValueAnimator? = null

    init {
        View.inflate(context, R.layout.view_feed_card, this)

        image_right.cutSide = CUT_LEFT
        image_left.cutSide = CUT_RIGHT
    }

    fun animateCutTo(newImageAWeight: Int) {
        imageCutAnimator?.cancel()
        val parsedWeight = Math.max(Math.min(newImageAWeight, 75), 25)
        imageCutAnimator = ValueAnimator.ofInt(cutPercentage, parsedWeight)
        imageCutAnimator?.let {
            it.interpolator = AccelerateDecelerateInterpolator()
            it.addUpdateListener { animation ->
                cutPercentage = animation.animatedValue as Int
            }
            it.duration = Math.max(Math.abs(cutPercentage - parsedWeight) * 20L, 400L)
            it.start()
        }
    }

    fun setImageAUrl(url: String) {
        Picasso.get().load(url).into(image_left)
    }

    fun setImageBUrl(url: String) {
        Picasso.get().load(url).into(image_right)
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setCreator(text: String) {
        creator.text = text
    }

    override fun setOnClickListener(l: OnClickListener?) {
        constraint_root.setOnClickListener(l)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        onWeightChanged()
    }

    private fun onWeightChanged() {
        image_left.cutPercentage = cutPercentage
        image_right.cutPercentage = cutPercentage
    }
}