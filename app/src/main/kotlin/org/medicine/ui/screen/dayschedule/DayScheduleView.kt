package org.medicine.ui.screen.dayschedule

import androidx.compose.runtime.Composable
import org.medicine.ui.screen.dayschedule.model.DayScheduleViewState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 30.01.2022 21:22.
 */

@Composable
fun DayScheduleView(
  uiState: DayScheduleViewState,
  navigateUp: () -> Unit,
) {
  when (uiState) {
    is DayScheduleViewState.Initial -> Unit
    is DayScheduleViewState.TherapySchedule -> Unit
  }
}
