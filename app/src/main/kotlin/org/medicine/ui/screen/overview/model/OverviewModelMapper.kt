package org.medicine.ui.screen.overview.model

import org.medicine.source.database.entity.TherapyEntity

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 10.01.2022 19:13.
 */
object OverviewModelMapper {

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
