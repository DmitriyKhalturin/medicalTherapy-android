package org.medicine.ui.screen.dayschedule

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.medicine.ui.screen.dayschedule.model.DayScheduleModel
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
    is DayScheduleViewState.TherapySchedule -> DaySchedule(uiState.therapy)
  }
}

@Composable
fun DaySchedule(therapy: DayScheduleModel) {
  Column {
    Text(text = therapy.name)
    Text(text = "Дата: ${therapy.date}")

    Text(text = "Медицина:")
    therapy.medicines.forEach {
      Text(text = "${it.name} - ${it.type}")
    }

    Text(text = "События:")
    therapy.deals.forEach {
      Text(text = "${it.name} - ${it.count}")
    }
  }
}
