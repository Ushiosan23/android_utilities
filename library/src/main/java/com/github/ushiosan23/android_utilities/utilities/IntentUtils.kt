package com.github.ushiosan23.android_utilities.utilities

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract

/**
 * Create intent to select external storage file.
 *
 * @param typeEl MimeType required. By default the MimeType is __&#42;&#47;&#42;__ to select any file
 * @param uri Default open location. This argument is valid only in [Build.VERSION_CODES.O] or above
 * @return Return an intent configured to launch
 */
fun fileChooserIntent(typeEl: String = "*/*", uri: Uri? = null) =
	Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
		addCategory(Intent.CATEGORY_OPENABLE)
		type = typeEl
		// special attributes
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
		}
	}

/**
 * Create intent to select external storage directory.
 *
 * @param uri Default open location. This argument is valid only in [Build.VERSION_CODES.O] or above
 * @return Return an intent configured to launch
 */
fun directoryChooserIntent(uri: Uri? = null) =
	Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			putExtra(DocumentsContract.EXTRA_INITIAL_URI, uri)
		}
	}
