package org.medicine.ui.screen.dayschedule.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.medicine.R
import org.medicine.model.medicine.sourceToSchedule
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.dayschedule.model.DayScheduleModel
import org.medicine.ui.stub.data.stubDaySchedule
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 31.01.2022 8:39.
 */

@Composable
fun DaySchedule(daySchedule: DayScheduleModel) {
  Column(modifier = Modifier.fillMaxSize()) {
    Text(
      text = daySchedule.name,
      modifier = Modifier.fillMaxWidth(),
      textAlign = TextAlign.Center,
      fontSize = 20.sp,
    )
    Text(
      text = "(${daySchedule.date})",
      modifier = Modifier.fillMaxWidth(),
      textAlign = TextAlign.Center,
      fontStyle = FontStyle.Italic,
    )

    Text(
      text = "Медикаменты:",
      modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
    )

    Box(modifier = Modifier.padding(start = 16.dp)) {
      daySchedule.medicines.forEach {
        Row {
          Icon(
            painterResource(id = it.type.sourceToSchedule().iconResId),
            EMPTY_STRING,
            tint = MaterialTheme.colors.primary,
          )
          Text(text = it.name)
        }
      }
    }

    val timeFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.timeDateFormat), Locale.getDefault())

    Text(
      text = "События:",
      modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
    )

    Box(modifier = Modifier.padding(start = 16.dp)) {
      daySchedule.deals.forEach {
        Text(text = "${it.name} в ${timeFormatter.format(it.time)}")
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun DaySchedulePreview() {
  MedicalTherapyTheme {
    DaySchedule(daySchedule = stubDaySchedule())
  }
}
