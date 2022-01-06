package org.medicine.tools

import org.junit.Test
import java.time.LocalDate

class StandardKtTest {

  @Test
  fun `Test daysUntil method`() {
    val five = 5L
    val now = LocalDate.now()
    val afterFiveDays = now.plusDays(five)

    assert(now.daysUntil(afterFiveDays).toLong() == five)
    assert(afterFiveDays.daysUntil(now).toLong() == five.unaryMinus())
    assert(now.daysUntil(now) == 0)
  }

  @Test
  fun `Test betweenDays method`() {
    val five = 5
    val now = LocalDate.now()
    val afterFiveDays = now.plusDays(five.toLong())

    assert(now.betweenDays(afterFiveDays) == five)
  }

  @Test
  fun `Test converter ClosedRange to List`() {
    val startDate = LocalDate.now()
    val endDate = startDate.plusDays(6)
    val oneWeekRange = startDate..endDate

    val oneWeekList = oneWeekRange.toList()

    assert(oneWeekList.size == 7)
  }

  @Test
  fun `Test converter from List to HashMap`() {
    val list = listOf("A", "B", "C")
    val map = list.toHashMap { it }

    assert(map.size == list.size)
  }
}
