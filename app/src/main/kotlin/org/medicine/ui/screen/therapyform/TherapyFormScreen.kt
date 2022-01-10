package org.medicine.ui.screen.therapyform

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.android.material.datepicker.MaterialDatePicker
import org.medicine.tools.time.toLocalDate
import org.medicine.tools.time.toLong
import org.medicine.ui.screen.therapyform.composable.TherapyForm
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */

@Composable
fun TherapyFormScreen(
  navController: NavController,
  systemUiController: SystemUiController,
  viewModel: TherapyFormViewModel,
) {
  val uiState = viewModel.uiState

  systemUiController.setStatusBarColor(MaterialTheme.colors.background)
  systemUiController.setNavigationBarColor(MaterialTheme.colors.background)

  val activity = LocalContext.current as AppCompatActivity

  TherapyFormView(
    navController,
    uiState,
    { date, callback -> showDatePickerDialog(activity.supportFragmentManager, date, callback) },
    { viewModel.obtainIntent(TherapyFormIntent.FillTherapy(it)) },
    { therapyId, therapyForm -> viewModel.obtainIntent(TherapyFormIntent.SaveTherapy(therapyId, therapyForm)) },
    { therapyId -> viewModel.obtainIntent(TherapyFormIntent.DeleteTherapy(therapyId)) }
  )

  LaunchedEffect(key1 = viewModel) {
    viewModel.obtainIntent(TherapyFormIntent.EnterScreen)
  }
}

private fun showDatePickerDialog(fragmentManager: FragmentManager, date: LocalDate, callback: TherapyDateOnChange) {
  val builder = MaterialDatePicker.Builder.datePicker()
  val datePicker = builder.setSelection(date.toLong()).build()

  datePicker.addOnPositiveButtonClickListener {
    callback(it.toLocalDate())
  }
  datePicker.show(fragmentManager, null)
}

private typealias TherapyDateOnChange = (LocalDate) -> Unit

@Composable
private fun TherapyFormView(
  navController: NavController,
  uiState: TherapyFormViewState,
  therapyDateOnChange: (LocalDate, TherapyDateOnChange) -> Unit,
  therapyFormOnChange: (TherapyFormModel) -> Unit,
  saveTherapy: (Long?, TherapyFormModel) -> Unit,
  deleteTherapy: (Long) -> Unit,
) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.TopCenter,
  ) {
    when (uiState) {
      is TherapyFormViewState.Initial -> Unit
      is TherapyFormViewState.Therapy -> TherapyForm(
        uiState.therapyId,
        uiState.therapy,
        { therapyFormOnChange(uiState.therapy.copy(name = it)) },
        { therapyFormOnChange(uiState.therapy.copy(description = it)) },
        { therapyDateOnChange(uiState.therapy.startDate) { therapyFormOnChange(uiState.therapy.copy(startDate = it)) } },
        { therapyDateOnChange(uiState.therapy.endDate) { therapyFormOnChange(uiState.therapy.copy(endDate = it)) } },
        { saveTherapy(uiState.therapyId, uiState.therapy) },
        { uiState.therapyId?.let(deleteTherapy) },
      )
    }
  }
}


@Preview(showBackground = true)
@Composable
fun TherapyFormViewPreview() {
  MedicalTherapyTheme {
    TherapyFormView(
      rememberNavController(),
      TherapyFormViewState.Initial,
      { _, _ -> }, {}, { _, _ -> }, {},
    )
  }
}
