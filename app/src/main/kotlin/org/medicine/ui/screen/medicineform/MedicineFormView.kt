package org.medicine.ui.screen.medicineform

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import org.medicine.ui.common.composable.successfulinfo.ObjectDeleteSuccessful
import org.medicine.ui.common.composable.successfulinfo.ObjectSaveSuccessful
import org.medicine.ui.common.dialog.showDatePickerDialog
import org.medicine.ui.common.dialog.showTimePickerDialog
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.screen.medicineform.composable.MedicineForm
import org.medicine.ui.screen.medicineform.model.MedicineFormModel
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 18:11.
 */

@Composable
internal fun MedicineFormView(
  uiState: MedicalFormViewState<MedicineFormModel>,
  medicineFormOnChange: (MedicineFormModel) -> Unit,
  createOrSaveMedicine: (Long, Long?, MedicineFormModel) -> Unit,
  deleteMedicine: (Long) -> Unit,
  saveOnSuccessful: suspend CoroutineScope.(Long) -> Unit,
  deleteOnSuccessful: suspend CoroutineScope.() -> Unit,
) {
  val activity = LocalContext.current as AppCompatActivity

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopCenter,
  ) {
    when (uiState) {
      is MedicalFormViewState.Initial -> Unit
      is MedicalFormViewState.Object -> uiState.run {
        `object`.run {
          MedicineForm(
            objectId,
            medicine = this,
            { medicineFormOnChange(this.copy(name = it)) },
            { medicineFormOnChange(this.copy(description = it)) },
            { medicineFormOnChange(this.copy(type = it)) },
            { medicineFormOnChange(this.copy(dosage = it)) },
            { showDatePickerDialog(activity, startDate) { medicineFormOnChange(this.copy(startDate = it)) } },
            { showDatePickerDialog(activity, endDate) { medicineFormOnChange(this.copy(endDate = it)) } },
            { showTimePickerDialog(activity, LocalTime.now()) { medicineFormOnChange(this.copy(times = times + it)) } },
            { medicineFormOnChange(this.copy(times = times.exclude(it))) },
            { createOrSaveMedicine(therapyId, objectId, this) },
            { objectId?.let(deleteMedicine) },
          )
        }
      }
      is MedicalFormViewState.SaveOnSuccessful -> uiState.run {
        ObjectSaveSuccessful(
          objectId,
          callback = {
            saveOnSuccessful(it)
          }
        )
      }
      is MedicalFormViewState.DeleteOnSuccessful -> uiState.run {
        ObjectDeleteSuccessful(
          objectId,
          callback = {
            deleteOnSuccessful()
          }
        )
      }
    }
  }
}

internal fun List<LocalTime>.exclude(time: LocalTime): List<LocalTime> {
  val buffer = toMutableList()
  val iterator = buffer.iterator()

  while (iterator.hasNext()) {
    if (iterator.next() == time) {
      iterator.remove()
      break
    }
  }

  return buffer.toList()
}
