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
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.delay
import org.medicine.common.exception.UnimplementedCallbackException
import org.medicine.common.ui.setSystemUiColors
import org.medicine.navigation.Destination
import org.medicine.navigation.Route
import org.medicine.navigation.navigate
import org.medicine.tools.time.toLocalDate
import org.medicine.tools.time.toLong
import org.medicine.ui.screen.therapyform.composable.TherapyDeleteSuccessful
import org.medicine.ui.screen.therapyform.composable.TherapyForm
import org.medicine.ui.screen.therapyform.composable.TherapySaveSuccessful
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent
import org.medicine.ui.screen.therapyform.model.TherapyFormModel
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import org.medicine.ui.stub.data.stubTherapyForm
import org.medicine.ui.theme.MedicalTherapyTheme
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */

@Composable
fun TherapyFormScreen(navController: NavController, viewModel: TherapyFormViewModel) {
  val uiState = viewModel.uiState

  rememberSystemUiController()
    .setSystemUiColors(
      MaterialTheme.colors.background,
      MaterialTheme.colors.background
    )

  TherapyFormView(
    uiState,
    { model -> viewModel.obtainIntent(TherapyFormIntent.FillTherapy(model)) },
    { therapyId, therapyForm -> viewModel.obtainIntent(TherapyFormIntent.CreateOrSaveTherapy(therapyId, therapyForm)) },
    { therapyId -> viewModel.obtainIntent(TherapyFormIntent.DeleteTherapy(therapyId)) },
    { therapyId ->
      val options = NavOptions.Builder()
        .setPopUpTo(Route.TherapyForm.name, inclusive = true)
        .build()

      navController.navigate(Destination.TherapySchedule(therapyId), options)
    },
    { throw UnimplementedCallbackException() },
  )

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(TherapyFormIntent.EnterScreen)
  }
}

private typealias TherapyDateOnChange = (LocalDate) -> Unit

private const val SUCCESSFUL_INFO_TIMEOUT = 1000L

@Composable
internal fun TherapyFormView(
  uiState: TherapyFormViewState,
  therapyFormOnChange: (TherapyFormModel) -> Unit,
  createOrSaveTherapy: (Long?, TherapyFormModel) -> Unit,
  deleteTherapy: (Long) -> Unit,
  saveOnSuccessful: (Long) -> Unit,
  deleteOnSuccessful: () -> Unit,
) {
  val activity = LocalContext.current as AppCompatActivity

  fun showDatePickerDialog(date: LocalDate, callback: TherapyDateOnChange) {
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
            delay(SUCCESSFUL_INFO_TIMEOUT)
            saveOnSuccessful(it)
          },
        )
      }
      is TherapyFormViewState.DeleteOnSuccessful -> uiState.run {
        TherapyDeleteSuccessful(
          therapyId,
          callback = {
            delay(SUCCESSFUL_INFO_TIMEOUT)
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
