package org.medicine.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 19:26.
 */
sealed class Destination(val route: Route) {
  @Parcelize object Overview : Destination(Route.Overview), Parcelable
  @Parcelize data class TherapySchedule(val therapyId: Long) : Destination(Route.TherapySchedule), Parcelable
  @Parcelize data class TherapyForm(val therapyId: Long? = null) : Destination(Route.TherapyForm), Parcelable
  @Parcelize data class MedicineForm(val medicineId: Long? = null) : Destination(Route.MedicineForm), Parcelable
  @Parcelize data class DealForm(val dealId: Long? = null) : Destination(Route.DealForm), Parcelable
  @Parcelize data class DaySchedule(val date: LocalDate) : Destination(Route.DaySchedule), Parcelable
  @Parcelize object ApplicationInfo : Destination(Route.ApplicationInfo), Parcelable
}
