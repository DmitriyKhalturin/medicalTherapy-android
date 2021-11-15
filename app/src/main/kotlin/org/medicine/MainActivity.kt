package org.medicine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.medicine.navigation.buildNavGraph
import org.medicine.ui.theme.MedicalTherapyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val coroutineScope = rememberCoroutineScope()
      val navController = rememberNavController()

      MedicalTherapyTheme {
        NavHost(
          navController = navController,
          graph = buildNavGraph(navController),
        )
      }
    }
  }
}
