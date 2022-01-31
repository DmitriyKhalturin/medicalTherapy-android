package org.medicine.ui.screen.dayschedule

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.navigation.Destination
import org.medicine.navigation.viewmodel.NavigationViewModel
import org.medicine.source.repository.MedicalTherapyRepository
import org.medicine.tools.viewmodel.IntentHandler
import org.medicine.ui.screen.dayschedule.model.DayScheduleIntent
import org.medicine.ui.screen.dayschedule.model.DayScheduleModelMapper.map
import org.medicine.ui.screen.dayschedule.model.DayScheduleViewState
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:40.
 */
@HiltViewModel
class DayScheduleViewModel @Inject constructor(
  private val repository: MedicalTherapyRepository,
) : NavigationViewModel<Destination.DaySchedule>(), IntentHandler<DayScheduleIntent> {

  var uiState by mutableStateOf<DayScheduleViewState>(DayScheduleViewState.Initial)
    private set

  override fun obtainIntent(intent: DayScheduleIntent) {
    launch {
      val state = uiState

      when (intent) {
        is DayScheduleIntent.EnterScreen -> reduce(state, intent)
      }
    }
  }

  private suspend fun reduce(state: DayScheduleViewState, intent: DayScheduleIntent.EnterScreen) {
    fetchDaySchedule()
  }

  private suspend fun fetchDaySchedule(therapyId: Long = destination.therapyId, date: LocalDate = destination.date) {
    val dayScheduleModel = map(repository.getTherapyScheduleByDay(therapyId, date), date)

    uiState = DayScheduleViewState.TherapySchedule(
      therapyId,
      dayScheduleModel,
    )
  }
}
