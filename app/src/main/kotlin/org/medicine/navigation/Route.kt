package org.medicine.navigation

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:20.
 */
sealed class Route {

  companion object {

    val Route.name: String
      get() = this.toString()
  }

  object Overview : Route()
  object TherapySchedule : Route()
  object TherapyForm : Route()
  object MedicineForm : Route()
  object DealForm : Route()
  object DaySchedule : Route()
  object ApplicationInfo : Route()
}
