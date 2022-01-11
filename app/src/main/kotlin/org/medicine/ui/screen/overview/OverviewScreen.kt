package org.medicine.ui.screen.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import org.medicine.navigation.Destination
import org.medicine.navigation.navigate
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.overview.composable.InitialTherapies
import org.medicine.ui.screen.overview.composable.NoOneTherapies
import org.medicine.ui.screen.overview.composable.Therapies
import org.medicine.ui.screen.overview.model.OverviewIntent
import org.medicine.ui.screen.overview.model.OverviewViewState
import org.medicine.ui.stub.data.stubActiveTherapies
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 5:50.
 */

@Composable
fun OverviewScreen(
  navController: NavController,
  systemUiController: SystemUiController,
  viewModel: OverviewViewModel,
) {
  val uiState = viewModel.uiState

  val statusBarColor = if (uiState is OverviewViewState.Therapies) {
    MaterialTheme.colors.primaryVariant
  } else {
    MaterialTheme.colors.background
  }

  systemUiController.setStatusBarColor(statusBarColor)
  systemUiController.setNavigationBarColor(MaterialTheme.colors.primaryVariant)

  OverviewView(
    navController,
    uiState,
  )

  LaunchedEffect(key1 = viewModel) {
    viewModel.obtainIntent(OverviewIntent.EnterScreen)
  }
}

@Composable
private fun OverviewView(
  navController: NavController,
  uiState: OverviewViewState,
) {
  Scaffold(
    floatingActionButtonPosition = FabPosition.End,
    isFloatingActionButtonDocked = true,
    floatingActionButton = {
      FloatingActionButton(onClick = {
        navController.navigate(Destination.TherapyForm())
      }) {
        Icon(Icons.Filled.Add, EMPTY_STRING)
      }
    },
    bottomBar = {
      BottomAppBar(cutoutShape = MaterialTheme.shapes.medium.copy(CornerSize(percent = 50))) {
        IconButton(onClick = {
          navController.navigate(Destination.ApplicationInfo)
        }) {
          Icon(Icons.Outlined.Info, EMPTY_STRING, tint = Color.White)
        }
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
      }
    }
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(it),
    ) {
      when (uiState) {
        is OverviewViewState.Initial ->
          InitialTherapies()
        is OverviewViewState.NoOneTherapies ->
          NoOneTherapies()
        is OverviewViewState.Therapies -> uiState.run {
          Therapies(
            activeTherapies,
            archivedTherapies,
            openTherapyOnClick = { therapyId ->
              navController.navigate(Destination.TherapySchedule(therapyId))
            }
          )
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun OverviewPreview() {
  MedicalTherapyTheme {
    OverviewView(
      rememberNavController(),
      // OverviewViewState.Initial,
      // OverviewViewState.NoOneTherapies,
      OverviewViewState.Therapies(
        activeTherapies = stubActiveTherapies(),
        archivedTherapies = emptyList(),
      ),
    )
  }
}
