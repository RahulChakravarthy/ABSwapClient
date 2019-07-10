package com.hacks.radish.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.hacks.radish.R
import com.hacks.radish.repo.dataobject.ImageDO
import com.hacks.radish.repo.dataobject.RenderModelDO
import kotlinx.android.synthetic.main.tag.view.*
import kotlinx.android.synthetic.main.view_feed_card.view.*
import com.hacks.radish.util.fadeIn
import com.hacks.radish.util.fadeOut
import com.hacks.radish.views.CutView.Companion.CUT_LEFT
import com.hacks.radish.views.CutView.Companion.CUT_RIGHT

class FeedCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), View.OnClickListener {

    companion object {
        enum class State {
            DEFAULT,
            SHOW_PERCENTAGE,
            TAP_LEFT_IMAGE,
            TAP_RIGHT_IMAGE,
        }
    }

    var model: RenderModelDO = RenderModelDO(
        id = "",
        creator = "",
        tags = listOf(),
        imageA = ImageDO("", 0),
        imageB = ImageDO("", 0))

    var state: State = Companion.State.DEFAULT

    private var cutPercentage: Int = 50
        set(value) {
            field = value
            onWeightChanged()
        }
        get() {
            return Math.min(100, field)
        }

    private var cutLocationAnimator: ValueAnimator? = null
    private var cutWidthAnimator: ValueAnimator? = null

    init {
        View.inflate(context, R.layout.view_feed_card, this)

        cut_right.cutSide = CUT_LEFT
        cut_left.cutSide = CUT_RIGHT
    }

    override fun onClick(view: View) {
        when(state) {
            Companion.State.DEFAULT -> {
                setState(Companion.State.SHOW_PERCENTAGE)
            }
            Companion.State.SHOW_PERCENTAGE -> {
                setState(Companion.State.DEFAULT)
            }
            Companion.State.TAP_LEFT_IMAGE -> TODO()
            Companion.State.TAP_RIGHT_IMAGE -> TODO()
        }
    }

    fun onQuickTap(imageATapped : Boolean) {
        when (state) {
            Companion.State.DEFAULT -> {

            }
            Companion.State.TAP_LEFT_IMAGE -> {

            }
            Companion.State.TAP_RIGHT_IMAGE -> {

            }
            else -> {}
        }
        if (imageATapped) {
            animateCutPercentage(100, 100L, 0)
        } else {
            animateCutPercentage(0, 100L, 0)
        }

    }

    private fun animateCutPercentage(imageAPercentage: Int,
                                     duration : Long =  Math.abs(cutPercentage -
                                             Math.max(Math.min(imageAPercentage, 75), 25)) * 30L,
                                     startDelay : Long = 200): ValueAnimator? {
        cutLocationAnimator?.cancel()
        val parsedWeight = Math.max(Math.min(imageAPercentage, 75), 25)
        cutLocationAnimator = ValueAnimator.ofInt(cutPercentage, parsedWeight)
        cutLocationAnimator?.let {
            it.interpolator = AccelerateDecelerateInterpolator()
            it.addUpdateListener { animation ->
                cutPercentage = animation.animatedValue as Int
            }
            it.duration = duration
            it.startDelay = startDelay
            it.start()
        }
        return cutLocationAnimator
    }

    private fun animateCutWidthPercentage(from: Int, to:Int): ValueAnimator? {
        cutWidthAnimator?.cancel()
        cutWidthAnimator = ValueAnimator.ofInt(from, to)
        cutWidthAnimator?.let {
            it.interpolator = LinearInterpolator()
            it.addUpdateListener {
                cut_left.cutWidthPercentage = it.animatedValue as Int
                cut_right.cutWidthPercentage = it.animatedValue as Int
            }
            it.duration = 300
            it.start()
        }
        return cutWidthAnimator
    }

    fun render(model: RenderModelDO) {
        if (this.model != model) {
            this.model = model
            render()
        }
    }

    fun setState(state: State, allowAnimations: Boolean = true) {
        val oldState = this.state
        this.state = state
        return when(state) {   //Ensures that the when statement is exhaustive
            Companion.State.DEFAULT -> setStateDefault(animate = when(oldState) {
                Companion.State.DEFAULT -> false
                Companion.State.SHOW_PERCENTAGE -> true
                else -> false
            } and allowAnimations)
            Companion.State.SHOW_PERCENTAGE -> setStateShowPercentage(animate = when(oldState) {
                Companion.State.DEFAULT -> true
                Companion.State.SHOW_PERCENTAGE -> false
                else -> false
            } and allowAnimations)
            else -> {}
        }
    }

    private fun setStateDefault(animate: Boolean) {
        if (animate) {
            percent_a.fadeOut()
            percent_b.fadeOut()
            animateCutWidthPercentage(0, 100)?.doOnEnd {
                animateCutPercentage(50)
            }
        } else {
            cutPercentage = 50

            cut_left.cutWidthPercentage = 100
            cut_right.cutWidthPercentage = 100

            percent_a.visibility = View.GONE
            percent_b.visibility = View.GONE
        }
    }

    private fun setStateShowPercentage(animate: Boolean) {
        val percentage = if (model.imageA.voteCount == 0L && model.imageB.voteCount == 0L) {
            50
        } else {
            (100 * (model.imageA.voteCount.toFloat() / (model.imageB.voteCount + model.imageA.voteCount).toFloat())).toInt()
        }
        if (animate) {
            animateCutWidthPercentage(100, 0)?.doOnEnd {
                animateCutPercentage(percentage)?.doOnStart {
                    percent_a.fadeIn(500)
                    percent_b.fadeIn(500)
                }
            }
        } else {
            cutPercentage = percentage
            cut_left.cutWidthPercentage = 0
            cut_right.cutWidthPercentage = 0

            percent_a.visibility = View.VISIBLE
            percent_a.alpha = 1f

            percent_b.visibility = View.VISIBLE
            percent_b.alpha = 1f
        }
    }

    private fun render() {
        fun newTagView(text: String, @ColorRes backgroundColor: Int = android.R.color.white, @ColorRes textColor: Int? = null): View {
            val tagView = View.inflate(context, R.layout.tag, null)
            tagView.text.text = text
            tagView.text.setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            textColor?.let {
                tagView.text.setTextColor(ContextCompat.getColor(context, textColor))
            }
            return tagView
        }

        tags_container.removeAllViews()
        Glide.with(this).load(model.imageA.imageUrl).into(image_left)
        Glide.with(this).load(model.imageB.imageUrl).into(image_right)
        title.text = model.id
        tags_container.addView(newTagView(model.creator, android.R.color.black, android.R.color.white))
        model.tags.forEach { tag ->
            tags_container.addView(newTagView(tag.name))
        }
        val aVotePercentage = (100 * (model.imageA.voteCount.toFloat() / (model.imageB.voteCount + model.imageA.voteCount).toFloat())).toInt()
        val bVotePercentage = 100 - aVotePercentage
        percent_a.text = "$aVotePercentage%"
        percent_b.text = "$bVotePercentage%"
    }


    override fun setOnClickListener(l: OnClickListener?) {
        constraint_root.setOnClickListener(l)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        onWeightChanged()
    }

    private fun onWeightChanged() {
        cut_left.cutPercentage = cutPercentage
        cut_right.cutPercentage = cutPercentage
    }
}