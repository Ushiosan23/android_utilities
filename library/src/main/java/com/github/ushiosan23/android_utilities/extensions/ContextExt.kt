package com.github.ushiosan23.android_utilities.extensions

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes

/* ---------------------------------------------------------
 *
 * Toast
 *
 * --------------------------------------------------------- */

/**
 * Make context toast.
 *
 * @param resId Toast content text
 * @param duration Toast duration
 * @return Return configured toast
 */
fun <T : Context> T.makeToast(@StringRes resId: Int, duration: Int): Toast =
	Toast.makeText(this, resId, duration)

/**
 * Make activity toast.
 *
 * @param text Toast text content
 * @param duration Toast duration
 * @return Return configured toast
 */
fun <T : Context> T.makeToast(text: CharSequence, duration: Int): Toast =
	Toast.makeText(this, text, duration)

/* ---------------------------------------------------------
 *
 * Display Metrics
 *
 * --------------------------------------------------------- */

/**
 *
 */
@Suppress("DEPRECATION")
fun <T : Context> T.getDisplayMetrics(): DisplayMetrics {
	val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
	// Get display depends of version
	val wdy = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) display!! else wm.defaultDisplay
	// Initialize metrics
	val metrics = DisplayMetrics()
	wdy.getRealMetrics(metrics)
	// Get metrics
	return metrics
}
