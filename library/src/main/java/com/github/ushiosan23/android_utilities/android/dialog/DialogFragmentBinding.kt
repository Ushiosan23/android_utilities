package com.github.ushiosan23.android_utilities.android.dialog

import android.app.Dialog
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.github.ushiosan23.android_utilities.data.MutableSize
import com.github.ushiosan23.android_utilities.utilities.inflateBinding

/**
 * Custom dialog. Also can attach view binding layout.
 * If you want to use this class, you need to enable android features:
 *
 * ```kotlin
 * buildFeatures {
 *   viewBinding = true
 * }
 * ```
 *
 * @param T Layout binding class
 * @see DialogFragment
 * @see IDialogFragmentBinding
 */
abstract class DialogFragmentBinding<T : ViewBinding> : DialogFragment(), IDialogFragmentBinding<T> {

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

	/**
	 * Property inflate fragment and attach to parent
	 */
	protected open val attachToRoot: Boolean = false

	/**
	 * Determine if dialog use full screen size.
	 * You can use this with `inset` resource.
	 */
	protected open val fullContent: Boolean = true

	/**
	 * Target dialog theme.
	 */
	protected open val dialogTheme: Int? = null

	/**
	 * Determine the dialog position.
	 *
	 * @see Gravity
	 */
	protected open val dialogGravity: Int = Gravity.CENTER

	/**
	 * Default dialog size.
	 * This size depends of [fullContent] param and can be change in [onDialogConfiguration] method
	 */
	private val dialogSize: MutableSize
		get() = MutableSize(
			if (fullContent) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT,
			if (fullContent) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
		)

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
	 * Called to have the fragment instantiate its user interface view.
	 * This is optional, and non-graphical fragments can return null.
	 * This will be called between [onCreate] and [onViewCreated].
	 *
	 * @param inflater The LayoutInflater object that can be used to inflate
	 * any views in the fragment
	 * @param container If non-null, this is the parent view that the fragment's
	 * UI should be attached to.
	 * @param savedInstanceState If non-null, this fragment is being re-constructed
	 * from a previous saved state as given here.
	 *
	 * @return Return the view for fragment binding or `null` if the fragment contains any error
	 */
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		// Initialize binding
		internalBinding = inflateBinding(
			bindingClass,
			inflater,
			container,
			attachToRoot
		)
		// Check if binding was created
		if (internalBinding == null) return null
		// Return a view
		return binding.root
	}

	/**
	 * Called immediately after [onCreateView] method.
	 *
	 * @param view Fragment dialog view
	 * @param savedInstanceState If non-null, this fragment is being re-constructed
	 * from a previous saved state as given here.
	 */
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		onFragmentLoaded(savedInstanceState)
	}

	/**
	 * This method will be called after [onCreate] and immediately before [onCreateView].
	 * The default implementation simply instantiates and returns a Dialog class.
	 *
	 * @param savedInstanceState The last saved instance state of the Fragment, or null if this is a freshly created Fragment.
	 * @return Return a new dialog instance.
	 */
	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		// Create custom dialog and use selected theme.
		val tmpDialog = Dialog(requireContext(), dialogTheme ?: theme)

		// Configure fragment dialog
		tmpDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
		// Configure dialog size
		onDialogConfiguration(
			tmpDialog,
			requireActivity().resources.displayMetrics,
			dialogSize,
			dialogGravity
		)
		// Return configured dialog
		return tmpDialog
	}

	/**
	 * Called when the fragment is visible to the user and actively running.
	 * @see androidx.appcompat.app.AppCompatActivity.onResume
	 */
	override fun onResume() {
		super.onResume()
		// Normally is used to configure dialog after creation.
		// "onDialogConfiguration" is called here because the dialog is not configured when it is created and has to be called
		// once the activity continues the flow after the dialog is displayed.
		dialog?.let {
			onDialogConfiguration(
				it,
				requireActivity().resources.displayMetrics,
				dialogSize,
				dialogGravity
			)
		}
	}

	/**
	 * Implement interface method.
	 *
	 * @param savedInstanceState The last saved instance state of the Fragment, or null if this is a freshly created Fragment.
	 */
	override fun onFragmentLoaded(savedInstanceState: Bundle?) {
		onDialogFragmentLoaded()
	}

	/**
	 * Called after [onCreateDialog] and [onResume] methods.
	 * You can override this method to change dialog configuration.
	 *
	 * @param d Target dialog to configure
	 * @param m Device display metrics
	 * @param s Current dialog size
	 * @param g Current Dialog gravity
	 */
	open fun onDialogConfiguration(d: Dialog, m: DisplayMetrics, s: MutableSize, g: Int) {
		if (!fullContent) return
		d.window?.setLayout(
			s.width,
			s.height
		)
		d.window?.setGravity(g)
	}

}
