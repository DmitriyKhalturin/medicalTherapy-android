package org.medicine.ui.screen.overview.composable.therapies

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.ui.screen.overview.composable.therapies.item.ArchivedTherapyItem
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.stub.data.stubArchivedTherapies
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.11.2021 9:57.
 */

@Composable
fun ArchivedTherapies(
  modifier: Modifier = Modifier,
  therapies: List<TherapyModel>,
  openTherapyOnClick: (Long) -> Unit,
) {
  val lazyListState = rememberLazyListState()

  LazyColumn(
    modifier = modifier.fillMaxSize(),
    state = lazyListState,
  ) {
    items(therapies) { therapy ->
      ArchivedTherapyItem(therapy = therapy, openTherapyOnClick = openTherapyOnClick)
    }
  }
}


@Preview(showBackground = true)
@Composable
fun ArchivedTherapiesPreview() {
  MedicalTherapyTheme {
    ArchivedTherapies(
      therapies = stubArchivedTherapies(),
      openTherapyOnClick = {},
    )
  }
}
