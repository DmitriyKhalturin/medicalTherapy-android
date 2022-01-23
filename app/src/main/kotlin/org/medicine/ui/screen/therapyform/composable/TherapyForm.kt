package org.medicine.ui.screen.therapyform.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.R
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.common.composable.medicalform.SkelMedicalForm
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.stub.data.stubTherapyForm
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
  therapyId: Long?,
  therapy: TherapyFormModel,
  nameOnChange: (String) -> Unit,
  descriptionOnChange: (String) -> Unit,
  startDateOnClick: () -> Unit,
  endDateOnClick: () -> Unit,
  createOrSaveTherapyOnClick: () -> Unit,
  deleteTherapyOnClick: () -> Unit,
) {
  val formatter = DateTimeFormatter.ofPattern(stringResource(R.string.therapyDateFormat), Locale.getDefault())

  SkelMedicalForm(
    fieldsCallback = { fieldModifier ->
      val focusManager = LocalFocusManager.current

      OutlinedTextField(
        modifier = fieldModifier,
        value = therapy.name,
        label = { Text("Therapy name") },
        isError = therapy.failedFields.name,
        onValueChange = nameOnChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
      )
      OutlinedTextField(
        modifier = fieldModifier,
        value = therapy.description,
        label = { Text("Information")},
        isError = therapy.failedFields.description,
        onValueChange = descriptionOnChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
      )
      OutlinedTextField(
        modifier = fieldModifier.clickable { focusManager.clearFocus(); startDateOnClick() },
        value = formatter.format(therapy.startDate),
        label = { Text("Start date") },
        isError = therapy.failedFields.date,
        onValueChange = { },
        enabled = false,
      )
      OutlinedTextField(
        modifier = fieldModifier.clickable { focusManager.clearFocus(); endDateOnClick() },
        value = formatter.format(therapy.endDate),
        label = { Text("End date") },
        isError = therapy.failedFields.date,
        onValueChange = { },
        enabled = false
      )
    },
    controlsCallback = { controlModifier ->
      OutlinedButton(
        modifier = controlModifier,
        onClick = { createOrSaveTherapyOnClick() }
      ) {
        if (therapyId != null) {
          Text(text = "Сохранить протокол лечения")
        } else {
          Text(text = "Создать протокол лечения")
        }
      }

      if (therapyId != null) {
        Button(
          modifier = controlModifier,
          onClick = { deleteTherapyOnClick() }
        ) {
          Icon(Icons.Filled.Delete, EMPTY_STRING)
          Text(text = "Удалить протокол лечения")
        }
      }
    },
  )
}


@Preview(showBackground = true)
@Composable
fun TherapyFormPreview() {
  val todayDate = LocalDate.now()

  MedicalTherapyTheme {
    TherapyForm(
      therapyId = null,
      therapy = stubTherapyForm(),
      {}, {}, {}, {}, {}, {},
    )
  }
}
