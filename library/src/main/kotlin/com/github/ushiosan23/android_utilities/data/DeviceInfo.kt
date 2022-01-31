package com.github.ushiosan23.android_utilities.data

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE
import androidx.biometric.BiometricManager.BIOMETRIC_SUCCESS

/**
 * Object used to check device information.
 */
object DeviceInfo {

	/**
	 * Get current device sdk version
	 */
	@JvmStatic
	val sdkVersion: Int
		get() = Build.VERSION.SDK_INT

	/**
	 * Get manufacturer of the device.
	 */
	@JvmStatic
	val manufacturer: String
		get() = Build.MANUFACTURER

	/**
	 * Get the device model.
	 */
	@JvmStatic
	val deviceModel: String
		get() = Build.MODEL

	/**
	 * All device arch supported.
	 */
	@JvmStatic
	val archSupportedABIS: Array<out String>
		get() = Build.SUPPORTED_ABIS

	/**
	 * Get all device x32 bit architecture support
	 */
	@JvmStatic
	val archSupportedX32: Array<out String>
		get() = Build.SUPPORTED_32_BIT_ABIS

	/**
	 * Get all device x64 bit architecture support
	 */
	@JvmStatic
	val archSupportedX64: Array<out String>
		get() = Build.SUPPORTED_64_BIT_ABIS

	/**
	 * Object used to check device hardware.
	 */
	object HardWare {

		/**
		 * Check if device support contains any camera.
		 *
		 * @param context Application context
		 * @return Return `true` if device has 1 or more cameras and `false` otherwise
		 */
		@JvmStatic
		fun hasAnyCamera(context: Context): Boolean =
			context.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)

		/**
		 * Return the list of currently connected camera devices by identifier, including
		 * cameras that may be in use by other camera API clients.
		 *
		 * @param context Application context
		 * @return Returns all cameras on the device. May be 0 if not found any camera.
		 */
		@JvmStatic
		fun totalCameras(context: Context): Int {
			val cm = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
			return cm.cameraIdList.size
		}

		/**
		 * Check if device has biometric authentication hardware.
		 *
		 * @param context Application context
		 * @return Return `true` if device has _fingerprint_ or _face-detection_ hardware or `false` otherwise
		 */
		@JvmStatic
		fun hasBiometricHardware(context: Context): Boolean {
			val bm = BiometricManager.from(context)
			val deviceValues = when {
				Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> BIOMETRIC_STRONG or DEVICE_CREDENTIAL
				Build.VERSION.SDK_INT == Build.VERSION_CODES.Q -> BIOMETRIC_STRONG
				else -> DEVICE_CREDENTIAL
			}

			return when (bm.canAuthenticate(deviceValues)) {
				BIOMETRIC_SUCCESS -> true
				BIOMETRIC_ERROR_NO_HARDWARE -> false
				else -> false
			}
		}

	}

}
