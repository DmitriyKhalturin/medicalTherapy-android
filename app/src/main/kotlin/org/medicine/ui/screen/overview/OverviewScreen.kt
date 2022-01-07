package org.medicine.ui.screen.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.medicine.navigation.Destination
import org.medicine.navigation.navigate
import org.medicine.ui.screen.overview.composable.NoOneTherapies
import org.medicine.ui.screen.overview.composable.PrepareTherapies
import org.medicine.ui.screen.overview.composable.Therapies
import org.medicine.ui.screen.overview.model.OverviewIntent
import org.medicine.ui.screen.overview.model.OverviewViewState
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 5:50.
 */

@Composable
fun OverviewScreen(
  navController: NavController,
  viewModel: OverviewViewModel,
) {
  val uiState = viewModel.uiState

  OverviewView(
    navController,
    uiState,
    therapyLoadingDone = { viewModel.therapyLoadingDone() },
  )

  LaunchedEffect(key1 = viewModel) {
    viewModel.obtainIntent(OverviewIntent.EnterScreen)
  }
}

@Composable
private fun OverviewView(
  navController: NavController,
  uiState: OverviewViewState,
  therapyLoadingDone: () -> Unit,
) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    when (uiState) {
      is OverviewViewState.Initial ->
        PrepareTherapies(
          therapyLoadingDone = {
            therapyLoadingDone()
          }
        )
      is OverviewViewState.NoOneTherapies ->
        NoOneTherapies(
          createTherapyOnClick = {
            navController.navigate(Destination.TherapyForm())
          }
        )
      is OverviewViewState.Therapies ->
        Therapies(
          uiState.activeTherapies,
          uiState.archivedTherapies,
          createTherapyOnClick = {
            navController.navigate(Destination.TherapyForm())
          },
          openTherapyOnClick = { therapyId ->
            navController.navigate(Destination.TherapySchedule(therapyId))
          }
        )
    }
  }
}


@Preview(showBackground = true)
@Composable
fun OverviewPreview() {
  MedicalTherapyTheme {
    OverviewView(
      rememberNavController(),
      OverviewViewState.Initial,
      therapyLoadingDone = {},
    )
  }
}
