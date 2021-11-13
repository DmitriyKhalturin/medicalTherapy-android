package org.medicine.tools

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 08.11.2021 2:49.
 */

const val EMPTY_STRING = ""

fun LocalDate.daysUntil(future: LocalDate) = this.until(future, ChronoUnit.DAYS).toInt()

fun ClosedRange<LocalDate>.toList(): List<LocalDate> {
  var item = start
  val result = mutableListOf<LocalDate>()

  do {
    result.add(item)
    item = item.plusDays(1)
  } while (item <= this.endInclusive)

  return result.toList()
}

inline fun <reified T, K> List<T>.toHashMap(keyGenerator: (T) -> K): HashMap<K, T> {
  val result = hashMapOf<K, T>()

  this.forEach {
    result[keyGenerator(it)] = it
  }

  return result
}
