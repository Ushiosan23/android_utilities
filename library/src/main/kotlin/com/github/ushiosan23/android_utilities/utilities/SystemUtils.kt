package com.github.ushiosan23.android_utilities.utilities

import android.os.Handler
import android.os.Looper

/**
 * Storage
 */
private lateinit var localSystemHandler: Handler

/**
 * Get valid system handler.
 * Initialize handler if reference is `null`.
 *
 * @return Returns a system handler instance
 */
fun getSystemHandler(): Handler = if (!::localSystemHandler.isInitialized) {
	localSystemHandler = Handler(Looper.getMainLooper())
	localSystemHandler
} else {
	localSystemHandler
}

/**
 * Run action in android UI Thread.
 * This method can be called outside of the application context.
 *
 * @param action Target action to execute
 */
fun runOnUiThreadEx(action: Runnable) = getSystemHandler().post(action)

/**
 * Run action in android UI Thread with delay.
 * This method can be called outside of the application context.
 *
 * @param action Target action to execute
 * @param delay Delay time in milliseconds
 */
fun runOnUiThreadEx(action: Runnable, delay: Long = 0L) = getSystemHandler().postDelayed(action, delay)
