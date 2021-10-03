package com.github.ushiosan23.app.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import com.github.ushiosan23.android_utilities.android.activity.ActivityCompatBinding
import com.github.ushiosan23.android_utilities.android.time.TimeUnits
import com.github.ushiosan23.android_utilities.android.time.TimerTask
import com.github.ushiosan23.android_utilities.extensions.makeSnack
import com.github.ushiosan23.android_utilities.extensions.makeToast
import com.github.ushiosan23.android_utilities.extensions.resolveClass
import com.github.ushiosan23.app.R
import com.github.ushiosan23.app.databinding.ActivityMainBinding
import com.github.ushiosan23.app.dialogs.DeviceInfoDialog
import com.github.ushiosan23.app.dialogs.ExampleDialog
import com.google.android.material.snackbar.Snackbar

class ActivityMain : ActivityCompatBinding<ActivityMainBinding>() {

	override val bindingClass: Class<ActivityMainBinding> = resolveClass()

	override fun onActivityLoaded(savedInstanceState: Bundle?) {
		binding.exampleButton.setOnClickListener(this::onButtonClicked)
		binding.exampleThemeToggle.setOnClickListener(this::onToggleThemeClicked)
		binding.exampleButtonDialog.setOnClickListener(this::onButtonDialogClicked)
		binding.hardwareInformationButton.setOnClickListener(this::onButtonInfoDialogClicked)
		binding.timerTaskButton.setOnClickListener(this::onButtonTimerTaskClicked)

		// Change theme
		val currentNightMode = resources
			.configuration
			.uiMode and Configuration.UI_MODE_NIGHT_MASK
		val isNightModeActive = currentNightMode == Configuration.UI_MODE_NIGHT_YES
		val drawable = AppCompatResources.getDrawable(
			this,
			if (isNightModeActive) R.drawable.ic_moon else R.drawable.ic_sun
		)
		binding.exampleThemeToggle.setImageDrawable(drawable)
	}

	/* ---------------------------------------------------------
	 *
	 * Events
	 *
	 * --------------------------------------------------------- */

	private fun onButtonClicked(v: View) {
		val snack = v.makeSnack(
			"Hello, World!",
			Snackbar.LENGTH_INDEFINITE
		)

		snack.setAction("Ok") {
			snack.dismiss()
		}.show()
	}

	@Suppress("UNUSED_PARAMETER")
	private fun onToggleThemeClicked(v: View) {
		// Change theme
		val currentNightMode = resources
			.configuration
			.uiMode and Configuration.UI_MODE_NIGHT_MASK
		val isNightModeActive = currentNightMode == Configuration.UI_MODE_NIGHT_YES

		makeToast(
			"Night Mode ${!isNightModeActive}",
			Toast.LENGTH_SHORT
		).show()


		AppCompatDelegate.setDefaultNightMode(
			if (isNightModeActive) AppCompatDelegate.MODE_NIGHT_NO else AppCompatDelegate.MODE_NIGHT_YES
		)
	}

	@Suppress("UNUSED_PARAMETER")
	private fun onButtonDialogClicked(v: View) {
		val dialog = ExampleDialog()
		dialog.show(supportFragmentManager, "ExampleDialog")
	}

	@Suppress("UNUSED_PARAMETER")
	private fun onButtonInfoDialogClicked(v: View) {
		val dialog = DeviceInfoDialog()
		dialog.show(supportFragmentManager, "DeviceInfoDialog")
	}

	@Suppress("UNUSED_PARAMETER")
	private fun onButtonTimerTaskClicked(v: View) {
		var seconds = 1
		var task: TimerTask? = null

		task = TimerTask.generateTimerTask(TimeUnits.getSeconds(3)) {
			if (seconds >= 3)
				task!!.stop()

			runOnUiThread {
				makeToast("Timer Task", Toast.LENGTH_SHORT)
					.show()
			}
			seconds++
		}
		task.start(false)
	}

}
