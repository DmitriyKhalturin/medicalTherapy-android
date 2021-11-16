package org.medicine.ui.screen.overview.model

import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 7:13.
 */
data class TherapyModel(
  val id: Long,
  val name: String,
  val description: String?,
  val startDate: LocalDate,
  val endDate: LocalDate,
)
