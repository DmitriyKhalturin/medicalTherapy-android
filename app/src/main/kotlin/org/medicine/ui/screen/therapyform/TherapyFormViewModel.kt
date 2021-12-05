package org.medicine.ui.screen.therapyform

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */
@HiltViewModel
class TherapyFormViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
): BaseViewModel(), IntentHandler<TherapyFormIntent> {

  var uiState by mutableStateOf<TherapyFormViewState>(TherapyFormViewState.Initial)
    private set

  override fun obtainIntent(intent: TherapyFormIntent) {
    TODO("Not yet implemented")
  }
}
