package org.medicine.ui.screen.medicineform.model

import org.medicine.source.database.entity.MedicineEntity

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 18.01.2022 22:21.
 */
object MedicineFormModelMapper {

  fun map(entity: MedicineEntity) = entity.run {
    MedicineFormModel(

    )
  }

  fun map(id: Long?, model: MedicineFormModel, therapyId: Long) = model.run {
    MedicineEntity(

    )
  }
}
