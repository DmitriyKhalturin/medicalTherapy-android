package org.medicine.ui.screen.dayschedule.model

import org.medicine.source.database.embedded.TherapyScheduleEmbedded
import org.medicine.source.database.entity.DealEntity
import org.medicine.source.database.entity.MedicineEntity
import org.medicine.tools.EMPTY_STRING
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 30.01.2022 21:54.
 */
object DayScheduleModelMapper {

  fun map(embedded: TherapyScheduleEmbedded, date: LocalDate): DayScheduleModel = embedded.run {
    DayScheduleModel(
      therapy.name,
      date,
      medicines.map { map(it) },
      deals.map { map(it) },
    )
  }

  private fun map(entity: MedicineEntity) = entity.run {
    DayScheduleModel.Medicine(
      id,
      name,
      (description ?: EMPTY_STRING),
      type,
      (dosage ?: EMPTY_STRING),
      admissionTimes,
    )
  }

  private fun map(entity: DealEntity) = entity.run {
    DayScheduleModel.Deal(
      id,
      name,
      (description ?: EMPTY_STRING),
      dateTime.toLocalTime(),
    )
  }
}
