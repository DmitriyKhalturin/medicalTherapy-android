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
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */
@HiltViewModel
class TherapyFormViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
): BaseViewModel(), IntentHandler<TherapyFormIntent> {

  private val destination: Destination.TherapyForm = savedStateHandle.destination()
  private val therapyId: Long? = destination.therapyId

  var uiState by mutableStateOf<TherapyFormViewState>(TherapyFormViewState.Initial)
    private set

  override fun obtainIntent(intent: TherapyFormIntent) {
    when (val state = uiState) {
      is TherapyFormViewState.Initial -> reduce(state, intent)
      is TherapyFormViewState.Therapy -> reduce(state, intent)
    }
  }

  private fun reduce(state: TherapyFormViewState.Initial, intent: TherapyFormIntent) {
    when (intent) {
      is TherapyFormIntent.EnterScreen -> fetchTherapy()
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private fun fetchTherapy() {
    therapyId?.also {
      // ***

      uiState = TherapyFormViewState.Therapy(
        it,
        TherapyFormModel(
          EMPTY_STRING,
          EMPTY_STRING,
          LocalDate.now(),
          LocalDate.now(),
        )
      )
    }
  }

  private fun reduce(state: TherapyFormViewState.Therapy, intent: TherapyFormIntent) {
    when (intent) {
      is TherapyFormIntent.SetTherapyForm -> setTherapyForm(intent.therapyForm)
      is TherapyFormIntent.SaveTherapyForm -> saveTherapyForm(intent.therapyId, intent.therapyForm)
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private fun setTherapyForm(therapyForm: TherapyFormModel) {
    uiState = TherapyFormViewState.Therapy(therapyId, therapyForm)
  }

  private fun saveTherapyForm(therapyId: Long?, therapyForm: TherapyFormModel) {
    //
  }
}
