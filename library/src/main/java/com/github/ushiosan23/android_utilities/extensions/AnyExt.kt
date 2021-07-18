package com.github.ushiosan23.android_utilities.extensions

/**
 * Return any object java class
 *
 * @param T Generic base object class
 * @param V Target generic reified result class
 * @return Return the object class result
 */
inline fun <T, reified V> T.resolveClass(): Class<V> = V::class.java
