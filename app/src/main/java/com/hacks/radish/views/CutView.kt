package com.hacks.radish.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlin.properties.Delegates


class CutView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    companion object {
        const val CUT_LEFT = 0
        const val CUT_RIGHT = 1
    }

    var cutSide: Int by Delegates.observable(CUT_LEFT) { _, old, new ->
        if (old != new) {
            invalidate()
        }
    }

    var cutPercentage: Int by Delegates.observable(50) { _, old, new ->
        if (old != new) {
            invalidate()
            updatePadding()
        }
    }

    var cutWidthPercentage: Int by Delegates.observable(100) { _, old, new ->
        if (old != new) {
            invalidate()
            updatePadding()
        }
    }

    private val cutWidth: Float
        get() = (cutWidthPercentage / 100f) * (height.toFloat() / 2)

    private val cutRatio: Float
        get() = when {
            cutPercentage < 25 -> 25
            cutPercentage > 75 -> 75
            else -> cutPercentage
        } / 100f

    private val cutLocation: Float
        get() = cutRatio * width

    private val clearPaint = Paint(ANTI_ALIAS_FLAG)
    private val cutPath = Path()

    init{
        updatePadding()

        clearPaint.color = ContextCompat.getColor(context, android.R.color.black)
        clearPaint.style = Paint.Style.FILL
        clearPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)

        //In versions > 3.0 need to define layer Type
        if (android.os.Build.VERSION.SDK_INT >= 11)
        {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        updatePadding()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)

        cutPath.reset()
        if (cutSide == CUT_LEFT) {
            cutPath.moveTo(0f, 0f)
            cutPath.lineTo(0f, height.toFloat())
            cutPath.lineTo( cutLocation - cutWidth / 2, height.toFloat())
            cutPath.lineTo(cutLocation + cutWidth / 2, 0f)
            cutPath.lineTo(0f, 0f)
        } else {
            cutPath.moveTo(cutLocation + cutWidth / 2, 0f)
            cutPath.lineTo(cutLocation - cutWidth / 2, height.toFloat())
            cutPath.lineTo(width.toFloat(), height.toFloat())
            cutPath.lineTo(width.toFloat(), 0f)
            cutPath.lineTo(cutLocation + cutWidth / 2, 0f)
        }
        cutPath.close()

        canvas?.drawPath(cutPath, clearPaint)
    }

    private fun updatePadding() {
        if (cutSide == CUT_RIGHT) {
            val padRight = width - (cutLocation + cutWidth / 2)
            setPadding(0,0, padRight.toInt(), 0)
        } else {
            val padLeft = cutLocation - cutWidth / 2
            setPadding(padLeft.toInt(), 0,0,0)
        }
    }
}