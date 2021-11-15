package org.medicine.ui.screen.overview.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.medicine.tools.EMPTY_STRING
import org.medicine.ui.theme.MedicalTherapyTheme

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 8:30.
 */

@Composable
fun NoOneTherapies(createTherapyOnClick: () -> Unit) {
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Icon(
        Icons.Outlined.Info,
        EMPTY_STRING,
        modifier = Modifier.size(48.dp),
        tint = MaterialTheme.colors.primaryVariant
      )
      Text(text = "Список протоколов лечения пуст.", modifier = Modifier.padding(16.dp))
      Button(onClick = { createTherapyOnClick() }) {
        Icon(Icons.Filled.Add, EMPTY_STRING)
        Text(text = "Создать протокол лечения")
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun NoOneTherapiesPreview() {
  MedicalTherapyTheme {
    NoOneTherapies(createTherapyOnClick = { })
  }
}
