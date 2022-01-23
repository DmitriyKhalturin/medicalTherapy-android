package org.medicine.ui.screen.therapyform

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import org.medicine.ui.common.composable.successfulinfo.ObjectDeleteSuccessful
import org.medicine.ui.common.composable.successfulinfo.ObjectSaveSuccessful
import org.medicine.ui.common.dialog.showDatePickerDialog
import org.medicine.ui.common.model.MedicalFormViewState
import org.medicine.ui.screen.therapyform.composable.TherapyForm
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.stub.data.stubTherapyForm
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 18:08.
 */

@Composable
internal fun TherapyFormView(
  uiState: MedicalFormViewState<TherapyFormModel>,
  therapyFormOnChange: (TherapyFormModel) -> Unit,
  createOrSaveTherapy: (Long?, TherapyFormModel) -> Unit,
  deleteTherapy: (Long) -> Unit,
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
        TherapyForm(
          objectId,
          `object`,
          { therapyFormOnChange(`object`.copy(name = it)) },
          { therapyFormOnChange(`object`.copy(description = it)) },
          { showDatePickerDialog(activity, `object`.startDate) { therapyFormOnChange(`object`.copy(startDate = it)) } },
          { showDatePickerDialog(activity, `object`.endDate) { therapyFormOnChange(`object`.copy(endDate = it)) } },
          { createOrSaveTherapy(objectId, `object`) },
          { objectId?.let(deleteTherapy) },
        )
      }
      is MedicalFormViewState.SaveOnSuccessful -> uiState.run {
        ObjectSaveSuccessful(
          objectId,
          callback = {
            saveOnSuccessful(it)
          },
        )
      }
      is MedicalFormViewState.DeleteOnSuccessful -> uiState.run {
        ObjectDeleteSuccessful(
          objectId,
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
      MedicalFormViewState.Object(
        objectId = null,
        `object` = stubTherapyForm(),
      ),
      // TherapyFormViewState.SavingSuccessful,
      // TherapyFormViewState.DeletingSuccessful,
      {}, { _, _ -> }, {}, {}, {},
    )
  }
}
