package org.medicine.ui.screen.therapyform

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.material.datepicker.MaterialDatePicker
import org.medicine.ui.screen.therapyform.composable.TherapyForm
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalDate
import java.util.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */

@Composable
fun TherapyFormScreen(
  navController: NavController,
  viewModel: TherapyFormViewModel,
) {
  val uiState = viewModel.uiState

  val activity = LocalContext.current as AppCompatActivity

  TherapyFormView(
    navController,
    uiState,
    { showDatePickerDialog(activity.supportFragmentManager, it) },
    { viewModel.obtainIntent(TherapyFormIntent.SetTherapyForm(it)) },
  )

  LaunchedEffect(key1 = uiState) {
    viewModel.obtainIntent(TherapyFormIntent.EnterScreen)
  }
}

private fun showDatePickerDialog(fragmentManager: FragmentManager, callback: TherapyDateOnChange) {
  val builder = MaterialDatePicker.Builder.datePicker()
  val datePicker = builder.build()

  datePicker.addOnPositiveButtonClickListener {
    val calendar = Calendar.getInstance(TimeZone.getDefault())

    calendar.timeInMillis = it

    val date = LocalDate.from(calendar.toInstant())

    callback(date)
  }
  datePicker.show(fragmentManager, null)
}

private typealias TherapyDateOnChange = (LocalDate) -> Unit

@Composable
private fun TherapyFormView(
  navController: NavController,
  uiState: TherapyFormViewState,
  therapyDateOnChange: (TherapyDateOnChange) -> Unit,
  therapyFormOnChange: (TherapyFormModel) -> Unit,
) {
  when (uiState) {
    is TherapyFormViewState.Initial -> Unit
    is TherapyFormViewState.Therapy -> TherapyForm(
      uiState.therapyForm,
      { therapyFormOnChange(uiState.therapyForm.copy(name = it)) },
      { therapyFormOnChange(uiState.therapyForm.copy(description = it)) },
      { therapyDateOnChange { therapyFormOnChange(uiState.therapyForm.copy(startDate = it)) } },
      { therapyDateOnChange { therapyFormOnChange(uiState.therapyForm.copy(endDate = it)) } },
    )
  }
}


@Preview(showBackground = true)
@Composable
fun TherapyFormViewPreview() {
  MedicalTherapyTheme {
    TherapyFormView(
      rememberNavController(),
      TherapyFormViewState.Initial,
      {}, {},
    )
  }
}
