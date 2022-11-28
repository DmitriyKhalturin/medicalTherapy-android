package org.medicine.ui.screen.dealform.model

import org.medicine.source.database.entity.TherapyEntity
import org.medicine.tools.EMPTY_STRING
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:11.
 */
data class DealFormModel(
  val therapyId: Long,
  val name: String,
  val description: String,
  val date: LocalDate,
  val time: LocalTime,
  val minValidInclusiveDate: LocalDate,
  val maxValidInclusiveDate: LocalDate,
  val failedFields: FailedFields,
) {

  companion object {

    fun empty(therapy: TherapyEntity) = therapy.run {
      DealFormModel(
        id,
        EMPTY_STRING,
        EMPTY_STRING,
        LocalDate.now(),
        LocalTime.now(),
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
    val time: Boolean = false,
  ) {

    val has:Boolean
      get() = name || date || time
  }
}
