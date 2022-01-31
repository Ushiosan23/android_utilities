package com.github.ushiosan23.android_utilities.extensions

import android.content.Context
import android.view.Gravity
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.TooltipCompat
import com.github.ushiosan23.android_utilities.R
import com.google.android.material.snackbar.Snackbar

/* ---------------------------------------------------------
 *
 * Snackbar
 *
 * --------------------------------------------------------- */

/**
 * Make view snackbar.
 *
 * @param resId Resource string id
 * @param duration Snack duration
 * @return Return configured snack bar
 */
fun View.makeSnack(@StringRes resId: Int, duration: Int): Snackbar =
	Snackbar.make(this, resId, duration)

/**
 * Make view snackbar.
 *
 * @param text Snack text content
 * @param duration Snack duration
 * @return Return configured snack bar
 */
fun View.makeSnack(text: CharSequence, duration: Int): Snackbar =
	Snackbar.make(this, text, duration)

/* ---------------------------------------------------------
 *
 * Tooltip
 *
 * --------------------------------------------------------- */

/**
 * Set view tooltip
 *
 * @param data Target tooltip data
 */
fun View.attachTooltip(data: CharSequence) =
	TooltipCompat.setTooltipText(this, data)

/**
 * Set view tooltip
 *
 * @param ctx Application context
 * @param resId String resource id
 * @param format String format arguments
 */
fun View.attachTooltip(ctx: Context, @StringRes resId: Int, vararg format: Any?) {
	val data = ctx.getString(resId, *format)
	attachTooltip(data)
}

/* ---------------------------------------------------------
 *
 * PopupMenu
 *
 * --------------------------------------------------------- */

/**
 * Set view popup menu
 *
 * @param ctx Activity context
 * @param menuId Resource menu id
 * @param gravity Menu gravity
 * @param popupStyleAttr An attribute in the current theme that contains a reference to a style resource that supplies
 * default values for the popup window.
 * @param style A resource identifier of a style resource that supplies default values for the popup window, used only if
 * popupStyleAttr is 0 or can not be found in the theme.
 * @return Returns a configured popup menu
 */
fun View.attachPopupMenu(
	ctx: Context,
	@MenuRes menuId: Int,
	gravity: Int? = null,
	@AttrRes popupStyleAttr: Int? = null,
	@StyleRes style: Int? = null
): PopupMenu {
	val popup = PopupMenu(
		ctx,
		this,
		gravity ?: Gravity.NO_GRAVITY,
		popupStyleAttr ?: R.attr.popupMenuStyle,
		style ?: 0
	)

	popup.menuInflater.inflate(menuId, popup.menu)
	return popup
}
