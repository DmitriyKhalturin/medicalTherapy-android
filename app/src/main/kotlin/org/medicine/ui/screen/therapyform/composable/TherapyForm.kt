package org.medicine.ui.screen.therapyform.composable

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
      MedicalInput(
        fieldModifier,
        "Наименования террапии",
        therapy.failedFields.name,
        therapy.name,
        nameOnChange,
      )

      MedicalInput(
        fieldModifier,
        "Информация",
        therapy.failedFields.description,
        therapy.description,
        descriptionOnChange,
      )

      MedicalClickableInput(
        fieldModifier,
        "Дата начала",
        therapy.failedFields.date,
        formatter.format(therapy.startDate),
        startDateOnClick,
      )

      MedicalClickableInput(
        fieldModifier,
        "Дата окончания",
        therapy.failedFields.date,
        formatter.format(therapy.endDate),
        endDateOnClick,
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
