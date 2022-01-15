package org.medicine.ui.screen.therapyschedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.medicine.common.ui.setSystemUiColors
import org.medicine.schedule.MedicalTherapySchedule
import org.medicine.ui.screen.therapyschedule.composable.*
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

internal val CUT_CORNER_SIZE = 32.dp

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

  val editFormType = remember { mutableStateOf(EditFormType.THERAPY) }

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

              Toolbar(scaffoldState, editFormType)
            }
          }
        }
      }
    },
    headerHeight = 0.dp,
    frontLayerElevation = 8.dp,
    frontLayerBackgroundColor = MaterialTheme.colors.background,
    frontLayerContent = {
      Box(
        modifier = Modifier
          .padding(top = CUT_CORNER_SIZE)
          .padding(16.dp)
      ) {
        when (editFormType.value) {
          EditFormType.THERAPY -> TherapyEditForm()
          EditFormType.MEDICINE -> MedicineEditForm()
          EditFormType.DEAL -> DealEditForm()
        }
      }
    },
    frontLayerShape = CutCornerShape(topStart = CUT_CORNER_SIZE),
  )
}
