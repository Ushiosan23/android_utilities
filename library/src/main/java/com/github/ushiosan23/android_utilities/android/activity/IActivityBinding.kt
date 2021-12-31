package com.github.ushiosan23.android_utilities.android.activity

import androidx.viewbinding.ViewBinding

/**
 * Interface model to use in activity bindings
 *
 * @param T Layout binding class
 * @see IActivityCompat
 */
interface IActivityBinding<T : ViewBinding> : IActivityCompat {

	/**
	 * Binding element instance
	 */
	val binding: T

	/**
	 * Binding class.
	 * This class is used to call `inflate` method via reflexion.
	 * @see com.github.ushiosan23.android_utilities.extensions.resolveClass
	 */
	val bindingClass: Class<T>

}
