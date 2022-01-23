package org.medicine.ui.screen.dealform.composable

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
import org.medicine.ui.screen.dealform.model.DealFormModel
import org.medicine.ui.stub.data.stubDealForm
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 21.01.2022 0:25.
 */

@Composable
fun DealFrom(
  dealId: Long?,
  deal: DealFormModel,
  nameOnChange: (String) -> Unit,
  descriptionOnChange: (String) -> Unit,
  dateOnClick: () -> Unit,
  timeOnClick: () -> Unit,
  createOrSaveDealOnClick: () -> Unit,
  deleteDealOnClick: () -> Unit,
) {
  val dateFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.dealDateFormat), Locale.getDefault())
  val timeFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.timeDateFormat), Locale.getDefault())

  SkelMedicalForm(
    fieldsCallback = { fieldModifier ->
      val focusManager = LocalFocusManager.current

      OutlinedTextField(
        modifier = fieldModifier,
        value = deal.name,
        label = { Text("Deal name") },
        isError = deal.failedFields.name,
        onValueChange = nameOnChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
      )
      OutlinedTextField(
        modifier = fieldModifier,
        value = deal.description,
        label = { Text("Information") },
        isError = deal.failedFields.description,
        onValueChange = descriptionOnChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
      )
      OutlinedTextField(
        modifier = fieldModifier.clickable { focusManager.clearFocus(); dateOnClick() },
        value = dateFormatter.format(deal.date),
        label = { Text("Date") },
        isError = deal.failedFields.date,
        onValueChange = { },
        enabled = false,
      )
      OutlinedTextField(
        modifier = fieldModifier.clickable { focusManager.clearFocus(); timeOnClick() },
        value = timeFormatter.format(deal.time),
        label = { Text("Time") },
        isError = deal.failedFields.time,
        onValueChange = { },
        enabled = false,
      )
    },
    controlsCallback = { controlModifier ->
      OutlinedButton(
        modifier = controlModifier,
        onClick = { createOrSaveDealOnClick() }
      ) {
        if (dealId != null) {
          Text(text = "Сохранить событие")
        } else {
          Text(text = "Создать событие")
        }
      }

      if (dealId != null) {
        Button(
          modifier = controlModifier,
          onClick = { deleteDealOnClick() }
        ) {
          Icon(Icons.Filled.Delete, EMPTY_STRING)
          Text(text = "Удалить событие ")
        }
      }
    },
  )
}


@Preview(showBackground = true)
@Composable
fun DealFormPreview() {
  MedicalTherapyTheme {
    DealFrom(
      dealId = null,
      deal = stubDealForm(),
      {},{},{},{},{},{},
    )
  }
}
