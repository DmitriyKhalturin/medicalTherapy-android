package org.medicine.ui.screen.overview.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 8:30.
 */

@Composable
fun Therapies() {
  var tabSelectedIndex by remember { mutableStateOf(0) }

  Column {
    val therapiesTabs = TherapiesTab.values()

    TherapiesTabRow(
      tabs = therapiesTabs,
      tabSelectedIndex = tabSelectedIndex,
      tabOnClick = { index -> tabSelectedIndex = index }
    )

    therapiesTabs[tabSelectedIndex].content()
  }
}

@Preview(showBackground = true)
@Composable
fun TherapiesPreview() {
  MedicalTherapyTheme {
    Therapies()
  }
}
