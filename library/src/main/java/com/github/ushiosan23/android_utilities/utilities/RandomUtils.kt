package com.github.ushiosan23.android_utilities.utilities

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
fun generateIntList(total: UInt): List<Int> =
	if (total == 0u) emptyList() else generateIntListImpl(total.toInt(), mutableListOf())

/* ---------------------------------------------------------
 *
 * Internal methods
 *
 * --------------------------------------------------------- */

private fun generateIntListImpl(total: Int, list: MutableList<Int>): List<Int> {

	do {
		val next = getRandom().nextInt(0, total)
		if (list.contains(next)) continue else list.add(next)
	} while (list.count() < total)

	return list
}
