package com.github.ushiosan23.android_utilities.android.time

import com.github.ushiosan23.android_utilities.time.TimerAction
import com.github.ushiosan23.android_utilities.utilities.runOnUiThreadEx
import java.util.*
import java.util.TimerTask

abstract class TaskJob protected constructor(
	/**
	 * Determine if current job is executed only one time
	 */
	private val oneshot: Boolean,

	/**
	 * Timer execution time
	 */
	private val time: Long,

	/**
	 * Action to execute
	 */
	private val action: TimerAction<TimerInfo>
) {

	/**
	 * Determine if action is running on ui context
	 */
	@Volatile
	var runOnUIContext: Boolean = false

	/**
	 * Internal job executor
	 */
	private val executor: Timer = Timer()

	/**
	 * Determine if job is active
	 */
	@Volatile
	var active: Boolean = false
		private set

	/**
	 * Last send info
	 */
	private lateinit var lastInfo: TimerInfo

	/* ------------------------------------------------------------------
	 * Methods
	 * ------------------------------------------------------------------ */

	/**
	 * Start current job
	 */
	@Synchronized
	fun start() {
		// Check if timer already start
		if (active)
			throw IllegalStateException("Current task already started")
		// Generate task interval
		if (oneshot) {
			executor.schedule(invokeInternal(), time)
		} else {
			executor.scheduleAtFixedRate(invokeInternal(), time, time)
		}
		// Change state
		active = true
	}

	/**
	 * Stop current execution
	 */
	@Synchronized
	fun stop() {
		// Check status
		if (!active) return
		// Cancel interval
		executor.cancel()
	}

	/* ------------------------------------------------------------------
	 * Internal methods
	 * ------------------------------------------------------------------ */

	/**
	 * Invoke action every timer interval
	 */
	private fun invokeInternal() = object : TimerTask() {

		override fun run() {
			// Generate information
			if (!this@TaskJob::lastInfo.isInitialized)
				lastInfo = TimerInfo(0L, 0L, oneshot)
			// Next info
			val next = TimerInfo(
				lastInfo.totalTime + time,
				lastInfo.iterations + 1,
				oneshot
			)
			// Invoke task
			if (runOnUIContext) {
				runOnUiThreadEx { action.invoke(next) }
			} else {
				action.invoke(next)
			}
			// Update info
			lastInfo = next
			if (oneshot) active = false
		}

	}

	/* ------------------------------------------------------------------
	 * Implementations
	 * ------------------------------------------------------------------ */

	/**
	 * Implementation task
	 */
	companion object {

		/**
		 * Generate Task job
		 *
		 * @param oneshot Determine if timer execute action one time
		 * @param time Timer execution time
		 * @param action Action to execute
		 */
		@JvmStatic
		fun <T : TimerAction<TimerInfo>> generateOf(oneshot: Boolean, time: Long, action: T): TaskJob = object : TaskJob(
			oneshot,
			time,
			action
		) {}

		/**
		 * Generate Task job
		 *
		 * @param time Timer execution time
		 * @param action Action to execute
		 */
		@JvmStatic
		fun <T : TimerAction<TimerInfo>> generateOf(time: Long, action: T): TaskJob =
			generateOf(true, time, action)

		/**
		 * Generate oneshot task
		 *
		 * @param time Timer execution time
		 * @param action Action to execute
		 */
		@JvmStatic
		fun oneShotOf(time: Long, action: TimerAction.Empty<TimerInfo>): TaskJob =
			generateOf(true, time, action)

	}


}
