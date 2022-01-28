package org.medicine.ui.screen.dealform.model

import org.medicine.source.database.entity.DealEntity
import org.medicine.source.database.entity.TherapyEntity
import org.medicine.tools.EMPTY_STRING
import java.time.LocalDateTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:12.
 */
object DealFormModelMapper {

  fun map(therapy: TherapyEntity, deal: DealEntity) = deal.run {
    DealFormModel(
      therapyId,
      name,
      (description ?: EMPTY_STRING),
      dateTime.toLocalDate(),
      dateTime.toLocalTime(),
      therapy.startDate,
      therapy.endDate,
      DealFormModel.FailedFields(),
    )
  }

  fun map(id: Long?, deal: DealFormModel, therapyId: Long) = deal.run {
    DealEntity(
      (id ?: 0L),
      name,
      description,
      LocalDateTime.of(date, time),
      therapyId,
    )
  }
}
