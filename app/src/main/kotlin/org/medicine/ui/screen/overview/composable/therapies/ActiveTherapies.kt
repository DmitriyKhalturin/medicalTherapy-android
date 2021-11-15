package org.medicine.ui.screen.overview.composable.therapies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.11.2021 9:57.
 */

@Composable
fun ActiveTherapies(
  therapies: List<TherapyModel>,
  openTherapyOnClick: (Long) -> Unit,
  createTherapyOnClick: () -> Unit,
) {
  val lazyListState = rememberLazyListState()

  Column(modifier = Modifier.fillMaxSize()) {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      state = lazyListState,
    ) {
      items(therapies) {
        //
      }
    }

    //
  }
}


@Preview
@Composable
fun ActiveTherapiesPreview() {
  MedicalTherapyTheme {
    ActiveTherapies(
      therapies = listOf(),
      openTherapyOnClick = { },
      createTherapyOnClick = { },
    )
  }
}
