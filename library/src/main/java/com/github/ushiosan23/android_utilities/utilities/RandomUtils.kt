package com.github.ushiosan23.android_utilities.utilities

import kotlin.math.abs
import kotlin.random.Random

private lateinit var defaultRandom: Random
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

fun generateIntList(total: Int): List<Int> =
	if (total == 0) emptyList() else generateIntListImpl(total, mutableListOf())

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
