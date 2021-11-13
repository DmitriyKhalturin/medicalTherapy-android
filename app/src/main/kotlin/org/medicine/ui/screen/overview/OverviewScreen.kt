package org.medicine.ui.screen.overview

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.medicine.ui.screen.overview.model.OverviewViewState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 5:50.
 */

@Composable
fun OverviewScreen(
  modifier: Modifier = Modifier,
  navController: NavController,
  viewModel: OverviewViewModel,
) {
  Overview(
    viewModel.uiState,
  )
}

@Composable
private fun Overview(
  uiState: OverviewViewState,
) {
  when (uiState) {
    is OverviewViewState.Initial -> Unit
    is OverviewViewState.FetchingTherapies -> Unit
    is OverviewViewState.NoOneTherapies -> Unit
    is OverviewViewState.Therapies -> Unit
  }
}
