package org.medicine.ui.screen.therapyschedule

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
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
    uiState,
    viewModel.destination.therapyId,
    { navController.navigateUp() },
    { viewModel.obtainIntent(TherapyScheduleIntent.RefreshTherapy) }
  )

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(TherapyScheduleIntent.EnterScreen)
  }
}

internal val CUT_CORNER_SIZE = 32.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TherapyScheduleView(
  uiState: TherapyScheduleViewState,
  therapyId: Long,
  navigateUp: () -> Unit,
  therapyOnRefresh: () -> Unit,
) {
  val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Concealed)
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(scaffoldState) {
    scaffoldState.reveal()
  }

  BackHandler(enabled = scaffoldState.isConcealed) {
    coroutineScope.launch {
      scaffoldState.reveal()
    }
  }

  fun refreshTherapy() {
    therapyOnRefresh()

    coroutineScope.launch {
      scaffoldState.reveal()
    }
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
    peekHeight = 8.dp,
    headerHeight = 0.dp,
    frontLayerElevation = 8.dp,
    frontLayerBackgroundColor = MaterialTheme.colors.background,
    frontLayerContent = {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .padding(top = CUT_CORNER_SIZE)
      ) {
        when (editFormType.value) {
          EditFormType.THERAPY ->
            TherapyEditForm(
              therapyId = therapyId,
              saveOnSuccessful = {
                refreshTherapy()
              },
              deleteOnSuccessful = {
                navigateUp()
              },
            )
          EditFormType.MEDICINE ->
            MedicineEditForm(
              therapyId = therapyId,
              medicineId = null,
              refreshTherapyCallback = {
                refreshTherapy()
              },
            )
          EditFormType.DEAL ->
            DealEditForm(
              therapyId = therapyId,
              dealId = null,
              therapyOnRefresh = {
                refreshTherapy()
              },
            )
        }
      }
    },
    frontLayerShape = CutCornerShape(topStart = CUT_CORNER_SIZE),
  )
}
