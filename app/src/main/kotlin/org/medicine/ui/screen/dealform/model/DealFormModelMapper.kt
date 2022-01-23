package org.medicine.ui.screen.dealform.model

import org.medicine.source.database.entity.DealEntity
import org.medicine.tools.EMPTY_STRING
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:12.
 */
object DealFormModelMapper {

  fun map(entity: DealEntity) = entity.run {
    DealFormModel(
      therapyId,
      name,
      (description ?: EMPTY_STRING),
      dateTime.toLocalDate(),
      dateTime.toLocalTime(),
      DealFormModel.FailedFields(),
    )
  }

  fun emptyDealFormModel(therapyId: Long) =
    DealFormModel(
      therapyId,
      EMPTY_STRING,
      EMPTY_STRING,
      LocalDate.now(),
      LocalTime.now(),
      DealFormModel.FailedFields(),
    )

  fun map(id: Long?, model: DealFormModel, therapyId: Long) = model.run {
    DealEntity(
      (id ?: 0L),
      name,
      description,
      LocalDateTime.of(date, time),
      therapyId,
    )
  }
}
