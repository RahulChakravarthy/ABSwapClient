package com.hacks.radish.util

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.hacks.radish.R
import kotlin.reflect.KClass

//region Snackbar makers
fun Context.longSnackbar(view: View,
                         msg: String,
                         onDismissed: () -> Unit = {},
                         onShown: () -> Unit = {}) {
    snackbar(view, msg, Snackbar.LENGTH_LONG, onDismissed, onShown)
}

fun Context.longSnackbar(view: View,
                         @StringRes msg: Int,
                         onDismissed: () -> Unit = {},
                         onShown: () -> Unit = {}) {
    longSnackbar(view, getString(msg), onDismissed, onShown)
}

fun Context.shortSnackbar(view: View,
                          msg: String,
                          onDismissed: () -> Unit = {},
                          onShown: () -> Unit = {}) {
    snackbar(view, msg, Snackbar.LENGTH_SHORT, onDismissed, onShown)
}

fun Context.shortSnackbar(view: View,
                          @StringRes msg: Int,
                          onDismissed: () -> Unit = {},
                          onShown: () -> Unit = {}) {
    shortSnackbar(view, getString(msg), onDismissed, onShown)
}

fun Context.snackbar(view: View,
                     msg: String,
                     length: Int = Snackbar.LENGTH_SHORT,
                     onDismissed: () -> Unit = {},
                     onShown: () -> Unit = {}) {
    val snackbar = Snackbar.make(view, msg, length)

    // Add styling
    snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
    val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
    textView.setTextColor(ContextCompat.getColor(this, R.color.colorAccent))

    snackbar.addCallback(object : Snackbar.Callback() {
        override fun onDismissed(snackbar: Snackbar, event: Int) {
            onDismissed()
        }

        override fun onShown(sb: Snackbar?) {
            onShown()
        }
    })

    snackbar.show()
}

fun Context.snackbar(view: View,
                     @StringRes msg: Int,
                     length: Int = Snackbar.LENGTH_SHORT,
                     onDismissed: () -> Unit = {},
                     onShown: () -> Unit = {}) {
    snackbar(view, getString(msg), length, onDismissed, onShown)
}

//endregion

/** Syntax helper to convert
 * data.observe(this, Observer<Int> { ... })
 * to
 * data.observe(this) { ... }
 */
fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T?) -> Unit) = observe(owner, Observer(observer))

fun <T : ViewModel> ViewModelProvider.get(modelClass: KClass<T>): T = get(modelClass.java)

/*
fun convertToDp(sizeInPixels: Double): Double {
    val resources = MainApplication.getInstance().applicationContext.resources
    val metrics = resources.displayMetrics
    return sizeInPixels / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}
fun convertToPixels(sizeInDp: Double): Double {
    val resources = MainApplication.getInstance().applicationContext.resources
    val metrics = resources.displayMetrics
    return sizeInDp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}
fun getPhoneHeightPixels(): Float {
    return MainApplication.getInstance().displayMetrics.heightPixels.toFloat()
}
fun getPhoneWidthPixels(): Float {
    return MainApplication.getInstance().displayMetrics.widthPixels.toFloat()
}
fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}
fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}
fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction{replace(frameId, fragment)}
}
*/

fun <T> MutableLiveData<T>.publishCurrentValue() {
    postValue(this.value)
}