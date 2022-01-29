package org.medicine.ui.screen.medicineform

import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.navigation.Destination
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.tools.isEmptyOrBlank
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.common.viewmodel.MedicalFormViewModel
import org.medicine.ui.screen.medicineform.model.MedicineFormModel
import org.medicine.ui.screen.medicineform.model.MedicineFormModelMapper.map
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:39.
 */
@HiltViewModel
class MedicineFormViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
) : MedicalFormViewModel<Destination.MedicineForm, MedicineFormModel>() {

  override suspend fun fetchObject() {
    val medicineId: Long? = destination.medicineId
    val therapyEntity = repository.getTherapy(destination.therapyId)
    val medicineFormModel = if (medicineId != null) {
      map(therapyEntity, repository.getMedicine(medicineId))
    } else {
      MedicineFormModel.empty(therapyEntity)
    }

    uiState = MedicalFormViewState.Object(
      medicineId,
      medicineFormModel,
    )
  }

  override suspend fun fillFormModel(model: MedicineFormModel) {
    uiState = MedicalFormViewState.Object(destination.medicineId, model)
  }

  override suspend fun createOrSaveObject(id: Long?, model: MedicineFormModel) {
    val failedFields = MedicineFormModel.FailedFields(
      model.name.isEmptyOrBlank(),
      model.description.isEmptyOrBlank(),
      (model.startDate <= model.endDate),
      model.times.isEmpty()
    )

    if (failedFields.has) {
      uiState = MedicalFormViewState.Object(
        id,
        model.copy(failedFields = failedFields),
      )
    } else {
      val medicineEntity = map(id, model)
      val medicineEntityId = if (id != null) {
        repository.updateMedicine(medicineEntity)
        id
      } else {
        repository.createMedicine(medicineEntity)
      }

      uiState = MedicalFormViewState.SaveOnSuccessful(medicineEntityId)
    }
  }

  override suspend fun deleteObject(id: Long) {
    repository.deleteMedicine(id)

    uiState = MedicalFormViewState.DeleteOnSuccessful(id)
  }
}
