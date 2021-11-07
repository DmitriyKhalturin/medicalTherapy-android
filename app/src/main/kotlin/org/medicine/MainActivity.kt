package org.medicine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import org.medicine.ui.theme.MedicineTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MedicineTheme {
        Scaffold(backgroundColor = MaterialTheme.colors.background) {
        }
      }
    }
  }
}
