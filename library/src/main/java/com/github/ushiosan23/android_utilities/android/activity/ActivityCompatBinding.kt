package com.github.ushiosan23.android_utilities.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.utilities.inflateBinding

/**
 * Custom activity compact. Also can attach view binding layout.
 * If you want to use this class, you need to enable android features:
 *
 * ```kotlin
 * buildFeatures {
 *   viewBinding = true
 * }
 * ```
 *
 * @param T Layout binding class
 * @see AppCompatActivity
 * @see IActivityBinding
 */
abstract class ActivityCompatBinding<T : ViewBinding> : AppCompatActivity(), IActivityBinding<T> {

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
	 * Attach to activity content and initialize all fragments.
	 * This method use reflexion to inflate view binding.
	 *
	 * @param savedInstanceState If the activity is being re-initialized after
	 * previously being shut down then this Bundle contains the data it most
	 * recently supplied in [onSaveInstanceState]. ***Note: Otherwise it is null.***
	 * @see inflateBinding
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		// Initialize binding
		internalBinding = inflateBinding(
			bindingClass,
			layoutInflater
		)
		// Check if binding is an error
		if (internalBinding == null) return
		// Initialize binding
		setContentView(binding.root)
		// Call only if activity is loaded
		onActivityLoaded(savedInstanceState)
	}

}
