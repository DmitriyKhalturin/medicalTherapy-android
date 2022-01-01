package org.medicine.ui.screen.therapyform.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.medicine.R
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 05.12.2021 23:33.
 */

@Composable
fun TherapyForm(
  therapyForm: TherapyFormModel,
  nameOnChange: (String) -> Unit,
  descriptionOnChange: (String) -> Unit,
  startDateOnClick: () -> Unit,
  endDateOnClick: () -> Unit,
  saveTherapyOnClick: () -> Unit,
) {
  val formatter = DateTimeFormatter.ofPattern(stringResource(R.string.therapyDateFormat), Locale.getDefault())

  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    val fieldModifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)

    OutlinedTextField(
      modifier = fieldModifier,
      value = therapyForm.name,
      onValueChange = nameOnChange
    )
    OutlinedTextField(
      modifier = fieldModifier,
      value = therapyForm.description,
      onValueChange = descriptionOnChange
    )
    OutlinedTextField(
      modifier = fieldModifier.clickable { startDateOnClick() },
      value = formatter.format(therapyForm.startDate),
      onValueChange = { },
      readOnly = true,
    )
    OutlinedTextField(
      modifier = fieldModifier.clickable { endDateOnClick() },
      value = formatter.format(therapyForm.endDate),
      onValueChange = { },
      readOnly = true
    )

    OutlinedButton(
      modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
      onClick = { saveTherapyOnClick() }
    ) {
      Text(text = "Сохранить протокол лечения")
    }
  }
}


@Preview(showBackground = true)
@Composable
fun TherapyFormPreview() {
  val todayDate = LocalDate.now()

  MedicalTherapyTheme {
    TherapyForm(
      therapyForm = TherapyFormModel(
        "First therapy.",
        EMPTY_STRING,
        todayDate,
        todayDate.plusDays(5),
      ),
      {}, {}, {}, {}, {},
    )
  }
}
