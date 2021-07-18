package com.github.ushiosan23.android_utilities.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.utilities.inflateBinding

/**
 * Custom fragment. Also can attach view binding layout.
 * If you want to use this class, you need to enable android features:
 *
 * ```kotlin
 * buildFeatures {
 *   viewBinding = true
 * }
 * ```
 *
 * @param T Layout binding class
 * @see Fragment
 * @see IFragmentBinding
 */
abstract class FragmentBinding<T : ViewBinding> : Fragment(), IFragmentBinding<T> {

	/* ---------------------------------------------------------
	 *
	 * Properties
	 *
	 * --------------------------------------------------------- */

	/**
	 * Target layout binding instance.
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	protected val binding: T
		get() = internalBinding!!

	/**
	 * Property inflate fragment and attach to parent
	 */
	protected open val attachToRoot: Boolean = false

	/* ---------------------------------------------------------
	 *
	 * Internal properties
	 *
	 * --------------------------------------------------------- */

	/**
	 * Internal binding.
	 * This binding is used only to check binding status (because `lateinit` cause a lot of problems).
	 */
	private var internalBinding: T? = null

	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

	/**
	 * Called to have the fragment instantiate its user interface view.
	 * This is optional, and non-graphical fragments can return null.
	 * This will be called between [onCreate] and [onViewCreated].
	 *
	 * @param inflater The LayoutInflater object that can be used to inflate
	 * any views in the fragment
	 * @param container If non-null, this is the parent view that the fragment's
	 * UI should be attached to.
	 * @param savedInstanceState If non-null, this fragment is being re-constructed
	 * from a previous saved state as given here.
	 *
	 * @return Return the view for fragment binding or `null` if the fragment contains any error
	 */
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
		return binding.root
	}

	/**
	 * Initialize all fragments.
	 * This method use reflexion to inflate view binding.
	 *
	 * @param savedInstanceState If non-null, this fragment is being re-constructed
	 * from a previous saved state as given here.
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		onFragmentLoaded(savedInstanceState)
	}

}
