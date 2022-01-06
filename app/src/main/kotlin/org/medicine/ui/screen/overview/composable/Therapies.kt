package org.medicine.ui.screen.overview.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.ui.screen.overview.composable.therapies.ActiveTherapies
import org.medicine.ui.screen.overview.composable.therapies.ArchivedTherapies
import org.medicine.ui.screen.overview.composable.therapies.TherapiesTabRow
import org.medicine.ui.screen.overview.model.TherapyModel
import org.medicine.ui.stub.data.stubActiveTherapies
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 8:30.
 */

@Composable
fun Therapies(
  activeTherapies: List<TherapyModel>,
  archivedTherapies: List<TherapyModel>,
  openTherapyOnClick: (Long) -> Unit,
  createTherapyOnClick: () -> Unit,
) {
  var tabSelectedIndex by remember { mutableStateOf(0) }

  Column(modifier = Modifier.fillMaxSize()) {
    TherapiesTabRow(
      tabs = TherapiesTab.values(),
      tabSelectedIndex = tabSelectedIndex,
      tabOnClick = { index -> tabSelectedIndex = index }
    )

    when (tabSelectedIndex) {
      TherapiesTab.Active.ordinal -> ActiveTherapies(
        therapies = activeTherapies,
        openTherapyOnClick = openTherapyOnClick,
        createTherapyOnClick = createTherapyOnClick,
      )
      TherapiesTab.Archived.ordinal -> ArchivedTherapies(
        therapies = archivedTherapies,
        openTherapyOnClick = openTherapyOnClick,
      )
    }
  }
}

enum class TherapiesTab(val title: String) {
  Active("Active"),
  Archived("Archived");
}


@Preview(showBackground = true)
@Composable
fun TherapiesPreview() {
  MedicalTherapyTheme {
    Therapies(
      activeTherapies = stubActiveTherapies(),
      archivedTherapies = emptyList(),
      {}, {},
    )
  }
}
