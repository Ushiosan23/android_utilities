package com.github.ushiosan23.android_utilities.extensions

import androidx.fragment.app.Fragment

/**
 * Runs the specified action on the UI thread.
 * If the current thread is the UI thread, then the action is executed immediately.
 * If the current thread is not the UI thread, the action is posted to the event queue of the UI thread.
 *
 * @param action Action to execute in ui thread
 */
fun Fragment.runOnUiThread(action: Runnable) {
	requireActivity().runOnUiThread(action)
}
