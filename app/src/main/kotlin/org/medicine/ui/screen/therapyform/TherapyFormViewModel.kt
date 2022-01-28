package org.medicine.ui.screen.therapyform

import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.navigation.Destination
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.tools.isEmptyOrBlank
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.common.viewmodel.MedicalFormViewModel
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormModelMapper.map
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */
@HiltViewModel
class TherapyFormViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
) : MedicalFormViewModel<Destination.TherapyForm, TherapyFormModel>() {

  override suspend fun fetchObject() {
    val therapyId: Long? = destination.therapyId
    val model = if (therapyId != null) {
      map(repository.getTherapy(therapyId))
    } else {
      TherapyFormModel.empty()
    }

    uiState = MedicalFormViewState.Object(
      therapyId,
      model,
    )
  }

  override suspend fun fillFormModel(model: TherapyFormModel) {
    uiState = MedicalFormViewState.Object(destination.therapyId, model)
  }

  override suspend fun createOrSaveObject(id: Long?, model: TherapyFormModel) {
    val failedFields = TherapyFormModel.FailedFields(
      model.name.isEmptyOrBlank(),
      model.description.isEmptyOrBlank(),
      model.startDate.isAfter(model.endDate),
    )

    if (failedFields.has) {
      uiState = MedicalFormViewState.Object(
        id,
        model.copy(failedFields = failedFields),
      )
    } else {
      val entity = map(id, model)
      val entityId = if (id != null) {
        repository.updateTherapy(entity)
        id
      } else {
        repository.createTherapy(entity)
      }

      uiState = MedicalFormViewState.SaveOnSuccessful(entityId)
    }
  }

  override suspend fun deleteObject(id: Long) {
    repository.deleteTherapy(id)

    uiState = MedicalFormViewState.DeleteOnSuccessful(id)
  }
}
