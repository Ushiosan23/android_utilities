package com.github.ushiosan23.android_utilities.utilities

import android.util.Log

fun logError(tag: String, throwable: Throwable) {
	Log.e(tag, throwable.message ?: "", throwable)
}
