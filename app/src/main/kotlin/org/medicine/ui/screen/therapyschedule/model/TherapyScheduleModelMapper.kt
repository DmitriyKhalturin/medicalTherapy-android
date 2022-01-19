package org.medicine.ui.screen.therapyschedule.model

import org.medicine.model.TherapyScheduleMapper.mapDealToSchedule
import org.medicine.model.TherapyScheduleMapper.mapMedicineToSchedule
import org.medicine.source.database.embedded.TherapyScheduleEmbedded

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 11.01.2022 20:47.
 */
object TherapyScheduleModelMapper {

  fun map(embedded: TherapyScheduleEmbedded): TherapyScheduleModel = embedded.run {
    TherapyScheduleModel(
      therapy.name,
      therapy.startDate,
      therapy.endDate,
      mapMedicineToSchedule(medicines),
      mapDealToSchedule(deals),
    )
  }
}
