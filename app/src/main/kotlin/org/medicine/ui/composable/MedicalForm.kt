package org.medicine.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 21.01.2022 12:32.
 */

@Composable
fun MedicalForm(
  fieldsCallback: @Composable ColumnScope.(Modifier) -> Unit,
  controlsCallback: @Composable ColumnScope.(Modifier) -> Unit,
) {
  Column(
    modifier = Modifier.padding(16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(modifier = Modifier.weight(weight = 1f)) {
      val fieldModifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)

      fieldsCallback(fieldModifier)
    }

    Column {
      val controlModifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 16.dp)

      controlsCallback(controlModifier)
    }
  }
}
