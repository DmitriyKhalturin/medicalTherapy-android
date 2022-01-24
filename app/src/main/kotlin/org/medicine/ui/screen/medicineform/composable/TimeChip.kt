package org.medicine.ui.screen.medicineform.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.medicine.R
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 25.01.2022 1:15.
 */

@Composable
fun TimeChip(
  value: LocalTime,
  removeTime: (LocalTime) -> Unit,
) {
  val timeFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.timeDateFormat), Locale.getDefault())

  Surface(
    modifier = Modifier.padding(8.dp),
    elevation = 4.dp,
    shape = RoundedCornerShape(percent = 50),
    border = BorderStroke(
      width = 1.dp,
      color = MaterialTheme.colors.primaryVariant,
    )
  ) {
    Row(
      modifier = Modifier.padding(start = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(text = timeFormatter.format(value))

      IconButton(onClick = {
        removeTime(value)
      }) {
        Icon(Icons.Filled.RemoveCircle, EMPTY_STRING)
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun TimeChipPreview() {
  MedicalTherapyTheme {
    TimeChip(value = LocalTime.now(), removeTime = {})
  }
}
