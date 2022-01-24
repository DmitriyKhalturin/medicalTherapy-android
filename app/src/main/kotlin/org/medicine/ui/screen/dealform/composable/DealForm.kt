package org.medicine.ui.screen.dealform.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.R
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
internal fun DealFrom(
  dealId: Long?,
  deal: DealFormModel,
  nameOnChange: (String) -> Unit,
  descriptionOnChange: (String) -> Unit,
  dateOnChange: () -> Unit,
  timeOnChange: () -> Unit,
  createOrSaveDeal: () -> Unit,
  deleteDeal: () -> Unit,
) {
  val dateFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.dealDateFormat), Locale.getDefault())
  val timeFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.timeDateFormat), Locale.getDefault())

  SkelMedicalForm(
    dealId,
    createOrSaveDeal,
    deleteDeal,
  ) { fieldModifier ->
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
      dateOnChange,
    )

    MedicalClickableInput(
      fieldModifier,
      "Время",
      deal.failedFields.time,
      timeFormatter.format(deal.time),
      timeOnChange,
    )
  }
}


@Preview(showBackground = true)
@Composable
fun DealFormPreview() {
  MedicalTherapyTheme {
    DealFrom(
      dealId = null,
      deal = stubDealForm(),
      {}, {}, {}, {}, {}, {},
    )
  }
}
