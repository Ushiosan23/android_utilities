package com.github.ushiosan23.android_utilities.android.activity

import android.os.Bundle

/**
 * Interface model to use in activity compact
 */
interface IActivityCompat {

	/**
	 * Called when activity was loaded.
	 *
	 * @param savedInstanceState If the activity is being re-initialized after
	 * previously being shut down then this Bundle contains the data it most
	 * recently supplied in [androidx.appcompat.app.AppCompatActivity.onSaveInstanceState]. ***Note: Otherwise it is null.***
	 */
	fun onActivityLoaded(savedInstanceState: Bundle?)

}
