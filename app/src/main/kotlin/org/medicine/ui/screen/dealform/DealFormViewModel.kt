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

  override suspend fun enterScreen(state: MedicalFormViewState<DealFormModel>) {
    fetchDeal()
  }

  override suspend fun fillForm(state: MedicalFormViewState<DealFormModel>, intent: MedicalFormIntent<DealFormModel>) {
  }

  override suspend fun createOrSaveObject(state: MedicalFormViewState<DealFormModel>, intent: MedicalFormIntent<DealFormModel>) {
  }

  override suspend fun deleteObject(state: MedicalFormViewState<DealFormModel>, intent: MedicalFormIntent<DealFormModel>) {
  }


  private suspend fun reduce(state: MedicalFormViewState.Initial<DealFormModel>, intent: MedicalFormIntent<DealFormModel>) {
    when (intent) {
      is MedicalFormIntent.EnterScreen -> fetchDeal()
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private suspend fun reduce(state: MedicalFormViewState.Object<DealFormModel>, intent: MedicalFormIntent<DealFormModel>) {
    when (intent) {
      is MedicalFormIntent.EnterScreen -> Unit
      is MedicalFormIntent.FillForm -> fillDeal(intent.`object`)
      is MedicalFormIntent.CreateOrSaveObject -> createOrSaveDeal(intent.objectId, intent.`object`)
      is MedicalFormIntent.DeleteObject -> deleteDeal(intent.objectId)
      // else -> throw UnimplementedViewStateException(intent, state)
    }
  }


  private suspend fun fetchDeal(dealId: Long? = destination.dealId) {
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

  private fun fillDeal(deal: DealFormModel) {
    uiState = MedicalFormViewState.Object(destination.dealId, deal)
  }

  private suspend fun createOrSaveDeal(dealId: Long?, deal: DealFormModel) {
    val failedFields = DealFormModel.FailedFields(
      deal.name.isEmptyOrBlank(),
      deal.description.isEmptyOrBlank(),
    )

    if (failedFields.has) {
      uiState = MedicalFormViewState.Object(
        dealId,
        deal.copy(failedFields = failedFields),
      )
    } else {
      TODO("Unimplemented repository call")
      val entityId = -1L

      uiState = MedicalFormViewState.SaveOnSuccessful(entityId)
    }
  }

  private suspend fun deleteDeal(dealId: Long) {
    TODO("Unimplemented repository call")

    uiState = MedicalFormViewState.DeleteOnSuccessful(dealId)
  }
}
