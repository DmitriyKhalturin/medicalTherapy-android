package org.medicine.ui.screen.therapyform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.medicine.ui.screen.therapyform.composable.TherapyForm
import org.medicine.ui.screen.therapyform.model.TherapyFormIntent
import org.medicine.ui.screen.therapyform.model.TherapyFormViewState
import org.medicine.ui.theme.MedicalTherapyTheme

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

  TherapyFormView(
    navController,
    uiState,
  )

  LaunchedEffect(key1 = uiState) {
    viewModel.obtainIntent(TherapyFormIntent.EnterScreen)
  }
}

@Composable
fun TherapyFormView(
  navController: NavController,
  uiState: TherapyFormViewState,
) {
  when (uiState) {
    is TherapyFormViewState.Initial -> Unit
    is TherapyFormViewState.Therapy -> TherapyForm(uiState.therapyForm)
  }
}


@Preview(showBackground = true)
@Composable
fun TherapyFormViewPreview() {
  MedicalTherapyTheme {
    TherapyFormView(
      rememberNavController(),
      TherapyFormViewState.Initial,
    )
  }
}
