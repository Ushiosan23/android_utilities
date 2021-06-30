package com.github.ushiosan23.android_utilities.android.activity

import androidx.viewbinding.ViewBinding

interface IActivityBinding<T : ViewBinding> : IActivityCompat {
	val bindingClass: Class<T>
}
