package com.github.ushiosan23.android_utilities.utilities

import java.util.*

/**
 * Get system calendar
 */
val systemCalendar: Calendar
	get() = Calendar.getInstance()

/**
 * Get device time in milliseconds
 *
 * @return Return device UNIX time
 */
fun getTimeMillis(): Long =
	systemCalendar.timeInMillis
