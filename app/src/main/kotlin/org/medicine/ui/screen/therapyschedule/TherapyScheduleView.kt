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
import kotlinx.coroutines.launch
import org.medicine.schedule.MedicalTherapySchedule
import org.medicine.ui.screen.therapyschedule.composable.ScheduleEditorSelector
import org.medicine.ui.screen.therapyschedule.composable.editor.DealScheduleEditor
import org.medicine.ui.screen.therapyschedule.composable.editor.MedicineScheduleEditor
import org.medicine.ui.screen.therapyschedule.composable.editor.ScheduleEditorType
import org.medicine.ui.screen.therapyschedule.composable.editor.TherapyScheduleEditor
import org.medicine.ui.screen.therapyschedule.model.TherapyScheduleViewState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 18:09.
 */

internal val CUT_CORNER_SIZE = 32.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun TherapyScheduleView(
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

  val scheduleEditorType = remember { mutableStateOf(ScheduleEditorType.THERAPY) }

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

              ScheduleEditorSelector(
                scheduleEditorOnSelect = {
                  scheduleEditorType.value = it

                  coroutineScope.launch {
                    scaffoldState.conceal()
                  }
                }
              )
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
        when (scheduleEditorType.value) {
          ScheduleEditorType.THERAPY ->
            TherapyScheduleEditor(
              therapyId = therapyId,
              saveOnSuccessful = {
                refreshTherapy()
              },
              deleteOnSuccessful = {
                navigateUp()
              },
            )
          ScheduleEditorType.MEDICINE ->
            MedicineScheduleEditor(
              therapyId = therapyId,
              therapyOnRefresh = {
                refreshTherapy()
              },
            )
          ScheduleEditorType.DEAL ->
            DealScheduleEditor(
              therapyId = therapyId,
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
