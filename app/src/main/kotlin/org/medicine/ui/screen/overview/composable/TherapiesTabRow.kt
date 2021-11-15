package org.medicine.ui.screen.overview.composable

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.11.2021 9:33.
 */

@Composable
fun TherapiesTabRow(
  tabs: Array<TherapiesTab>,
  tabSelectedIndex: Int,
  tabOnClick: (Int) -> Unit
) {
  TabRow(selectedTabIndex = tabSelectedIndex) {
    tabs.forEachIndexed { index, therapiesTab ->
      Tab(selected = index == tabSelectedIndex, onClick = { tabOnClick(index) }) {
        Text(text = therapiesTab.title)
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun TherapiesTabRowPreview() {
  MedicalTherapyTheme {
    val tabSelectedIndex by remember { mutableStateOf(0) }
    TherapiesTabRow(tabs = TherapiesTab.values(), tabSelectedIndex = tabSelectedIndex, tabOnClick = { })
  }
}
