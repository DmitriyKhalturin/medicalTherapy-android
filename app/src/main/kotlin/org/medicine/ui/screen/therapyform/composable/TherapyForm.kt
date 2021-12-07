package org.medicine.ui.screen.therapyform.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
) {
  val formatter = DateTimeFormatter.ofPattern(stringResource(R.string.dateFormat), Locale.getDefault())

  Column {
    OutlinedTextField(value = therapyForm.name, onValueChange = nameOnChange)
    OutlinedTextField(value = therapyForm.description, onValueChange = descriptionOnChange)
    OutlinedTextField(
      value = formatter.format(therapyForm.startDate),
      onValueChange = { },
      readOnly = true,
      modifier = Modifier.clickable { startDateOnClick() },
    )
    OutlinedTextField(
      value = formatter.format(therapyForm.endDate),
      onValueChange = { },
      readOnly = true,
      modifier = Modifier.clickable { endDateOnClick() }
    )
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
      {}, {}, {}, {},
    )
  }
}
