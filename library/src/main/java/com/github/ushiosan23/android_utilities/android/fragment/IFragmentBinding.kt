package com.github.ushiosan23.android_utilities.android.fragment

import android.os.Bundle
import androidx.viewbinding.ViewBinding

interface IFragmentBinding<T : ViewBinding> {
	val bindingClass: Class<T>
	fun onFragmentLoaded(savedInstanceState: Bundle?)
}
