package org.medicine.ui.screen.therapyschedule

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import java.time.LocalDate

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
  openDaySchedule: (LocalDate) -> Unit,
  therapyOnRefresh: () -> Unit,
) {
  val scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed)
  val coroutineScope = rememberCoroutineScope()
  val scheduleEditorType = remember { mutableStateOf(ScheduleEditorType.NONE) }

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
                dateOnClick = {
                  openDaySchedule(it)
                },
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
          else -> Unit
        }
      }
    },
    frontLayerShape = CutCornerShape(topStart = CUT_CORNER_SIZE),
  )
}
