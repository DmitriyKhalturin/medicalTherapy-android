package org.medicine.ui.screen.overview.composable.therapies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.screen.overview.composable.therapies.item.ActiveTherapyItem
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.stub.data.stubActiveTherapies
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.11.2021 9:57.
 */

@Composable
fun ActiveTherapies(
  modifier: Modifier = Modifier,
  therapies: List<TherapyModel>,
  openTherapyOnClick: (Long) -> Unit,
  createTherapyOnClick: () -> Unit,
) {
  val lazyListState = rememberLazyListState()

  Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    LazyColumn(
      modifier = Modifier.weight(1f),
      state = lazyListState,
    ) {
      items(therapies) { therapy ->
        ActiveTherapyItem(therapy = therapy, openTherapyOnClick = openTherapyOnClick)
      }
    }

    Button(
      modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
      onClick = { createTherapyOnClick() }
    ) {
      Icon(Icons.Filled.Add, EMPTY_STRING)
      Text(text = "Создать протокол лечения")
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ActiveTherapiesPreview() {
  MedicalTherapyTheme {
    ActiveTherapies(
      therapies = stubActiveTherapies(),
      openTherapyOnClick = { },
      createTherapyOnClick = { },
    )
  }
}