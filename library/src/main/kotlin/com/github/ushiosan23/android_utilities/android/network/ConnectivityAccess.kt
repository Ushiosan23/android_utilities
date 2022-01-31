package com.github.ushiosan23.android_utilities.android.network

import android.Manifest
import android.content.Context
import android.net.Network
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission
import com.github.ushiosan23.android_utilities.extensions.connectivityManager

object ConnectivityAccess {

	/**
	 * Check if network instance has valid connection (valid ip, valid dns, valid connection & internet)
	 *
	 * @param ctx Application/Activity context
	 * @param network The network instance to check
	 * @return `true` if connection is valid and `false` otherwise
	 */
	@JvmStatic
	@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
	fun checkConnectivity(ctx: Context, network: Network): Boolean {
		val manager = ctx.connectivityManager
		val capabilities = manager.getNetworkCapabilities(network)

		return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
	}

	/**
	 * Check if active network instance has valid connection (valid ip, valid dns, valid connection & internet)
	 *
	 * @param ctx Application/Activity context
	 * @return `true` if connection is valid and `false` otherwise
	 */
	@JvmStatic
	@RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
	fun checkConnectivity(ctx: Context): Boolean {
		val manager = ctx.connectivityManager
		val network = manager.activeNetwork ?: return false

		return checkConnectivity(ctx, network)
	}

}
