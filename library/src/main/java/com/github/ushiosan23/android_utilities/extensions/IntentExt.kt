package com.github.ushiosan23.android_utilities.extensions

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import java.io.Serializable

/**
 * Extension used to put all valid intent objects.
 *
 * @param name Intent extra name
 * @param value Intent extra value
 * @return Returns the current intent with all extra values
 */
fun Intent.putAnyExtra(name: String, value: Any?): Intent = when (value) {
	is Bundle -> putExtra(name, value)
	is Serializable -> putExtra(name, value)
	is Array<*> -> putExtra(name, value)
	is Boolean -> putExtra(name, value)
	is BooleanArray -> putExtra(name, value)
	is Byte -> putExtra(name, value)
	is ByteArray -> putExtra(name, value)
	is Char -> putExtra(name, value)
	is CharArray -> putExtra(name, value)
	is Editable -> putExtra(name, value.toString())
	is CharSequence -> putExtra(name, value)
	is Double -> putExtra(name, value)
	is DoubleArray -> putExtra(name, value)
	is Float -> putExtra(name, value)
	is FloatArray -> putExtra(name, value)
	is Int -> putExtra(name, value)
	is IntArray -> putExtra(name, value)
	is Long -> putExtra(name, value)
	is LongArray -> putExtra(name, value)
	is Short -> putExtra(name, value)
	is ShortArray -> putExtra(name, value)
	is String -> putExtra(name, value)
	else -> putExtra(name, Bundle())
}

/**
 * Extension used to put all valid intent objects.
 *
 * @param pair Map pair value
 * @return Returns the current intent with all extra values
 */
fun Intent.putAnyExtra(pair: Pair<String, Any?>): Intent =
	putAnyExtra(pair.first, pair.second)
