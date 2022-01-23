package org.medicine.ui.screen.therapyform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.navigation.Destination
import org.medicine.navigation.viewmodel.NavigationViewModel
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.tools.isEmptyOrBlank
import org.medicine.ui.common.model.MedicalFormIntent
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormModelMapper.buildEmptyModel
import org.medicine.ui.screen.therapyform.model.TherapyFormModelMapper.map
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */
@HiltViewModel
class TherapyFormViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
): NavigationViewModel<Destination.TherapyForm>(), IntentHandler<MedicalFormIntent<TherapyFormModel>> {

  var uiState by mutableStateOf<MedicalFormViewState<TherapyFormModel>>(MedicalFormViewState.Initial())
    private set

  override fun obtainIntent(intent: MedicalFormIntent<TherapyFormModel>) {
    launch {
      when (val state = uiState) {
        is MedicalFormViewState.Initial -> reduce(state, intent)
        is MedicalFormViewState.Object -> reduce(state, intent)
        else -> throw UnimplementedViewStateException(intent, state)
      }
    }
  }

  private suspend fun reduce(state: MedicalFormViewState.Initial<TherapyFormModel>, intent: MedicalFormIntent<TherapyFormModel>) {
    when (intent) {
      is MedicalFormIntent.EnterScreen -> fetchTherapy()
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private suspend fun reduce(state: MedicalFormViewState.Object<TherapyFormModel>, intent: MedicalFormIntent<TherapyFormModel>) {
    when (intent) {
      is MedicalFormIntent.EnterScreen -> Unit
      is MedicalFormIntent.FillForm -> fillTherapy(intent.`object`)
      is MedicalFormIntent.CreateOrSaveObject -> createOrSaveTherapy(intent.objectId, intent.`object`)
      is MedicalFormIntent.DeleteObject -> deleteTherapy(intent.objectId)
      // else -> throw UnimplementedViewStateException(intent, state)
    }
  }


  private suspend fun fetchTherapy(therapyId: Long? = destination.therapyId) {
    val model = if (therapyId != null ) { map(repository.getTherapy(therapyId)) } else { buildEmptyModel() }

    uiState = MedicalFormViewState.Object(
      therapyId,
      model,
    )
  }

  private fun fillTherapy(therapy: TherapyFormModel) {
    uiState = MedicalFormViewState.Object(destination.therapyId, therapy)
  }

  private suspend fun createOrSaveTherapy(therapyId: Long?, therapy: TherapyFormModel) {
    val failedFields = TherapyFormModel.FailedFields(
      therapy.name.isEmptyOrBlank(),
      therapy.description.isEmptyOrBlank(),
      therapy.startDate.isAfter(therapy.endDate),
    )

    if (failedFields.has) {
      uiState = MedicalFormViewState.Object(
        therapyId,
        therapy.copy(failedFields = failedFields),
      )
    } else {
      val entity = map(therapyId, therapy)
      val entityId = if (therapyId != null) {
        repository.updateTherapy(entity)
        therapyId
      } else {
        repository.createTherapy(entity)
      }

      uiState = MedicalFormViewState.SaveOnSuccessful(entityId)
    }
  }

  private suspend fun deleteTherapy(therapyId: Long) {
    repository.deleteTherapy(therapyId)

    uiState = MedicalFormViewState.DeleteOnSuccessful(therapyId)
  }
}
