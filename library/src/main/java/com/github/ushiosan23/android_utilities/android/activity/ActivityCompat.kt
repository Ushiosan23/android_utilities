package com.github.ushiosan23.android_utilities.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class ActivityCompat : AppCompatActivity(), IActivityCompat {

	/* ---------------------------------------------------------
	 *
	 * Properties
	 *
	 * --------------------------------------------------------- */

	protected abstract val contentView: Int

	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(contentView)
		// Called only if activity is loaded
		onActivityLoaded(savedInstanceState)
	}

}
