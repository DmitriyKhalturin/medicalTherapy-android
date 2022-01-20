package org.medicine.model.medicine

import org.medicine.schedule.data.Medicine
import org.medicine.source.database.entity.MedicineType

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 19.01.2022 0:28.
 */

fun MedicineType.sourceToSchedule() = when (this) {
  MedicineType.PILLS -> Medicine.Type.PILLS
  MedicineType.INJECTION -> Medicine.Type.INJECTION
  MedicineType.LIQUID -> Medicine.Type.LIQUID
}


fun Medicine.Type.scheduleToSource() = when (this) {
  Medicine.Type.PILLS -> MedicineType.PILLS
  Medicine.Type.INJECTION -> MedicineType.INJECTION
  Medicine.Type.LIQUID -> MedicineType.LIQUID
}
