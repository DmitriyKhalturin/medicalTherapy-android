package org.medicine.tools.time

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 10.01.2022 11:57.
 */

fun Long.toLocalDate(): LocalDate =
  Instant.ofEpochMilli(this)
    .atZone(ZoneId.systemDefault())
    .toLocalDate()

fun LocalDate.toMilliseconds(): Long =
  this.atStartOfDay(ZoneId.systemDefault())
    .toInstant()
    .toEpochMilli()

private const val MILLISECONDS_IN_DAY =  24 * 60 * 60 * 1_000

fun Long.plusDay(): Long = this + MILLISECONDS_IN_DAY

fun Long.minusDay(): Long = this - MILLISECONDS_IN_DAY
