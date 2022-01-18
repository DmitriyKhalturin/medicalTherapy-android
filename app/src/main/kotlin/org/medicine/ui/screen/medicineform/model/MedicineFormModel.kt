package org.medicine.ui.screen.medicineform.model

import org.medicine.schedule.data.Medicine
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:20.
 */
data class MedicineFormModel(
  val name: String,
  val description: String,
  val type: Medicine.Type,
  val dosage: String,
  val startDate: LocalDate,
  val endDate: LocalDate,
  val times: List<LocalTime>,
  val failedFields: FailedFields,
) {

  data class FailedFields(
    val name: Boolean = false,
    val description: Boolean = false,
    val dosage: Boolean = false,
    val startDate: Boolean = false,
    val endDate: Boolean = false,
    val times: Boolean = false,
  ) {

    val has: Boolean
      get() = name || description || dosage || startDate || endDate || times
  }
}
