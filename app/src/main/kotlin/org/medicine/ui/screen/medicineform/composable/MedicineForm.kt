package org.medicine.ui.screen.medicineform.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.medicine.R
import org.medicine.schedule.data.Medicine
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.common.composable.medicalform.MedicalClickableInput
import org.medicine.ui.common.composable.medicalform.MedicalInput
import org.medicine.ui.common.composable.medicalform.SkelMedicalForm
import org.medicine.ui.screen.medicineform.model.MedicineFormModel
import org.medicine.ui.stub.data.stubMedicineForm
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 20:03.
 */

@Composable
internal fun MedicineForm(
  medicineId: Long?,
  medicine: MedicineFormModel,
  nameOnChange: (String) -> Unit,
  descriptionOnChange: (String) -> Unit,
  typeOnChange: (Medicine.Type) -> Unit,
  dosageOnChange: (String) -> Unit,
  startDateOnChange: () -> Unit,
  endDateOnChange: () -> Unit,
  addTime: () -> Unit,
  removeTime: (LocalTime) -> Unit,
  createOrSaveMedicine: () -> Unit,
  deleteMedicine: () -> Unit,
) {
  val dateFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.dealDateFormat), Locale.getDefault())

  SkelMedicalForm(
    medicineId,
    createOrSaveMedicine,
    deleteMedicine,
  ) { fieldModifier ->
    MedicalInput(
      fieldModifier,
      "Наименование лечения",
      medicine.failedFields.name,
      medicine.name,
      nameOnChange,
    )

    MedicalInput(
      fieldModifier,
      "Информация",
      medicine.failedFields.description,
      medicine.description,
      descriptionOnChange,
    )

    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp),
      horizontalArrangement = Arrangement.SpaceAround,
    ) {
      MedicineTypeToggleButton(
        medicine.type,
        Medicine.Type.PILLS,
      ) {
        typeOnChange(it)
      }

      MedicineTypeToggleButton(
        medicine.type,
        Medicine.Type.INJECTION,
      ) {
        typeOnChange(it)
      }

      MedicineTypeToggleButton(
        medicine.type,
        Medicine.Type.LIQUID,
      ) {
        typeOnChange(it)
      }
    }

    MedicalInput(
      fieldModifier,
      "Доза",
      isError = false,
      medicine.dosage,
      dosageOnChange,
    )

    MedicalClickableInput(
      fieldModifier,
      "Дата начала",
      medicine.failedFields.date,
      dateFormatter.format(medicine.startDate),
      startDateOnChange,
    )

    MedicalClickableInput(
      fieldModifier,
      "Дата окончания",
      medicine.failedFields.date,
      dateFormatter.format(medicine.endDate),
      endDateOnChange,
    )

    Text(
      text = "Время примема:",
      modifier = Modifier.padding(top = 16.dp),
    )

    OutlinedButton(
      onClick = {
        addTime()
      },
      modifier = Modifier.fillMaxWidth(),
    ) {
      Icon(Icons.Filled.Add, EMPTY_STRING)

      Text(text = "Добавить время")
    }

    val lazyListState = rememberLazyListState()

    LazyRow(
      state = lazyListState,
      modifier = Modifier
        .height(86.dp)
        .padding(top = 16.dp),
    ) {
      items(medicine.times) { time ->
        TimeChip(
          time
        ) {
          removeTime(it)
        }
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun MedicineFormPreview() {
  MedicalTherapyTheme {
    MedicineForm(
      medicineId = null,
      medicine = stubMedicineForm(),
      {}, {}, {}, {}, {}, {}, {}, {}, {}, {},
    )
  }
}
