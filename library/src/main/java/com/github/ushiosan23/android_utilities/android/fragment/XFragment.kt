package com.github.ushiosan23.android_utilities.android.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager

open class XFragment : Fragment {

	/* ---------------------------------------------------------
	 *
	 * Properties
	 *
	 * --------------------------------------------------------- */

	/**
	 * Activity view model provider
	 */
	private lateinit var fragmentViewModelProvider: ViewModelProvider

	/**
	 * Application preferences
	 */
	lateinit var sharedPreferences: SharedPreferences

	/* ---------------------------------------------------------
	 *
	 * Constructors
	 *
	 * --------------------------------------------------------- */

	/**
	 * Default constructors
	 */
	constructor() : super()

	/**
	 * Constructor with layout
	 */
	constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

	/**
	 * Called immediately after [.onCreateView]
	 * has returned, but before any saved state has been restored in to the view.
	 * This gives subclasses a chance to initialize themselves once
	 * they know their view hierarchy has been completely created.  The fragment's
	 * view hierarchy is not however attached to its parent at this point.
	 * @param view The View returned by [.onCreateView].
	 * @param savedInstanceState If non-null, this fragment is being re-constructed
	 * from a previous saved state as given here.
	 */
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
	}

	/**
	 * Get activity fragment provider
	 *
	 * @return [ViewModelProvider] instance
	 */
	@Suppress("MemberVisibilityCanBePrivate")
	fun getViewModelProvider(): ViewModelProvider {
		// Check if property is initialized
		if (!::fragmentViewModelProvider.isInitialized) {
			fragmentViewModelProvider = ViewModelProvider(requireActivity())
		}
		// Get provider
		return fragmentViewModelProvider
	}

	/**
	 * Get view model instance
	 *
	 * @param T Target view model generic
	 * @param type Class type
	 * @return [T] View model instance
	 */
	fun <T : ViewModel> getViewModel(type: Class<T>): T {
		return getViewModelProvider().get(type)
	}

	/**
	 * Get view model instance
	 *
	 * @param T Target view model generic
	 * @return [T] View model instance
	 */
	inline fun <reified T : ViewModel> viewModelOf(): T =
		getViewModel(T::class.java)

}
