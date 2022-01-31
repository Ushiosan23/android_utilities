package com.github.ushiosan23.android_utilities.extensions

import android.util.DisplayMetrics
import android.util.TypedValue

/**
 * Convert any number to complex value dimension.
 *
 * @param metrics Context display metrics
 * @param dimension Target complex unit
 * @return Return a number with applied dimension
 * @see TypedValue
 */
fun Number.toComplexDimension(metrics: DisplayMetrics, dimension: Int): Number =
	TypedValue.applyDimension(dimension, this.toFloat(), metrics)

/**
 * Convert any number to DPI.
 *
 * @param metrics Context display metrics
 * @return Return a number with applied dimension
 * @see TypedValue
 */
fun Number.toDIP(metrics: DisplayMetrics): Number =
	toComplexDimension(metrics, TypedValue.COMPLEX_UNIT_DIP)

/**
 * Convert any number to DPI.
 *
 * @param metrics Context display metrics
 * @return Return a number with applied dimension
 * @see TypedValue
 */
fun Number.ftoDIP(metrics: DisplayMetrics): Float =
	toDIP(metrics).toFloat()

/**
 * Convert any number to SP.
 *
 * @param metrics Context display metrics
 * @return Return a number with applied dimension
 * @see TypedValue
 */
fun Number.toSP(metrics: DisplayMetrics): Number =
	toComplexDimension(metrics, TypedValue.COMPLEX_UNIT_SP)

/**
 * Convert any number to SP.
 *
 * @param metrics Context display metrics
 * @return Return a number with applied dimension
 * @see TypedValue
 */
fun Number.ftoSP(metrics: DisplayMetrics): Float =
	toSP(metrics).toFloat()
