package com.github.ushiosan23.android_utilities.android.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.utilities.inflateBinding

abstract class ActivityCompatBinding<T : ViewBinding> : AppCompatActivity(), IActivityBinding<T> {

	/* ---------------------------------------------------------
	 *
	 * Properties
	 *
	 * --------------------------------------------------------- */

	@Suppress("MemberVisibilityCanBePrivate")
	protected val binding: T
		get() = internalBinding!!

	/* ---------------------------------------------------------
	 *
	 * Internal properties
	 *
	 * --------------------------------------------------------- */

	private var internalBinding: T? = null

	/* ---------------------------------------------------------
	 *
	 * Methods
	 *
	 * --------------------------------------------------------- */

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

	protected inline fun <reified V> resolveClass(): Class<V> = V::class.java

}
