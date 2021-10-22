package com.github.ushiosan23.android_utilities.extensions

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.preference.PreferenceManager

/* ---------------------------------------------------------
 *
 * Shared preferences
 *
 * --------------------------------------------------------- */

/**
 * Global app shared preferences
 */
private lateinit var appSharedPreferences: SharedPreferences

/**
 * Get application shared preferences
 */
val Context.sharedPreferences: SharedPreferences
	get() = if (!::appSharedPreferences.isInitialized) {
		appSharedPreferences = PreferenceManager.getDefaultSharedPreferences(applicationContext)
		appSharedPreferences
	} else {
		appSharedPreferences
	}


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
 * Get context display metrics.
 *
 * @param T Target context
 * @return Returns context display metrics
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

/* ---------------------------------------------------------
 *
 * Network
 *
 * --------------------------------------------------------- */

/**
 * Get connectivity manager
 */
val Context.connectivityManager: ConnectivityManager
	get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
