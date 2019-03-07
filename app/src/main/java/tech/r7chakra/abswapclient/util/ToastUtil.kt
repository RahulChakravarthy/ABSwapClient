package tech.r7chakra.abswapclient.util

import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.Toast

object ToastUtils {
    fun positionToast(toast: Toast, view: View, window: Window, offsetX: Int, offsetY: Int) {
        // toasts are positioned relatively to decor view, views relatively to their parents, we have to gather additional data to have a common coordinate system
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        // covert anchor view absolute position to a position which is relative to decor view
        val viewLocation = IntArray(2)
        view.getLocationInWindow(viewLocation)
        val viewLeft = viewLocation[0] - rect.left
        val viewTop = viewLocation[1] - rect.top

        // measure toast to center it relatively to the anchor view
        val metrics = DisplayMetrics()
        window.windowManager.defaultDisplay.getMetrics(metrics)
        val widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(metrics.widthPixels, View.MeasureSpec.UNSPECIFIED)
        val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(metrics.heightPixels, View.MeasureSpec.UNSPECIFIED)
        toast.view.measure(widthMeasureSpec, heightMeasureSpec)
        val toastWidth = toast.view.measuredWidth

        // compute toast offsets    
        val toastX = viewLeft + (view.width - toastWidth) / 2 + offsetX
        val toastY = viewTop + view.height + offsetY

        toast.setGravity(Gravity.START or Gravity.TOP, toastX, toastY)
    }
}