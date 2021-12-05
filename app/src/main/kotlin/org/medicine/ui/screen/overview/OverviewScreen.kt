package org.medicine.ui.screen.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.medicine.navigation.Destination
import org.medicine.navigation.navigate
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.overview.composable.NoOneTherapies
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
  )

  LaunchedEffect(key1 = uiState) {
    viewModel.obtainIntent(OverviewIntent.EnterScreen)
  }
}

@Composable
fun OverviewView(
  navController: NavController,
  uiState: OverviewViewState,
) {
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      OverviewTopBar(
        applicationOnClick = {
          navController.navigate(Destination.ApplicationInfo)
        }
      )
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding),
      contentAlignment = Alignment.Center
    ) {
      when (uiState) {
        is OverviewViewState.Initial -> Unit
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
}

@Composable
fun OverviewTopBar(
  applicationOnClick: () -> Unit,
) {
  TopAppBar(
    title = {},
    actions = {
      IconButton(onClick = { applicationOnClick() }) {
        Icon(Icons.Outlined.Info, EMPTY_STRING)
      }
    },
  )
}


@Preview(showBackground = true)
@Composable
fun OverviewPreview() {
  MedicalTherapyTheme {
    OverviewView(
      rememberNavController(),
      OverviewViewState.Initial,
    )
  }
}
