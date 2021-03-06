package com.github.ushiosan23.android_utilities.extensions

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar

/* ---------------------------------------------------------
 *
 * Intent
 *
 * --------------------------------------------------------- */

/**
 * Change activity extension.
 *
 * @param T Target generic class
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
 * @param T Target generic class
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
 * @param T Target generic class
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
 * @param T Current activity
 * @param resId Resource string id
 * @param duration Snack duration
 * @return Return configured snack bar
 */
fun <T : Activity> T.makeSnack(@StringRes resId: Int, duration: Int): Snackbar =
	Snackbar.make(getActivityViewRoot(), resId, duration)

/**
 * Make activity snackbar. The snack is attached to activity root view.
 *
 * @param T Current activity
 * @param text Snack content text
 * @param duration Snack duration
 * @return Return configured snack bar
 */
fun <T : Activity> T.makeSnack(text: CharSequence, duration: Int): Snackbar =
	Snackbar.make(getActivityViewRoot(), text, duration)

/* ---------------------------------------------------------
 *
 * Utilities
 *
 * --------------------------------------------------------- */

/**
 * Get activity view root.
 * This view is a special element and can be found in all activities.
 *
 * @param T Current activity
 * @return Return the activity root view
 * @see Window.ID_ANDROID_CONTENT
 */
fun <T : Activity> T.getActivityViewRoot(): View =
	findViewById(Window.ID_ANDROID_CONTENT)
