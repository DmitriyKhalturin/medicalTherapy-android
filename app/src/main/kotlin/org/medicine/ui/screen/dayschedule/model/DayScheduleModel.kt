package org.medicine.ui.screen.dayschedule.model

import org.medicine.schedule.data.Deal
import org.medicine.schedule.data.Medicine
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 30.01.2022 21:52.
 */
data class DayScheduleModel(
  val name: String,
  val date: LocalDate,
  val medicines: List<Medicine>,
  val deals: List<Deal>,
)
