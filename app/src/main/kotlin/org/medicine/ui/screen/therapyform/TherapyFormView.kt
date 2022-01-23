package org.medicine.ui.screen.therapyform

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.CoroutineScope
import org.medicine.tools.time.toLocalDate
import org.medicine.tools.time.toLong
import org.medicine.ui.screen.therapyform.composable.TherapyDeleteSuccessful
import org.medicine.ui.screen.therapyform.composable.TherapyForm
import org.medicine.ui.screen.therapyform.composable.TherapySaveSuccessful
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import org.medicine.ui.stub.data.stubTherapyForm
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 18:08.
 */

@Composable
internal fun TherapyFormView(
  uiState: TherapyFormViewState,
  therapyFormOnChange: (TherapyFormModel) -> Unit,
  createOrSaveTherapy: (Long?, TherapyFormModel) -> Unit,
  deleteTherapy: (Long) -> Unit,
  saveOnSuccessful: suspend CoroutineScope.(Long) -> Unit,
  deleteOnSuccessful: suspend CoroutineScope.() -> Unit,
) {
  val activity = LocalContext.current as AppCompatActivity

  fun showDatePickerDialog(date: LocalDate, callback: (LocalDate) -> Unit) {
    val fragmentManager = activity.supportFragmentManager
    val builder = MaterialDatePicker.Builder.datePicker()
    val datePicker = builder.setSelection(date.toLong()).build()

    datePicker.addOnPositiveButtonClickListener {
      callback(it.toLocalDate())
    }
    datePicker.show(fragmentManager, null)
  }

  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopCenter,
  ) {
    when (uiState) {
      is TherapyFormViewState.Initial -> Unit
      is TherapyFormViewState.Therapy -> uiState.run {
        TherapyForm(
          therapyId,
          therapy,
          { therapyFormOnChange(therapy.copy(name = it)) },
          { therapyFormOnChange(therapy.copy(description = it)) },
          { showDatePickerDialog(therapy.startDate) { therapyFormOnChange(therapy.copy(startDate = it)) } },
          { showDatePickerDialog(therapy.endDate) { therapyFormOnChange(therapy.copy(endDate = it)) } },
          { createOrSaveTherapy(therapyId, therapy) },
          { therapyId?.let(deleteTherapy) },
        )
      }
      is TherapyFormViewState.SaveOnSuccessful -> uiState.run {
        TherapySaveSuccessful(
          therapyId,
          callback = {
            saveOnSuccessful(it)
          },
        )
      }
      is TherapyFormViewState.DeleteOnSuccessful -> uiState.run {
        TherapyDeleteSuccessful(
          therapyId,
          callback = {
            deleteOnSuccessful()
          },
        )
      }
    }
  }
}


@Preview(showBackground = true)
@Composable
fun TherapyFormViewPreview() {
  MedicalTherapyTheme {
    TherapyFormView(
      // TherapyFormViewState.Initial,
      TherapyFormViewState.Therapy(
        therapyId = null,
        therapy = stubTherapyForm(),
      ),
      // TherapyFormViewState.SavingSuccessful,
      // TherapyFormViewState.DeletingSuccessful,
      {}, { _, _ -> }, {}, {}, {},
    )
  }
}
