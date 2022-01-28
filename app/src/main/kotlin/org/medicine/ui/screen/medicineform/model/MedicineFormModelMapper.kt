package org.medicine.ui.screen.medicineform.model

import org.medicine.model.medicine.scheduleToSource
import org.medicine.model.medicine.sourceToSchedule
import org.medicine.source.database.entity.MedicineEntity
import org.medicine.source.database.entity.TherapyEntity
import org.medicine.tools.EMPTY_STRING
import org.medicine.tools.time.daysUntil

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:21.
 */
object MedicineFormModelMapper {

  fun map(therapy: TherapyEntity, medicine: MedicineEntity) = medicine.run {
    MedicineFormModel(
      therapyId,
      name,
      (description ?: EMPTY_STRING),
      type.sourceToSchedule(),
      (dosage ?: EMPTY_STRING),
      startDate,
      endDate,
      admissionTimes,
      therapy.startDate,
      therapy.endDate,
      MedicineFormModel.FailedFields(),
    )
  }

  fun map(id: Long?, medicine: MedicineFormModel, therapyId: Long) = medicine.run {
    MedicineEntity(
      (id ?: 0L),
      name,
      description,
      type.scheduleToSource(),
      dosage,
      startDate,
      startDate.daysUntil(endDate),
      times,
      therapyId,
    )
  }
}
