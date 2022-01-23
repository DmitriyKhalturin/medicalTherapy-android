package org.medicine.ui.screen.overview

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.medicine.common.ui.setSystemUiColors
import org.medicine.navigation.Destination
import org.medicine.navigation.navigate
import org.medicine.ui.screen.overview.model.OverviewIntent

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 5:50.
 */

@Composable
fun OverviewScreen(navController: NavController, viewModel: OverviewViewModel) {
  val uiState = viewModel.uiState

  rememberSystemUiController()
    .setSystemUiColors(
      MaterialTheme.colors.primaryVariant,
      MaterialTheme.colors.background
    )

  OverviewView(
    uiState,
    { navController.navigate(Destination.ApplicationInfo) },
    { navController.navigate(Destination.TherapyForm()) },
    { therapyId -> navController.navigate(Destination.TherapySchedule(therapyId)) },
  )

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(OverviewIntent.EnterScreen)
  }
}
