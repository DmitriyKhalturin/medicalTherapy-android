package org.medicine.ui.screen.dealform.model

import java.time.LocalDateTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:11.
 */
data class DealFormModel(
  val name: String,
  val description: String,
  val dateTime: LocalDateTime,
  val failedFields: FailedFields,
) {

  data class FailedFields(
    val name: Boolean = false,
    val description: Boolean = false,
    val date: Boolean = false,
    val time: Boolean = false,
  ) {

    val has:Boolean
      get() = name || description || date || time
  }
}
