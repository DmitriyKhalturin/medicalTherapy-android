package org.medicine.ui.screen.therapyschedule

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.SystemUiController
import org.medicine.R
import org.medicine.schedule.MedicalTherapySchedule
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleIntent
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleViewState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 10.11.2021 23:59.
 */

@Composable
fun TherapyScheduleScreen(
  navController: NavController,
  systemUiController: SystemUiController,
  viewModel: TherapyScheduleViewModel,
) {
  val uiState = viewModel.uiState

  systemUiController.setStatusBarColor(MaterialTheme.colors.background)
  systemUiController.setNavigationBarColor(MaterialTheme.colors.primaryVariant)

  TherapyScheduleView(
    navController,
    uiState,
  )

  LaunchedEffect(key1 = viewModel) {
    viewModel.obtainIntent(TherapyScheduleIntent.EnterScreen)
  }
}

@Composable
fun TherapyScheduleView(
  navController: NavController,
  uiState: TherapyScheduleViewState,
) {
  Scaffold(
    floatingActionButtonPosition = FabPosition.End,
    isFloatingActionButtonDocked = true,
    floatingActionButton = {
      FloatingActionButton(onClick = { /*TODO*/ }) {
        Icon(Icons.Filled.ArrowBack, EMPTY_STRING)
      }
    },
    bottomBar = {
      BottomAppBar(cutoutShape = MaterialTheme.shapes.medium.copy(CornerSize(percent = 50))) {
        IconButton(onClick = { /*TODO*/ }) {
          Icon(Icons.Filled.Edit, EMPTY_STRING)
        }
        IconButton(onClick = { /*TODO*/ }) {
          Icon(painterResource(id = R.drawable.ic_pills), EMPTY_STRING)
        }
        IconButton(onClick = { /*TODO*/ }) {
          Icon(painterResource(id = R.drawable.ic_event), EMPTY_STRING)
        }
        Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
      }
    },
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
    ) {
      when (uiState) {
        is TherapyScheduleViewState.Initial -> Unit
        is TherapyScheduleViewState.Therapy -> uiState.run {
          Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            Text(
              modifier = Modifier.padding(16.dp),
              text = therapy.name,
            )

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
    }
  }
}
