package org.medicine.ui.screen.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.medicine.common.ui.setSystemUiColors
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
fun OverviewScreen(navController: NavController, viewModel: OverviewViewModel) {
  val uiState = viewModel.uiState

  rememberSystemUiController()
    .setSystemUiColors(
      MaterialTheme.colors.primaryVariant,
      MaterialTheme.colors.background
    )

  OverviewView(
    uiState,
    {
      navController.navigate(Destination.ApplicationInfo)
    },
    {
      val options = NavOptions.Builder()
        .build()

      navController.navigate(Destination.TherapyForm(), options)
    },
    { therapyId ->
      navController.navigate(Destination.TherapySchedule(therapyId))
    },
  )

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(OverviewIntent.EnterScreen)
  }
}

@Composable
private fun OverviewView(
  uiState: OverviewViewState,
  openApplicationInfo: () -> Unit,
  createNewTherapy: () -> Unit,
  openTherapy: (Long) -> Unit,
) {
  Scaffold(
    topBar = {
      TopAppBar(elevation = 0.dp) {
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))

        IconButton(onClick = {
          openApplicationInfo()
        }) {
          Icon(Icons.Outlined.Info, EMPTY_STRING, tint = Color.White)
        }
      }
    },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = {
      FloatingActionButton(onClick = {
        createNewTherapy()
      }) {
        Icon(Icons.Filled.Add, EMPTY_STRING, modifier = Modifier.scale(scale = 1.25f))
      }
    },
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
            openTherapyOnClick = { therapyId -> openTherapy(therapyId) }
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
      // OverviewViewState.Initial,
      // OverviewViewState.NoOneTherapies,
      OverviewViewState.Therapies(
        activeTherapies = stubActiveTherapies(),
        archivedTherapies = emptyList(),
      ),
      {}, {}, {},
    )
  }
}
