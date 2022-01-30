package org.medicine.ui.screen.dayschedule

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.medicine.common.ui.setSystemUiColors
import org.medicine.ui.screen.dayschedule.model.DayScheduleIntent

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 10.11.2021 23:59.
 */

@Composable
fun DayScheduleScreen(navController: NavController, viewModel: DayScheduleViewModel) {
  val uiState = viewModel.uiState

  rememberSystemUiController()
    .setSystemUiColors(
      MaterialTheme.colors.background,
      MaterialTheme.colors.background
    )

  DayScheduleView(
    uiState,
    { navController.navigateUp() }
  )

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(DayScheduleIntent.EnterScreen)
  }
}
