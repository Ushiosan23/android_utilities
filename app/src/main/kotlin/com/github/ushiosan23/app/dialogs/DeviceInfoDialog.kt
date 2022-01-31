package com.github.ushiosan23.app.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.util.DisplayMetrics
import com.github.ushiosan23.android_utilities.android.dialog.DialogFragmentBinding
import com.github.ushiosan23.android_utilities.data.DeviceInfo
import com.github.ushiosan23.android_utilities.data.MutableSize
import com.github.ushiosan23.android_utilities.extensions.resolveClass
import com.github.ushiosan23.app.databinding.DialogInformationBinding

class DeviceInfoDialog : DialogFragmentBinding<DialogInformationBinding>() {

	override val bindingClass: Class<DialogInformationBinding> = resolveClass()

	@SuppressLint("SetTextI18n")
	override fun onDialogFragmentLoaded() {
		binding.infoAndroidVersion.text = "${DeviceInfo.sdkVersion}"
		binding.infoDeviceManufacturer.text = DeviceInfo.manufacturer
		binding.infoDeviceModel.text = DeviceInfo.deviceModel
		binding.infoDeviceArch.text = "${DeviceInfo.archSupportedABIS.toList()}"
		binding.infoDeviceCameras.text =
			"(${DeviceInfo.HardWare.hasAnyCamera(requireContext())}) ${DeviceInfo.HardWare.totalCameras(requireContext())}"
		binding.infoDeviceBiometric.text = "${DeviceInfo.HardWare.hasBiometricHardware(requireContext())}"
	}

	override fun onDialogConfiguration(d: Dialog, m: DisplayMetrics, s: MutableSize, g: Int) {
		s.height = m.heightPixels / 2
		super.onDialogConfiguration(d, m, s, g)
	}

}
