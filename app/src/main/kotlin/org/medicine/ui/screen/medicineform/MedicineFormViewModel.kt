package org.medicine.ui.screen.medicineform

import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.navigation.Destination
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.tools.isEmptyOrBlank
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.common.viewmodel.MedicalFormViewModel
import org.medicine.ui.screen.medicineform.model.MedicineFormModel
import org.medicine.ui.screen.medicineform.model.MedicineFormModelMapper
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
    val model = if (medicineId != null) {
      TODO("Unimplemented repository call")
    } else {
      val therapy = repository.getTherapy(destination.therapyId)

      MedicineFormModelMapper.emptyMedicineFormModel(therapy)
    }

    uiState = MedicalFormViewState.Object(
      medicineId,
      model,
    )
  }

  override suspend fun fillFormModel(model: MedicineFormModel) {
    uiState = MedicalFormViewState.Object(destination.medicineId, model)
  }

  override suspend fun createOrSaveObject(id: Long?, model: MedicineFormModel) {
    val failedFields = MedicineFormModel.FailedFields(
      model.name.isEmptyOrBlank(),
      model.description.isEmptyOrBlank(),
      model.startDate.isAfter(model.endDate),
      model.times.isEmpty()
    )

    if (failedFields.has) {
      uiState = MedicalFormViewState.Object(
        id,
        model.copy(failedFields = failedFields),
      )
    } else {
      TODO("Unimplemented repository call")
      val entityId = -1L

      uiState = MedicalFormViewState.SaveOnSuccessful(entityId)
    }
  }

  override suspend fun deleteObject(id: Long) {
    TODO("Unimplemented repository call")

    uiState = MedicalFormViewState.DeleteOnSuccessful(id)
  }
}
