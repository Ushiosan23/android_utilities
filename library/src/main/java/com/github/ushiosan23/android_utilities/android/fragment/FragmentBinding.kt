package com.github.ushiosan23.android_utilities.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.utilities.inflateBinding

abstract class FragmentBinding<T : ViewBinding> : Fragment(), IFragmentBinding<T> {

	/* ---------------------------------------------------------
	 *
	 * Properties
	 *
	 * --------------------------------------------------------- */

	@Suppress("MemberVisibilityCanBePrivate")
	protected val binding: T
		get() = internalBinding!!

	protected open val attachToRoot: Boolean = false

	/* ---------------------------------------------------------
	 *
	 * Internal properties
	 *
	 * --------------------------------------------------------- */

	private var internalBinding: T? = null

	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		// Initialize binding
		internalBinding = inflateBinding(
			bindingClass,
			inflater,
			container,
			attachToRoot
		)
		// Check if binding is an error
		if (internalBinding == null) return null
		// Return view
		onFragmentLoaded(savedInstanceState)
		return binding.root
	}


}
