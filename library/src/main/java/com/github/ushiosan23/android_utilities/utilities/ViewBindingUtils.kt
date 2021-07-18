package com.github.ushiosan23.android_utilities.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * Inflate layout binding class. This method is used to inflate activities.
 *
 * @param clazz Target layout class to inflate
 * @param inflater Activity or fragment inflater
 * @return Returns an instance of target class binding
 */
@Suppress("UNCHECKED_CAST")
fun <T : ViewBinding> inflateBinding(clazz: Class<T>, inflater: LayoutInflater): T? {
	val method = try {
		clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
	} catch (err: Exception) {
		return null
	}

	return try {
		method.invoke(null, inflater) as T
	} catch (err: Exception) {
		logError("INFLATE_ERROR", err)
		null
	}
}

/**
 * Inflate layout binding class. This method is used to inflate fragments.
 *
 * @param clazz Target layout class to inflate
 * @param inflater Activity or fragment inflater
 * @param parent Target parent
 * @param attachToParent Property inflate fragment and attach to parent
 * @return Returns an instance of target class binding
 */
@Suppress("UNCHECKED_CAST")
fun <T : ViewBinding> inflateBinding(
	clazz: Class<T>,
	inflater: LayoutInflater,
	parent: ViewGroup?,
	attachToParent: Boolean
): T? {
	val method = try {
		clazz.getDeclaredMethod(
			"inflate",
			LayoutInflater::class.java,
			ViewGroup::class.java,
			Boolean::class.java
		)
	} catch (err: Exception) {
		return null
	}

	return try {
		method.invoke(null, inflater, parent, attachToParent) as T
	} catch (err: Exception) {
		logError("INFLATE_ERROR", err)
		null
	}
}
