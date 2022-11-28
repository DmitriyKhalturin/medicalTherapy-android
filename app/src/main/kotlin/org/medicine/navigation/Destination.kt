package org.medicine.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 19:26.
 */
sealed class Destination(val route: Route) : Parcelable {
  @Parcelize object Overview : Destination(Route.Overview)
  @Parcelize data class TherapySchedule(val therapyId: Long) : Destination(Route.TherapySchedule)
  @Parcelize data class TherapyForm(val therapyId: Long? = null) : Destination(Route.TherapyForm)
  @Parcelize data class MedicineForm(val therapyId: Long, val medicineId: Long? = null) : Destination(Route.MedicineForm)
  @Parcelize data class DealForm(val therapyId: Long, val dealId: Long? = null) : Destination(Route.DealForm)
  @Parcelize data class DaySchedule(val therapyId: Long, val date: LocalDate) : Destination(Route.DaySchedule)
  @Parcelize object ApplicationInfo : Destination(Route.ApplicationInfo)
}
