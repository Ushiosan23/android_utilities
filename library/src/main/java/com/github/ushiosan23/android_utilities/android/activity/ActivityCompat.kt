package com.github.ushiosan23.android_utilities.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Custom activity.
 * This activity load layout automatically, only you need to define [contentView] property to load target layout.
 *
 * @see AppCompatActivity
 * @see IActivityCompat
 */
abstract class ActivityCompat : AppCompatActivity(), IActivityCompat {

	/* ---------------------------------------------------------
	 *
	 * Properties
	 *
	 * --------------------------------------------------------- */

	/**
	 * Target layout id.
	 * This property is required to create the activity.
	 */
	protected abstract val contentView: Int

	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

	/**
	 * Attach to activity content and initialize all fragments.
	 *
	 * @param savedInstanceState If the activity is being re-initialized after
	 * previously being shut down then this Bundle contains the data it most
	 * recently supplied in [onSaveInstanceState]. ***Note: Otherwise it is null.***
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		// Set defined content view resource id
		setContentView(contentView)
		// Called only if activity is loaded
		onActivityLoaded(savedInstanceState)
	}

}
