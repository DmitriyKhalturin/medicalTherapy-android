package org.medicine.ui.screen.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.ui.screen.overview.model.OverviewIntent
import org.medicine.ui.screen.overview.model.OverviewViewState
import org.medicine.ui.screen.overview.model.map.TherapyMapper.map
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:06.
 */
@HiltViewModel
class OverviewViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
  savedStateHandle: SavedStateHandle,
) : BaseViewModel(), IntentHandler<OverviewIntent> {

  var uiState by mutableStateOf<OverviewViewState>(OverviewViewState.Initial)
    private set

  override fun obtainIntent(intent: OverviewIntent) {
    launch {
      when (intent) {
        is OverviewIntent.EnterScreen -> reduce(uiState, intent)
      }
    }
  }

  private suspend fun reduce(state: OverviewViewState, intent: OverviewIntent.EnterScreen) {
    fetchTherapies()
  }

  private suspend fun fetchTherapies() {
    val entities = repository.getTherapies()

    uiState = if (entities.isEmpty()) {
      OverviewViewState.NoOneTherapies
    } else {
      val (archivedEntities, activeEntities) = entities.partition { it.isArchived }
      val activeModels = activeEntities.map { map(it) }
      val archivedModels = archivedEntities.map { map(it) }

      OverviewViewState.Therapies(activeModels, archivedModels)
    }
  }
}
