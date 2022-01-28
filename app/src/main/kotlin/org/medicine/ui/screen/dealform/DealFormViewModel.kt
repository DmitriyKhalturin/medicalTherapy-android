package org.medicine.ui.screen.dealform

import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.navigation.Destination
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.tools.isEmptyOrBlank
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.common.viewmodel.MedicalFormViewModel
import org.medicine.ui.screen.dealform.model.DealFormModel
import org.medicine.ui.screen.dealform.model.DealFormModelMapper.emptyDealFormModel
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:40.
 */
@HiltViewModel
class DealFormViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
) : MedicalFormViewModel<Destination.DealForm, DealFormModel>() {

  override suspend fun fetchObject() {
    val dealId: Long? = destination.dealId
    val model = if (dealId != null) {
      TODO("Unimplemented repository call")
    } else {
      val therapy = repository.getTherapy(destination.therapyId)

      emptyDealFormModel(therapy)
    }

    uiState = MedicalFormViewState.Object(
      dealId,
      model,
    )
  }

  override suspend fun fillFormModel(model: DealFormModel) {
    uiState = MedicalFormViewState.Object(destination.dealId, model)
  }

  override suspend fun createOrSaveObject(id: Long?, model: DealFormModel) {
    val failedFields = DealFormModel.FailedFields(
      model.name.isEmptyOrBlank(),
      model.description.isEmptyOrBlank(),
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
