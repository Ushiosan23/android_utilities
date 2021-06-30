package com.github.ushiosan23.android_utilities.extensions

import android.util.DisplayMetrics
import android.util.TypedValue

fun Number.toComplexDimension(metrics: DisplayMetrics, dimension: Int): Number =
	TypedValue.applyDimension(dimension, this.toFloat(), metrics)

fun Number.toDIP(metrics: DisplayMetrics): Number =
	toComplexDimension(metrics, TypedValue.COMPLEX_UNIT_DIP)

fun Number.ftoDIP(metrics: DisplayMetrics): Float =
	toDIP(metrics).toFloat()

fun Number.toSP(metrics: DisplayMetrics): Number =
	toComplexDimension(metrics, TypedValue.COMPLEX_UNIT_SP)

fun Number.ftoSP(metrics: DisplayMetrics): Float =
	toSP(metrics).toFloat()
