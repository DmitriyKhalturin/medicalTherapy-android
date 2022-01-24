package org.medicine.ui.screen.medicineform.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.medicine.R
import org.medicine.schedule.data.Medicine
import org.medicine.ui.common.composable.medicalform.SkelMedicalForm
import org.medicine.ui.screen.medicineform.model.MedicineFormModel
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
  val timeFormatter = DateTimeFormatter.ofPattern(stringResource(R.string.timeDateFormat), Locale.getDefault())

  SkelMedicalForm(
    medicineId,
    createOrSaveMedicine,
    deleteMedicine,
  ) {

  }
}
