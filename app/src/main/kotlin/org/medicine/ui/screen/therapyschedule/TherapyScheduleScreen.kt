package org.medicine.ui.screen.therapyschedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import org.medicine.R
import org.medicine.common.ui.setSystemUiColors
import org.medicine.schedule.MedicalTherapySchedule
import org.medicine.ui.screen.therapyschedule.composable.ToolButton
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
  val coroutineScope = rememberCoroutineScope()
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
            Column {
              MedicalTherapySchedule(
                modifier = Modifier.weight(weight = 1f, fill = true),
                startDate = therapy.startDate,
                endDate = therapy.endDate,
                medicines = therapy.medicines,
                deals = therapy.deals,
                dateOnClick = {},
                medicineOnClick = {},
                dealOnClick = {},
                dealsOnClick = {},
              )

              Box(
                modifier = Modifier
                  .background(
                    brush = Brush.verticalGradient(
                      colors = listOf(
                        MaterialTheme.colors.surface,
                        MaterialTheme.colors.background,
                      )
                    )
                  )
                  .fillMaxWidth()
                  .padding(16.dp),
                contentAlignment = Alignment.Center,
              ) {
                Row {
                  val toolButtonModifier = Modifier.padding(horizontal = 4.dp)

                  ToolButton(
                    modifier = toolButtonModifier,
                    imageVector = Icons.Filled.Edit,
                    text = "Редактировать",
                  ) {
                    // load therapy edit form
                    coroutineScope.launch {
                      scaffoldState.conceal()
                    }
                  }
                  ToolButton(
                    modifier = toolButtonModifier,
                    painter = painterResource(id = R.drawable.ic_pills),
                    text = "Медикаменты",
                  ) {
                    // load medication form
                    coroutineScope.launch {
                      scaffoldState.conceal()
                    }
                  }
                  ToolButton(
                    modifier = toolButtonModifier,
                    painter = painterResource(id = R.drawable.ic_event),
                    text = "События",
                  ) {
                    // load event form
                    coroutineScope.launch {
                      scaffoldState.conceal()
                    }
                  }
                }
              }
            }
          }
        }
      }
    },
    headerHeight = 0.dp,
    frontLayerElevation = 8.dp,
    frontLayerBackgroundColor = MaterialTheme.colors.background,
    frontLayerContent = {
      //
    },
    frontLayerShape = CutCornerShape(topStart = 32.dp),
  )
}
