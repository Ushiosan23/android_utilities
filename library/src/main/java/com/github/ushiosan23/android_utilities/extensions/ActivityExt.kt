package com.github.ushiosan23.android_utilities.extensions

import android.app.Activity
import android.content.Intent
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
import com.google.android.material.snackbar.Snackbar

/* ---------------------------------------------------------
 *
 * Intent
 *
 * --------------------------------------------------------- */

/**
 * Change activity extension.
 *
 * @param T Current activity
 * @param L Target generic class
 * @param bundle Additional options for how the Activity should be started.
 * @param extra Extra intent arguments
 */
inline fun <T : Activity, reified L> T.changeActivity(bundle: Bundle? = null, vararg extra: Pair<String, Any?>) =
	with(Intent(this, L::class.java)) {
		extra.forEach { putAnyExtra(it) }
		startActivity(this, bundle)
	}

/**
 * Change activity and wait for result.
 *
 * @param T Current activity
 * @param L Target generic class
 * @param requestCode Activity request code
 * @param bundle Additional options for how the Activity should be started.
 * @param extra Extra intent arguments
 */
inline fun <T : Activity, reified L> T.changeActivityForResult(
	requestCode: Int,
	bundle: Bundle? = null,
	vararg extra: Pair<String, Any?>
) =
	with(Intent(this, L::class.java)) {
		extra.forEach { putAnyExtra(it) }
		startActivityForResult(this, requestCode, bundle)
	}

/**
 * Change activity extension.
 * Close current open activity after open the new activity.
 *
 * @param T Current activity
 * @param L Target generic class
 * @param bundle Additional options for how the Activity should be started.
 * @param extra Extra intent arguments
 */
inline fun <T : Activity, reified L> T.changeActivityAndClose(bundle: Bundle? = null, vararg extra: Pair<String, Any?>) {
	changeActivity<T, L>(bundle, *extra)
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

/* ---------------------------------------------------------
 *
 * Activity registration callbacks
 *
 * --------------------------------------------------------- */

/**
 * Register result activity.
 * You can use that to manage any type of contracts.
 *
 * @param T Target activity type
 * @param L Contract type
 * @param Y Result type
 * @param contract Contract instance
 * @param callback Callback result instance
 * @return Returns the activity result element to check
 */
fun <T : AppCompatActivity, L, Y> T.registerForResultEx(
	contract: ActivityResultContract<L, Y>,
	callback: ActivityResultCallback<Y>
): ActivityResultLauncher<L> =
	registerForActivityResult(contract, callback)

/**
 * Register activity to request result. This method is the new form to replace [Activity.startActivityForResult]
 *
 * @param T Target activity
 * @param callback Result callback
 * @return Return the activity result launcher instance
 */
fun <T : AppCompatActivity> T.registerActivityForResultEx(
	callback: ActivityResultCallback<ActivityResult>
): ActivityResultLauncher<Intent> =
	registerForResultEx(ActivityResultContracts.StartActivityForResult(), callback)

