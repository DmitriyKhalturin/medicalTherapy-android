package org.medicine.ui.screen.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.medicine.navigation.Route
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.overview.composable.NoOneTherapies
import org.medicine.ui.screen.overview.composable.Therapies
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
  Overview(
    navController,
    viewModel.uiState,
  )
}

@Composable
fun Overview(
  navController: NavController,
  uiState: OverviewViewState,
) {
  val scaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      OverviewTopBar(
        applicationOnClick = {
          navController.navigate(Route.ApplicationInfo.name)
        }
      )
    }
  ) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
      when (uiState) {
        is OverviewViewState.Initial -> {
          Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Ass(
              withArgument = {
                navController.navigate(Route.DaySchedule.name)
              },
              withoutArgument = {
                navController.navigate(Route.DaySchedule.name)
              },
            )
          }
        }
        is OverviewViewState.NoOneTherapies ->
          NoOneTherapies(
            createTherapyOnClick = { }
          )
        is OverviewViewState.Therapies ->
          Therapies(
            uiState.activeTherapies,
            uiState.archivedTherapies,
            createTherapyOnClick = {
              navController.navigate(Route.TherapyForm.name)
            },
            openTherapyOnClick = {
              // TODO: pass Id to savedStateHandle
              navController.navigate(Route.TherapyForm.name)
            }
          )
      }
    }
  }
}

@Composable
fun Ass(
  withArgument: () -> Unit,
  withoutArgument: () -> Unit
) {
  Column() {
    Button(onClick = { withArgument() }) {
      Text(text = "with argument")
    }
    Button(onClick = { withoutArgument() }) {
      Text(text = "without argument")
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


@Preview
@Composable
fun OverviewPreview() {
  MedicalTherapyTheme {
    Overview(
      rememberNavController(),
      OverviewViewState.Initial,
    )
  }
}
