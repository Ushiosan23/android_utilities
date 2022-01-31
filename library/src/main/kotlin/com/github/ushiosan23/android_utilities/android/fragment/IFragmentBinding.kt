package com.github.ushiosan23.android_utilities.android.fragment

import android.os.Bundle
import androidx.viewbinding.ViewBinding

/**
 * Interface model to use in fragment bindings
 *
 * @param T Layout binding class
 */
interface IFragmentBinding<T : ViewBinding> {

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

	/**
	 * Called when fragment was loaded.
	 *
	 * @param savedInstanceState The last saved instance state of the Fragment, or null if this is a freshly created Fragment.
	 */
	fun onFragmentLoaded(savedInstanceState: Bundle?)

}
