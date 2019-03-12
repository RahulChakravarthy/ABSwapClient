package tech.r7chakra.abswapclient.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.view_percentage_filter.view.*
import tech.r7chakra.abswapclient.util.GlideApp


class PercentageFilterView : View {

    constructor(context : Context) : super(context)
    constructor(context : Context, attrs : AttributeSet) : super(context, attrs)
    constructor(context : Context, attrs : AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr)
    constructor(context : Context, attrs : AttributeSet, defStyleAttr : Int, defStyleRes : Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private fun init(attrs: AttributeSet) {
        inflate(context, tech.r7chakra.abswapclient.R.layout.view_percentage_filter, viewPercentageLayout)

        val attributes = context.obtainStyledAttributes(attrs, tech.r7chakra.abswapclient.R.styleable.PercentageFilterView)
        attributes.recycle()
    }

    fun setMainBackgroundImage(url : String) {
        GlideApp
            .with(this)
            .load(url)
            .centerCrop()
            .into(mainImageView)
    }

    /**
     * Display counting percentage and greyed out image
     */
    fun displayPercentage(percentageValue : Int) {
        //Apply gray filter on imageview
        val greyFilter = PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY)
        mainImageView.colorFilter = greyFilter

        //Animate the text
        mainText.visibility = View.VISIBLE
        val animator = ValueAnimator.ofInt(0, percentageValue)
        animator.duration = 1000
        animator.interpolator = DecelerateInterpolator()
        animator.addUpdateListener {
            mainText.text = it.animatedValue.toString()
        }
    }

}