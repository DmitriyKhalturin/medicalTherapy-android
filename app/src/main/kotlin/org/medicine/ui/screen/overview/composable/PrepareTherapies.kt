package org.medicine.ui.screen.overview.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.medicine.ui.theme.MedicalTherapyTheme

@Composable
fun PrepareTherapies(nextStepOnClick: () -> Unit) {
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      modifier = Modifier
        .clickable { nextStepOnClick() }
        .padding(16.dp),
      text = "Загружаем протоколы"
    )
  }
}


@Preview(showBackground = true)
@Composable
fun PrepareTherapiesPreview() {
  MedicalTherapyTheme {
    PrepareTherapies(nextStepOnClick = {})
  }
}
