package org.medicine.ui.screen.therapyschedule.model.map

import org.medicine.schedule.data.Deal
import org.medicine.schedule.data.Medicine
import org.medicine.source.database.entity.DealEntity
import org.medicine.source.database.entity.MedicineEntity
import org.medicine.source.database.entity.relation.TherapyScheduleRelation
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleModel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 11.01.2022 20:47.
 */
object TherapyScheduleMapper {

  fun map(relation: TherapyScheduleRelation): TherapyScheduleModel = relation.run {
    TherapyScheduleModel(
      therapy.name,
      therapy.startDate,
      therapy.endDate,
      medicines.map { map(it) },
      deals.map { map(it) },
    )
  }

  private fun map(entity: MedicineEntity): Medicine {}

  private fun map(entity: DealEntity): Deal {}
}
