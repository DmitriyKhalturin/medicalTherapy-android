package org.medicine.ui.screen.therapyform

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import org.medicine.common.exception.UnimplementedCallbackException
import org.medicine.common.ui.setSystemUiColors
import org.medicine.navigation.Destination
import org.medicine.navigation.Route
import org.medicine.navigation.navigate
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */

private const val SUCCESSFUL_INFO_TIMEOUT = 1000L

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

      delay(SUCCESSFUL_INFO_TIMEOUT)
      navController.navigate(Destination.TherapySchedule(therapyId), options)
    },
    { throw UnimplementedCallbackException() },
  )

  LaunchedEffect(viewModel) {
    viewModel.obtainIntent(TherapyFormIntent.EnterScreen)
  }
}
