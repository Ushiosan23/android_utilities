package com.github.ushiosan23.android_utilities.utilities

import java.util.*

val systemCalendar: Calendar
	get() = Calendar.getInstance()

fun getTimeMillis(): Long =
	systemCalendar.timeInMillis
