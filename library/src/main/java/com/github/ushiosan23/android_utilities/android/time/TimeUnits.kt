package com.github.ushiosan23.android_utilities.android.time

@Suppress("MemberVisibilityCanBePrivate")
object TimeUnits {

	/**
	 * Get milliseconds unit
	 *
	 * @param v Total time unit
	 */
	@JvmStatic
	fun getUnit(v: Number = 1): Long = (1 * v.toDouble()).toLong()

	/**
	 * Get milliseconds unit
	 *
	 * @param v Total seconds
	 */
	@JvmStatic
	fun getSeconds(v: Number = 1): Long = (getUnit(1000) * v.toDouble()).toLong()

	/**
	 * Get milliseconds unit
	 *
	 * @param v Total minutes
	 */
	@JvmStatic
	fun getMinutes(v: Number = 1): Long = (getSeconds(60) * v.toDouble()).toLong()

	/**
	 * Get milliseconds unit
	 *
	 * @param v Total hours
	 */
	@JvmStatic
	fun getHours(v: Number = 1): Long = (getMinutes(60) * v.toDouble()).toLong()

	/**
	 * Get milliseconds unit
	 *
	 * @param v Total days
	 */
	@JvmStatic
	fun getDays(v: Number = 1): Long = (getHours(24) * v.toDouble()).toLong()

}
