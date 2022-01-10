package org.medicine.ui.screen.overview.model.map

import org.medicine.source.database.entity.TherapyEntity
import org.medicine.ui.screen.overview.model.TherapyModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 10.01.2022 19:13.
 */
object TherapyMapper {

  fun map(entity: TherapyEntity) = entity.run {
    TherapyModel(
      id,
      name,
      description,
      startDate,
      endDate
    )
  }
}
