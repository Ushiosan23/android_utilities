package com.github.ushiosan23.android_utilities.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager

/* ---------------------------------------------------------
 *
 * Intent
 *
 * --------------------------------------------------------- */

inline fun <reified T> Activity.changeActivity(bundle: Bundle? = null, vararg extra: Pair<String, Any?>) =
	with(Intent(this, T::class.java)) {
		extra.forEach { putAnyExtra(it) }
		startActivity(this, bundle)
	}

inline fun <reified T> Activity.changeActivityForResult(
	requestCode: Int,
	bundle: Bundle? = null,
	vararg extra: Pair<String, Any?>
) =
	with(Intent(this, T::class.java)) {
		extra.forEach { putAnyExtra(it) }
		startActivityForResult(this, requestCode, bundle)
	}

inline fun <reified T> Activity.changeActivityAndClose(bundle: Bundle? = null, vararg extra: Pair<String, Any?>) {
	changeActivity<T>(bundle, *extra)
	finish()
}

/* ---------------------------------------------------------
 *
 * Utilities
 *
 * --------------------------------------------------------- */

@Suppress("DEPRECATION")
fun Activity.getDisplayMetrics(): DisplayMetrics {
	val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
	val wDisplay = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) display!! else windowManager.defaultDisplay
	// Get metrics
	val metrics = DisplayMetrics()
	wDisplay.getRealMetrics(metrics)

	return metrics
}
