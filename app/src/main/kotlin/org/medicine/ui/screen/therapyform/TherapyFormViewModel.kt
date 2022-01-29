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
    val therapyFormModel = if (therapyId != null) {
      map(repository.getTherapy(therapyId))
    } else {
      TherapyFormModel.empty()
    }

    uiState = MedicalFormViewState.Object(
      therapyId,
      therapyFormModel,
    )
  }

  override suspend fun fillFormModel(model: TherapyFormModel) {
    uiState = MedicalFormViewState.Object(destination.therapyId, model)
  }

  override suspend fun createOrSaveObject(id: Long?, model: TherapyFormModel) {
    val failedFields = TherapyFormModel.FailedFields(
      model.name.isEmptyOrBlank(),
      model.description.isEmptyOrBlank(),
      (model.startDate <= model.endDate),
    )

    if (failedFields.has) {
      uiState = MedicalFormViewState.Object(
        id,
        model.copy(failedFields = failedFields),
      )
    } else {
      val therapyEntity = map(id, model)
      val therapyEntityId = if (id != null) {
        repository.updateTherapy(therapyEntity)
        id
      } else {
        repository.createTherapy(therapyEntity)
      }

      uiState = MedicalFormViewState.SaveOnSuccessful(therapyEntityId)
    }
  }

  override suspend fun deleteObject(id: Long) {
    repository.deleteTherapy(id)

    uiState = MedicalFormViewState.DeleteOnSuccessful(id)
  }
}
