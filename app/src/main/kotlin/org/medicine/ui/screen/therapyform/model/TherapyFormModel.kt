package org.medicine.ui.screen.therapyform.model

import org.medicine.tools.EMPTY_STRING
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
  val failedFields: FailedFields,
) {

  companion object {

    private const val BETWEEN_START_END_THERAPY_DATE = 7L

    fun empty() =
      TherapyFormModel(
        EMPTY_STRING,
        EMPTY_STRING,
        LocalDate.now(),
        LocalDate.now().plusDays(BETWEEN_START_END_THERAPY_DATE),
        FailedFields(),
      )
  }

  data class FailedFields(
    val name: Boolean = false,
    val description: Boolean = false,
    val date: Boolean = false,
  ) {

    val has: Boolean
      get() = name || date
  }
}
