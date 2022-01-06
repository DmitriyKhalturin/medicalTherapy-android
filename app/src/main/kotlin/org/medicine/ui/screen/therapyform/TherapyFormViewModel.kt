package org.medicine.ui.screen.therapyform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.navigation.Destination
import org.medicine.navigation.destination
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import org.medicine.ui.screen.therapyform.model.map.TherapyFormMapper.buildEmptyModel
import org.medicine.ui.screen.therapyform.model.map.TherapyFormMapper.map
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */
@HiltViewModel
class TherapyFormViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
  savedStateHandle: SavedStateHandle,
): BaseViewModel(), IntentHandler<TherapyFormIntent> {

  private val destination: Destination.TherapyForm = savedStateHandle.destination()
  private val therapyId: Long? = destination.therapyId

  var uiState by mutableStateOf<TherapyFormViewState>(TherapyFormViewState.Initial)
    private set

  override fun obtainIntent(intent: TherapyFormIntent) {
    launch {
      when (val state = uiState) {
        is TherapyFormViewState.Initial -> reduce(state, intent)
        is TherapyFormViewState.Therapy -> reduce(state, intent)
      }
    }
  }

  private suspend fun reduce(state: TherapyFormViewState.Initial, intent: TherapyFormIntent) {
    when (intent) {
      is TherapyFormIntent.EnterScreen -> fetchTherapy()
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private suspend fun fetchTherapy() {
    val model = if (therapyId != null ) {
      map(repository.getTherapy(therapyId))
    } else {
      buildEmptyModel()
    }

    uiState = TherapyFormViewState.Therapy(
      therapyId,
      model,
    )
  }

  private suspend fun reduce(state: TherapyFormViewState.Therapy, intent: TherapyFormIntent) {
    when (intent) {
      is TherapyFormIntent.EnterScreen -> Unit
      is TherapyFormIntent.FillTherapy -> fillTherapy(intent.therapy)
      is TherapyFormIntent.SaveTherapy -> saveTherapy(intent.therapyId, intent.therapy)
      is TherapyFormIntent.DeleteTherapy -> deleteTherapy(intent.therapyId)
      // else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private fun fillTherapy(therapy: TherapyFormModel) {
    uiState = TherapyFormViewState.Therapy(therapyId, therapy)
  }

  private suspend fun saveTherapy(therapyId: Long?, therapy: TherapyFormModel) {
    val entity = map(therapyId, therapy)

    if (therapyId != null) {
      repository.updateTherapy(entity)
    } else {
      repository.createTherapy(entity)
    }
  }

  private suspend fun deleteTherapy(therapyId: Long) {
    repository.deleteTherapy(therapyId)
  }
}
