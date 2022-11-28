package org.medicine.ui.screen.therapyform.model

import org.medicine.source.database.entity.TherapyEntity
import org.medicine.tools.EMPTY_STRING

object TherapyFormModelMapper {

  fun map(entity: TherapyEntity) = entity.run {
    TherapyFormModel(
      name,
      (description ?: EMPTY_STRING),
      startDate, endDate,
      TherapyFormModel.FailedFields(),
    )
  }

  fun map(id: Long?, model: TherapyFormModel) = model.run {
    TherapyEntity(
      (id ?: 0L),
      name,
      description,
      startDate,
      endDate,
    )
  }
}
