package com.github.ushiosan23.android_utilities.android.activity

import android.app.Activity
import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.android.app.IApplication
import com.github.ushiosan23.android_utilities.extensions.tryCast

abstract class ActivityMonitorCompatBinding<T : ViewBinding> : ActivityCompatBinding<T>() {


	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

	/**
	 * Initialize all fragments
	 */
	override fun onStart() {
		super.onStart()
		setMonitoringActivity(this)
	}

	/**
	 * Dispatch onPause() to fragments.
	 */
	override fun onPause() {
		super.onPause()
		setMonitoringActivity(null)
	}

	/* ---------------------------------------------------------
	 *
	 * Internal methods
	 *
	 * --------------------------------------------------------- */

	/**
	 * Register or remove application activity
	 *
	 * @param activity Target activity
	 */
	private fun setMonitoringActivity(activity: Activity?) {
		application.tryCast<IApplication> { it.setCurrentActivity(activity) }
	}

}
