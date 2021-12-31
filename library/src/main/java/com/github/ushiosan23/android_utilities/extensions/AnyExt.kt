package com.github.ushiosan23.android_utilities.extensions

/**
 * Object string representation
 *
 * @return Special object string representation
 */
fun Any.toInfoString(): String =
	"(@${Integer.toHexString(hashCode())}) ${javaClass.name}"

/**
 * Object string representation
 *
 * @return Special object short string representation
 */
fun Any.toShortInfoString(): String =
	"(@${Integer.toHexString(hashCode())}) ${javaClass.simpleName}"

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
inline fun <reified V> Any.tryCast(action: (item: V) -> Unit): Any {
	// Check types
	if (this is V) action.invoke(this as V)
	// Return current instance
	return this
}

/**
 * Try to cast object to another type
 *
 * @param action Target action to execute if object was changed
 * @return Return current object
 */
inline fun <reified V> Any.tryCastContext(action: V.() -> Unit): Any {
	// Check type
	if (this is V) action.invoke(this as V)
	// Return current instance
	return this
}
