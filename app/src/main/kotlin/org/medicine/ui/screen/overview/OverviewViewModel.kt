package org.medicine.ui.screen.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.ui.screen.overview.model.OverviewIntent
import org.medicine.ui.screen.overview.model.OverviewViewState
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:06.
 */
@HiltViewModel
class OverviewViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
) : BaseViewModel(), IntentHandler<OverviewIntent> {

  var uiState by mutableStateOf<OverviewViewState>(OverviewViewState.Initial)
    private set

  override fun obtainIntent(intent: OverviewIntent) {
    launch {
      when (val state = uiState) {
        is OverviewViewState.Initial -> reduce(state, intent)
        is OverviewViewState.NoOneTherapies -> Unit
        is OverviewViewState.Therapies -> Unit
      }
    }
  }

  private suspend fun reduce(state: OverviewViewState.Initial, intent: OverviewIntent) {
    when (intent) {
      is OverviewIntent.EnterScreen -> fetchTherapies()
      else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private suspend fun fetchTherapies() {
    // Fetch therapies and set view state.
    //delay(1000)
    //
    //uiState = OverviewViewState.NoOneTherapies
  }
}
