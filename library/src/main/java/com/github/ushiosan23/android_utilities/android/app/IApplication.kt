package com.github.ushiosan23.android_utilities.android.app

import android.app.Activity
import androidx.fragment.app.Fragment

interface IApplication {

	/* ---------------------------------------------------------
	 *
	 * Activity
	 *
	 * --------------------------------------------------------- */

	/**
	 * Determine current application activity
	 *
	 * @param activity Target activity
	 */
	fun setCurrentActivity(activity: Activity?)

	/* ---------------------------------------------------------
	 *
	 * Fragments
	 *
	 * --------------------------------------------------------- */

	/**
	 * Add fragment to list of fragments
	 *
	 * @param fragment Target fragment
	 */
	fun addFragment(fragment: Fragment)

	/**
	 * Remove fragment from list if exists
	 *
	 * @param fragment Target fragment
	 */
	fun removeFragment(fragment: Fragment)

}
