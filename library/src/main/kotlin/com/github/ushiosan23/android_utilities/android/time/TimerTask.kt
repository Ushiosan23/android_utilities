package com.github.ushiosan23.android_utilities.android.time

import java.util.*


class TimerTask private constructor(t: Long, tt: () -> Unit) {

	/**
	 * Timer time
	 */
	private val time: Long = t

	/**
	 * Task to execute
	 */
	private val task: () -> Unit = tt

	/**
	 * Native timer
	 */
	private val timer: Timer = Timer()

	/**
	 * Start timer task
	 *
	 * @param oneShot Determine if task run only one time
	 */
	fun start(oneShot: Boolean = true) {
		if (oneShot) {
			timer.schedule(generateTask(task), time)
		} else {
			timer.scheduleAtFixedRate(generateTask(task), time, time)
		}
	}

	/**
	 * Stop current timer
	 */
	fun stop() = timer.cancel()

	/**
	 * Generate valid timer task
	 *
	 * @param task Timer task
	 */
	private fun generateTask(task: () -> Unit): java.util.TimerTask = object : java.util.TimerTask() {
		override fun run() {
			task.invoke()
		}
	}

	/**
	 * Companion object
	 */
	companion object {

		/**
		 * Generate timer task
		 *
		 * @param time Wait time
		 * @param action Action to invoke
		 */
		@JvmStatic
		fun generateTimerTask(time: Long, action: () -> Unit): TimerTask =
			TimerTask(time, action)

	}

}
