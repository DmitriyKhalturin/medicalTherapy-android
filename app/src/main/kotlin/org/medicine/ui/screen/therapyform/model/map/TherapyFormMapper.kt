package org.medicine.ui.screen.therapyform.model.map

import org.medicine.source.database.entity.TherapyEntity
import org.medicine.tools.EMPTY_STRING
import org.medicine.tools.daysUntil
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import java.time.LocalDate

object TherapyFormMapper {

  private const val BETWEEN_START_END_THERAPY_DATE = 7L

  fun map(entity: TherapyEntity) = entity.run {
    TherapyFormModel(
      name,
      (description ?: EMPTY_STRING),
      startDate, endDate,
      TherapyFormModel.FailedFields(),
    )
  }

  fun buildEmptyModel() =
    TherapyFormModel(
      EMPTY_STRING,
      EMPTY_STRING,
      LocalDate.now(),
      LocalDate.now().plusDays(BETWEEN_START_END_THERAPY_DATE),
      TherapyFormModel.FailedFields(),
    )

  fun map(id: Long?, model: TherapyFormModel) = model.run {
    TherapyEntity(
      (id ?: 0L),
      name,
      description,
      startDate,
      startDate.daysUntil(endDate),
    )
  }
}
