package org.medicine.ui.screen.dealform.composable

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.R
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.common.composable.medicalform.MedicalClickableInput
import org.medicine.ui.common.composable.medicalform.MedicalInput
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
      MedicalInput(
        fieldModifier,
        "Наименование события",
        deal.failedFields.name,
        deal.name,
        nameOnChange,
      )

      MedicalInput(
        fieldModifier,
        "Информация",
        deal.failedFields.description,
        deal.description,
        descriptionOnChange,
      )

      MedicalClickableInput(
        fieldModifier,
        "Дата",
        deal.failedFields.date,
        dateFormatter.format(deal.date),
        dateOnClick,
      )

      MedicalClickableInput(
        fieldModifier,
        "Время",
        deal.failedFields.time,
        timeFormatter.format(deal.time),
        timeOnClick,
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
