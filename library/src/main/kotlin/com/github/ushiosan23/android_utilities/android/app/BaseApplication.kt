package com.github.ushiosan23.android_utilities.android.app

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.github.ushiosan23.android_utilities.utilities.logInfo

abstract class BaseApplication : Application(), IApplication {

	/* ---------------------------------------------------------
	 *
	 * Properties
	 *
	 * --------------------------------------------------------- */

	/**
	 * List with all fragments
	 */
	protected var currentApplicationFragments: MutableList<Fragment> = mutableListOf()
		private set

	/**
	 * Current app activity
	 */
	@Volatile
	protected var currentApplicationActivity: Activity? = null
		private set

	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

	/**
	 * Determine current application activity
	 *
	 * @param activity Target activity
	 */
	@Synchronized
	override fun setCurrentActivity(activity: Activity?) {
		// Set activity
		currentApplicationActivity = activity
		// Show log
		logInfo("$this", currentApplicationActivity)
	}

	/**
	 * Add fragment to list of fragments
	 *
	 * @param fragment Target fragment
	 */
	override fun addFragment(fragment: Fragment) {
		// Add fragment to list
		currentApplicationFragments.add(fragment)
	}

	/**
	 * Remove fragment from list if exists
	 *
	 * @param fragment Target fragment
	 */
	override fun removeFragment(fragment: Fragment) {
		// Check if fragment exists
		if (currentApplicationFragments.contains(fragment)) return
		// Remove fragment
		currentApplicationFragments.remove(fragment)
	}

}
