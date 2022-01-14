package org.medicine.ui.screen.therapyschedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.medicine.R
import org.medicine.common.ui.setSystemUiColors
import org.medicine.schedule.MedicalTherapySchedule
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleIntent
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleViewState

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
    navController,
    uiState,
  )

  LaunchedEffect(key1 = viewModel) {
    viewModel.obtainIntent(TherapyScheduleIntent.EnterScreen)
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TherapyScheduleView(
  navController: NavController,
  uiState: TherapyScheduleViewState,
) {
  val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)

  LaunchedEffect(scaffoldState) {
    scaffoldState.reveal()
  }

  BackdropScaffold(
    scaffoldState = scaffoldState,
    appBar = {
      TopAppBar(
        navigationIcon = {},
        title = {
          if (uiState is TherapyScheduleViewState.Therapy) {
            Text(text = uiState.therapy.name)
          }
        },
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background,
      )
    },
    backLayerBackgroundColor = MaterialTheme.colors.background,
    backLayerContent = {
      Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
          is TherapyScheduleViewState.Initial -> Unit
          is TherapyScheduleViewState.Therapy -> uiState.run {
            MedicalTherapySchedule(
              startDate = therapy.startDate,
              endDate = therapy.endDate,
              medicines = therapy.medicines,
              deals = therapy.deals,
              dateOnClick = {},
              medicineOnClick = {},
              dealOnClick = {},
              dealsOnClick = {},
            )
          }
        }
      }
    },
    headerHeight = 48.dp,
    frontLayerShape = CutCornerShape(topStart = 32.dp),
    frontLayerBackgroundColor = MaterialTheme.colors.secondary,
    frontLayerContent = {
      Row(
        modifier = Modifier.fillMaxWidth()
      ) {
        IconButton(onClick = {}) {
          Icon(Icons.Filled.Edit, EMPTY_STRING, tint = Color.White)
        }
          IconButton(onClick = {}) {
          Icon(painterResource(id = R.drawable.ic_pills), EMPTY_STRING, tint = Color.White)
        }
          IconButton(onClick = {}) {
          Icon(painterResource(id = R.drawable.ic_event), EMPTY_STRING, tint = Color.White)
        }
      }
    },
  )
}
