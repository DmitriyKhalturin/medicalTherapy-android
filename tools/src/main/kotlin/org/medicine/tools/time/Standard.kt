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

// TODO: refactoring this hack!
fun LocalDate.toLong(): Long =
  this.plusDays(1)
    .atStartOfDay(ZoneId.systemDefault())
    .toInstant()
    .toEpochMilli()
