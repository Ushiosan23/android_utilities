package com.github.ushiosan23.android_utilities.utilities

import android.util.Log

/**
 * Show log information
 *
 * @param tag Used to identify the source of a log message.
 * It usually identifies the class or activity where the log call occurs.
 * @param message The message you would like logged.
 */
fun logInfo(tag: String, message: CharSequence?) = Log.i(tag, message.toString())

/**
 * Show log information
 *
 * @param obj Used to identify the source of a log message.
 */
fun logInfo(obj: Any?) = logInfo(if (obj == null) "null" else obj::class.java.name, "$obj")

/**
 * Show log warning
 *
 * @param tag Used to identify the source of a log message.
 * It usually identifies the class or activity where the log call occurs.
 * @param message The message you would like logged.
 */
fun logWarning(tag: String, message: CharSequence?) = Log.w(tag, message.toString())

/**
 * Show log warning
 *
 * @param obj Used to identify the source of a log message.
 */
fun logWarning(obj: Any?) = logWarning(if (obj == null) "null" else obj::class.java.name, "$obj")

/**
 * Show log error
 *
 * @param tag Used to identify the source of a log message.
 * It usually identifies the class or activity where the log call occurs.
 * @param throwable The message you would like logged.
 */
fun logError(tag: String, throwable: Throwable) = Log.e(tag, throwable.message ?: "null", throwable)

/**
 * Show log error
 *
 * @param throwable Used to identify the source of a log message.
 */
fun logError(throwable: Throwable) = logError(throwable::class.java.name, throwable)
