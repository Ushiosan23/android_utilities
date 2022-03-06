package com.github.ushiosan23.android_utilities.android.time

data class TimerInfo(
	/**
	 * Total timer time
	 */
	val totalTime: Long,

	/**
	 * Total timer iterations
	 */
	val iterations: Long,

	/**
	 * Returns if current timer is a oneshot instance
	 */
	val isOneshot: Boolean
)
