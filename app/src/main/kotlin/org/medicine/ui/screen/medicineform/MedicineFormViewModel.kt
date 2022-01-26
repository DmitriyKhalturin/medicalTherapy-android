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

  override suspend fun enterScreen(state: MedicalFormViewState<MedicineFormModel>) {
    fetchMedicine()
  }

  override suspend fun fillForm(state: MedicalFormViewState<MedicineFormModel>, intent: MedicalFormIntent<MedicineFormModel>) {
  }

  override suspend fun createOrSaveObject(state: MedicalFormViewState<MedicineFormModel>, intent: MedicalFormIntent<MedicineFormModel>) {
  }

  override suspend fun deleteObject(state: MedicalFormViewState<MedicineFormModel>, intent: MedicalFormIntent<MedicineFormModel>) {
  }


  private suspend fun reduce(state: MedicalFormViewState.Initial<MedicineFormModel>, intent: MedicalFormIntent<MedicineFormModel>) {
    when (intent) {
      is MedicalFormIntent.EnterScreen -> fetchMedicine()
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private suspend fun reduce(state: MedicalFormViewState.Object<MedicineFormModel>, intent: MedicalFormIntent<MedicineFormModel>) {
    when (intent) {
      is MedicalFormIntent.EnterScreen -> Unit
      is MedicalFormIntent.FillForm -> fillMedicine(intent.`object`)
      is MedicalFormIntent.CreateOrSaveObject -> createOrSaveMedicine(intent.objectId, intent.`object`)
      is MedicalFormIntent.DeleteObject -> deleteMedicine(intent.objectId)
      // else -> throw UnimplementedViewStateException(intent, state)
    }
  }


  private suspend fun fetchMedicine(medicineId: Long? = destination.medicineId) {
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

  private fun fillMedicine(medicine: MedicineFormModel) {
    uiState = MedicalFormViewState.Object(destination.medicineId, medicine)
  }

  private suspend fun createOrSaveMedicine(medicineId: Long?, medicine: MedicineFormModel) {
    val failedFields = MedicineFormModel.FailedFields(
      medicine.name.isEmptyOrBlank(),
      medicine.description.isEmptyOrBlank(),
      medicine.startDate.isAfter(medicine.endDate),
      medicine.times.isEmpty()
    )

    if (failedFields.has) {
      uiState = MedicalFormViewState.Object(
        medicineId,
        medicine.copy(failedFields = failedFields),
      )
    } else {
      TODO("Unimplemented repository call")
      val entityId = -1L

      uiState = MedicalFormViewState.SaveOnSuccessful(entityId)
    }
  }

  private suspend fun deleteMedicine(medicineId: Long) {
    TODO("Unimplemented repository call")

    uiState = MedicalFormViewState.DeleteOnSuccessful(medicineId)
  }
}
