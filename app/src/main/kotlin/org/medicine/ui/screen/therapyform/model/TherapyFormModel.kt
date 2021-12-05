package org.medicine.ui.screen.therapyform.model

import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 05.12.2021 23:11.
 */
data class TherapyFormModel(
  val name: String,
  val description: String,
  val startDate: LocalDate,
  val endDate: LocalDate,
)
