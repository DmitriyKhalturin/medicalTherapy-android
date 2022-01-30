package org.medicine.ui.screen.dayschedule.model

import org.medicine.model.TherapyScheduleMapper
import org.medicine.source.database.embedded.TherapyScheduleEmbedded
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
      TherapyScheduleMapper.mapMedicineToSchedule(medicines),
      TherapyScheduleMapper.mapDealToSchedule(deals),
    )
  }
}
