package org.medicine.ui.screen.therapyschedule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.exception.UnimplementedViewStateException
import org.medicine.common.viewmodel.IntentHandler
import org.medicine.navigation.Destination
import org.medicine.navigation.viewmodel.NavigationViewModel
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleIntent
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleModelMapper.map
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleViewState
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:24.
 */
@HiltViewModel
class TherapyScheduleViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
) : NavigationViewModel<Destination.TherapySchedule>(), IntentHandler<TherapyScheduleIntent> {

  var uiState by mutableStateOf<TherapyScheduleViewState>(TherapyScheduleViewState.Initial)
    private set

  override fun obtainIntent(intent: TherapyScheduleIntent) {
    launch {
      when (val state = uiState) {
        is TherapyScheduleViewState.Initial -> reduce(state, intent)
        is TherapyScheduleViewState.Therapy -> throw UnimplementedViewStateException(intent, state)
      }
    }
  }

  private suspend fun reduce(state: TherapyScheduleViewState.Initial, intent: TherapyScheduleIntent) {
    when (intent) {
      is TherapyScheduleIntent.EnterScreen -> fetchTherapy()
      // else -> throw UnimplementedViewStateException(intent, state)
    }
  }

  private suspend fun fetchTherapy() {
    val therapyId = destination.therapyId
    val model = map(repository.getTherapySchedule(therapyId))

    uiState = TherapyScheduleViewState.Therapy(
      therapyId,
      model,
    )
  }
}
