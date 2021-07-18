package com.github.ushiosan23.app.dialogs

import android.app.Dialog
import android.util.DisplayMetrics
import android.view.Gravity
import com.github.ushiosan23.android_utilities.android.dialog.DialogFragmentBinding
import com.github.ushiosan23.android_utilities.extensions.resolveClass
import com.github.ushiosan23.android_utilities.data.MutableSize
import com.github.ushiosan23.app.R
import com.github.ushiosan23.app.databinding.DialogExampleBinding

class ExampleDialog : DialogFragmentBinding<DialogExampleBinding>() {

	override val bindingClass: Class<DialogExampleBinding> = resolveClass()
	override val dialogTheme: Int = R.style.Theme_Experiments_Dialogs_Base
	override val dialogGravity: Int = Gravity.BOTTOM

	override fun onDialogFragmentLoaded() {}

	override fun onDialogConfiguration(d: Dialog, m: DisplayMetrics, s: MutableSize, g: Int) {
		s.height = (m.heightPixels * 0.5).toInt()
		super.onDialogConfiguration(d, m, s, g)
	}

}
