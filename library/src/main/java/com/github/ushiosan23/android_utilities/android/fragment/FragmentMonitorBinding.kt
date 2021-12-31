package com.github.ushiosan23.android_utilities.android.fragment

import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.android.app.IApplication
import com.github.ushiosan23.android_utilities.extensions.toShortInfoString
import com.github.ushiosan23.android_utilities.extensions.tryCast
import com.github.ushiosan23.android_utilities.utilities.logError
import com.github.ushiosan23.android_utilities.utilities.logInfo

abstract class FragmentMonitorBinding<T : ViewBinding> : FragmentBinding<T>() {

	/**
	 * Called when the Fragment is visible to the user.  This is generally
	 * tied to [android.app.Activity.onStart] of the containing
	 * Activity's lifecycle.
	 */
	override fun onStart() {
		super.onStart()
		// Register fragment on application
		try {
			requireActivity().application.tryCast<IApplication> { it.addFragment(this) }
		} catch (err: Exception) {
			logError(err)
		}
		// Display information
		logInfo(this.javaClass.simpleName, toShortInfoString())
	}

	/**
	 * Called when the Fragment is no longer resumed.  This is generally
	 * tied to [android.app.Activity.onPause] of the containing
	 * Activity's lifecycle.
	 */
	override fun onPause() {
		super.onPause()
		try {
			requireActivity().application.tryCast<IApplication> {
				it.removeFragment(this)
			}
		} catch (err: Exception) {
			logError(err)
		}
		// Display information
		logInfo(this.javaClass.simpleName, null)
	}

}
