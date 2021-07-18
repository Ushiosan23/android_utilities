package com.github.ushiosan23.android_utilities.utilities

import kotlin.math.abs
import kotlin.random.Random

/**
 * Get system random property
 */
private lateinit var defaultRandom: Random

/**
 * Get system random.
 * Initialize system random if is `null`
 *
 * @return Return a valid random object
 */
private fun getRandom(): Random = if (!::defaultRandom.isInitialized) {
	defaultRandom = Random(getTimeMillis())
	defaultRandom
} else {
	defaultRandom
}

/* ---------------------------------------------------------
 *
 * Methods
 *
 * --------------------------------------------------------- */

/**
 * Generate a random order list.
 * This method generate a list between `0` and [total] in random order
 *
 * @param total Generated list size
 * @return Returns a list with all elements in random order
 */
fun generateIntList(total: Int): List<Int> =
	if (total == 0) emptyList() else generateIntListImpl(total, mutableListOf())

/**
 * Generate a random order list.
 * This method generate a list between [start] and [end] in random order.
 *
 * @param start First number to start
 * @param end Last range number
 * @return Returns a list with all elements in random order
 */
fun generateIntRangeList(start: Int, end: Int): List<Int> =
	if ((start - end) == 0) emptyList() else generateIntRangeListImpl(start until end, mutableListOf())

/* ---------------------------------------------------------
 *
 * Internal methods
 *
 * --------------------------------------------------------- */

private fun generateIntListImpl(total: Int, list: MutableList<Int>): List<Int> {
	val next = getRandom().nextInt(0, total)

	while (list.count() < total) {
		if (list.contains(next)) continue else list.add(next)
	}

	return list
}

private fun generateIntRangeListImpl(range: IntRange, list: MutableList<Int>): List<Int> {
	val next = getRandom().nextInt(range.first, range.last)
	val count = abs(range.first) + abs(range.last)

	while (list.count() < count) {
		if (list.contains(next)) continue else list.add(next)
	}

	return list
}
