package com.github.ushiosan23.android_utilities.extensions

/**
 * Return any object java class
 *
 * @param T Generic base object class
 * @param V Target generic reified result class
 * @return Return the object class result
 */
inline fun <T, reified V> T.resolveClass(): Class<V> = V::class.java

/**
 * Try to cast object to another type
 *
 * @param action Target action to execute if object was changed
 * @return Return current object
 */
@Suppress("UNCHECKED_CAST")
fun <T, V> T.tryCast(action: (item: V) -> V) = try {
	action.invoke(this as V)
	this
} catch (ignored: Exception) {
	this
}
