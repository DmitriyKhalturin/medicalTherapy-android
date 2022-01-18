package org.medicine.ui.screen.dealform.model

import org.medicine.source.database.entity.DealEntity
import org.medicine.tools.EMPTY_STRING
import java.time.LocalDateTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:12.
 */
object DealFormModelMapper {

  fun map(entity: DealEntity) = entity.run {
    DealFormModel(
      name,
      (description ?: EMPTY_STRING),
      dateTime,
      DealFormModel.FailedFields(),
    )
  }

  fun buildEmptyModel() =
    DealFormModel(
      EMPTY_STRING,
      EMPTY_STRING,
      LocalDateTime.now(),
      DealFormModel.FailedFields(),
    )

  fun map(id: Long?, model: DealFormModel, therapyId: Long) = model.run {
    DealEntity(
      (id ?: 0L),
      name,
      description,
      dateTime,
      therapyId,
    )
  }
}
