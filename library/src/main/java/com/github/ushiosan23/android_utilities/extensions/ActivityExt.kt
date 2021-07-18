package com.github.ushiosan23.android_utilities.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

/* ---------------------------------------------------------
 *
 * Intent
 *
 * --------------------------------------------------------- */

/**
 * Change activity extension.
 *
 * @param bundle Additional options for how the Activity should be started.
 * @param extra Extra intent arguments
 */
inline fun <reified T> Activity.changeActivity(bundle: Bundle? = null, vararg extra: Pair<String, Any?>) =
	with(Intent(this, T::class.java)) {
		extra.forEach { putAnyExtra(it) }
		startActivity(this, bundle)
	}

/**
 * Change activity and wait for result.
 *
 * @param requestCode Activity request code
 * @param bundle Additional options for how the Activity should be started.
 * @param extra Extra intent arguments
 */
inline fun <reified T> Activity.changeActivityForResult(
	requestCode: Int,
	bundle: Bundle? = null,
	vararg extra: Pair<String, Any?>
) =
	with(Intent(this, T::class.java)) {
		extra.forEach { putAnyExtra(it) }
		startActivityForResult(this, requestCode, bundle)
	}

/**
 * Change activity extension.
 * Close current open activity after open the new activity.
 *
 * @param bundle Additional options for how the Activity should be started.
 * @param extra Extra intent arguments
 */
inline fun <reified T> Activity.changeActivityAndClose(bundle: Bundle? = null, vararg extra: Pair<String, Any?>) {
	changeActivity<T>(bundle, *extra)
	finish()
}

/* ---------------------------------------------------------
 *
 * Snackbar
 *
 * --------------------------------------------------------- */

/**
 * Make activity snackbar. The snack is attached to activity root view.
 *
 * @param resId Resource string id
 * @param duration Snack duration
 * @return Return configured snack bar
 */
fun Activity.makeSnack(@StringRes resId: Int, duration: Int): Snackbar =
	Snackbar.make(getActivityViewRoot(), resId, duration)

/**
 * Make activity snackbar. The snack is attached to activity root view.
 *
 * @param text Snack content text
 * @param duration Snack duration
 * @return Return configured snack bar
 */
fun Activity.makeSnack(text: CharSequence, duration: Int): Snackbar =
	Snackbar.make(getActivityViewRoot(), text, duration)

/* ---------------------------------------------------------
 *
 * Toast
 *
 * --------------------------------------------------------- */

/**
 * Make activity toast.
 *
 * @param resId Toast content text
 * @param duration Toast duration
 * @return Return configured toast
 */
fun Activity.makeToast(@StringRes resId: Int, duration: Int): Toast =
	Toast.makeText(this, resId, duration)

/**
 * Make activity toast.
 *
 * @param text Toast text content
 * @param duration Toast duration
 * @return Return configured toast
 */
fun Activity.makeToast(text: CharSequence, duration: Int): Toast =
	Toast.makeText(this, text, duration)

/* ---------------------------------------------------------
 *
 * Utilities
 *
 * --------------------------------------------------------- */

/**
 * Get activity view root.
 * This view is a special element and can be found in all activities.
 *
 * @return Return the activity root view
 * @see Window.ID_ANDROID_CONTENT
 */
fun Activity.getActivityViewRoot(): View =
	findViewById(Window.ID_ANDROID_CONTENT)

/**
 * Get activity display metrics.
 *
 * @return Return the activity root view.
 */
@Suppress("DEPRECATION")
fun Activity.getDisplayMetrics(): DisplayMetrics {
	val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
	// Get display depends of android version
	val wDisplay = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) display!! else windowManager.defaultDisplay
	// Get metrics
	val metrics = DisplayMetrics()
	wDisplay.getRealMetrics(metrics)
	return metrics
}
