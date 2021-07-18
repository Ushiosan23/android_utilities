package com.github.ushiosan23.android_utilities.android.dialog

import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.android.fragment.IFragmentBinding

/**
 * Interface model to use in dialog bindings
 *
 * @param T Layout binding class
 * @see IFragmentBinding
 */
interface IDialogFragmentBinding<T : ViewBinding> : IFragmentBinding<T> {

	/**
	 * Called when all fragments are loaded.
	 */
	fun onDialogFragmentLoaded()

}
