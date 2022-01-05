package org.medicine.ui.screen.overview.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PrepareTherapies(therapyLoadingDone: () -> Unit) {
  Text(
    modifier = Modifier
      .clickable { therapyLoadingDone() }
      .padding(16.dp),
    text = "Загружаем протоколы",
  )
}
