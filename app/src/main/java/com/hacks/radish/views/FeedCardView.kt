package com.hacks.radish.views

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.hacks.radish.R
import com.hacks.radish.views.SplitImageView.Companion.CUT_LEFT
import com.hacks.radish.views.SplitImageView.Companion.CUT_RIGHT
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.tag.view.*
import kotlinx.android.synthetic.main.view_feed_card.view.*

class FeedCardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        enum class State {
            DEFAULT,
            SHOW_PERCENTAGE
        }
    }

    data class RenderModel(val title: String,
                           val creator: String,
                           val tags: List<RenderModel.Tag>,
                           val imageA: RenderModel.Image,
                           val imageB: RenderModel.Image) {
        data class Image(val url: String, val votes: Long)
        data class Tag(val name: String)
    }

    private var model: RenderModel = RenderModel(
        title = "",
        creator = "",
        tags = listOf(),
        imageA = RenderModel.Image("", 0),
        imageB = RenderModel.Image("", 0))

    private var state: State = Companion.State.DEFAULT

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

    private fun animateCutPercentage(imageAPercentage: Int) {
        imageCutAnimator?.cancel()
        val parsedWeight = Math.max(Math.min(imageAPercentage, 75), 25)
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

    fun render(model: RenderModel) {
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
            } and allowAnimations)
            Companion.State.SHOW_PERCENTAGE -> setStateShowPercentage(animate = when(oldState) {
                Companion.State.DEFAULT -> true
                Companion.State.SHOW_PERCENTAGE -> false
            } and allowAnimations)
        }
    }

    private fun setStateDefault(animate: Boolean) {
        if (animate) {
            animateCutPercentage(50)
        } else {
            cutPercentage = 50
        }
    }

    private fun setStateShowPercentage(animate: Boolean) {
        val percentage = if (model.imageA.votes == 0L && model.imageB.votes == 0L) {
            50
        } else {
            (model.imageA.votes / (model.imageB.votes + model.imageA.votes)).toInt()
        }
        if (animate) {
            animateCutPercentage(percentage)
        } else {
            cutPercentage = percentage
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
        Picasso.get().load(model.imageA.url).into(image_left)
        Picasso.get().load(model.imageB.url).into(image_right)
        title.text = model.title
        tags_container.addView(newTagView(model.creator, android.R.color.black, android.R.color.white))
        model.tags.forEach { tag ->
            tags_container.addView(newTagView(tag.name))
        }
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