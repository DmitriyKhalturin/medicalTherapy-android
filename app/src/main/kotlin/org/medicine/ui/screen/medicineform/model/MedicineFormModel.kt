package org.medicine.ui.screen.medicineform.model

import org.medicine.schedule.data.Medicine
import org.medicine.source.database.entity.TherapyEntity
import org.medicine.tools.EMPTY_STRING
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:20.
 */
data class MedicineFormModel(
  val therapyId: Long,
  val name: String,
  val description: String,
  val type: Medicine.Type,
  val dosage: String,
  val startDate: LocalDate,
  val endDate: LocalDate,
  val times: List<LocalTime>,
  val minValidInclusiveDate: LocalDate,
  val maxValidInclusiveDate: LocalDate,
  val failedFields: FailedFields,
) {

  companion object {

    private const val BETWEEN_START_END_MEDICINE_DATE = 0L

    fun empty(therapy: TherapyEntity) = therapy.run {
      MedicineFormModel(
        id,
        EMPTY_STRING,
        EMPTY_STRING,
        Medicine.Type.PILLS,
        EMPTY_STRING,
        LocalDate.now(),
        LocalDate.now().plusDays(BETWEEN_START_END_MEDICINE_DATE),
        emptyList(),
        startDate,
        endDate,
        FailedFields(),
      )
    }
  }

  data class FailedFields(
    val name: Boolean = false,
    val description: Boolean = false,
    val date: Boolean = false,
    val times: Boolean = false,
  ) {

    val has: Boolean
      get() = name || description || date || times
  }
}
