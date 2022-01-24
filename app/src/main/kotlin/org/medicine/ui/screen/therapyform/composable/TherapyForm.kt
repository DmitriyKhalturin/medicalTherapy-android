package org.medicine.ui.screen.therapyform.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.R
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
  startDateOnChange: () -> Unit,
  endDateOnChange: () -> Unit,
  createOrSaveTherapy: () -> Unit,
  deleteTherapy: () -> Unit,
) {
  val dateFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.therapyDateFormat), Locale.getDefault())

  SkelMedicalForm(
    therapyId,
    createOrSaveTherapy,
    deleteTherapy,
  ) { fieldModifier ->
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
      dateFormatter.format(therapy.startDate),
      startDateOnChange,
    )

    MedicalClickableInput(
      fieldModifier,
      "Дата окончания",
      therapy.failedFields.date,
      dateFormatter.format(therapy.endDate),
      endDateOnChange,
    )
  }
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
