package com.github.ushiosan23.android_utilities.extensions

/**
 * Apply to any text valid format
 *
 * @param args Target format arguments
 * @return Formatted string result
 */
fun CharSequence.format(vararg args: Any?): String =
	String.format(this.toString(), *args)
