package org.medicine.ui.screen.therapyschedule

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.medicine.common.ui.setSystemUiColors
import org.medicine.navigation.Destination
import org.medicine.navigation.navigate
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleIntent

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 10.11.2021 23:59.
 */

@Composable
fun TherapyScheduleScreen(navController: NavController, viewModel: TherapyScheduleViewModel) {
  val uiState = viewModel.uiState

  rememberSystemUiController()
    .setSystemUiColors(
      MaterialTheme.colors.background,
      MaterialTheme.colors.background
    )

  TherapyScheduleView(
    uiState,
    viewModel.destination.therapyId,
    { navController.navigateUp() },
    { therapyId, date -> navController.navigate(Destination.DaySchedule(therapyId, date)) },
    { viewModel.obtainIntent(TherapyScheduleIntent.RefreshTherapy) }
  )

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(TherapyScheduleIntent.EnterScreen)
  }
}
