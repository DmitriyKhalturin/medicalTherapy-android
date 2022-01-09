package org.medicine.ui.screen.overview.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.medicine.ui.theme.MedicalTherapyTheme

@Composable
fun PrepareTherapies() {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(text = "Загружаем протоколы")
  }
}


@Preview(showBackground = true)
@Composable
fun PrepareTherapiesPreview() {
  MedicalTherapyTheme {
    PrepareTherapies()
  }
}
