package org.medicine.ui.screen.therapyschedule.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.medicine.R
import org.medicine.ui.screen.therapyschedule.composable.form.ScheduleEditorType
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 10:12.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScheduleEditorSelector(
  scaffoldState: BackdropScaffoldState,
  scheduleEditorType: MutableState<ScheduleEditorType>,
) {
  val coroutineScope = rememberCoroutineScope()

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
        scheduleEditorType.value = ScheduleEditorType.THERAPY

        coroutineScope.launch {
          scaffoldState.conceal()
        }
      }
      ToolButton(
        modifier = toolButtonModifier,
        painter = painterResource(id = R.drawable.ic_pills),
        text = "Медикаменты",
      ) {
        scheduleEditorType.value = ScheduleEditorType.MEDICINE

        coroutineScope.launch {
          scaffoldState.conceal()
        }
      }
      ToolButton(
        modifier = toolButtonModifier,
        painter = painterResource(id = R.drawable.ic_event),
        text = "События",
      ) {
        scheduleEditorType.value = ScheduleEditorType.DEAL

        coroutineScope.launch {
          scaffoldState.conceal()
        }
      }
    }
  }
}


@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ScheduleEditorSelectorPreview() {
  MedicalTherapyTheme {
    ScheduleEditorSelector(
      scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed),
      scheduleEditorType = remember { mutableStateOf(ScheduleEditorType.THERAPY) },
    )
  }
}
