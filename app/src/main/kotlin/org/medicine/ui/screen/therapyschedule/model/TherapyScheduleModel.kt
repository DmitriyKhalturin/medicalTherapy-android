package org.medicine.ui.screen.therapyschedule.model

import org.medicine.schedule.data.Deal
import org.medicine.schedule.data.Medicine
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 11.01.2022 20:43.
 */
data class TherapyScheduleModel(
  val id: Long,
  val name: String,
  val startDate: LocalDate,
  val endDate: LocalDate,
  val medicines: List<Medicine>,
  val deals: List<Deal>,
)
