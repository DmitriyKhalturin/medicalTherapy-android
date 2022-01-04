package org.medicine

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import org.medicine.navigation.buildNavGraph
import org.medicine.ui.theme.MedicalTherapyTheme

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val navController = rememberNavController()
      val systemUiController = rememberSystemUiController()

      MedicalTherapyTheme {
        systemUiController.setStatusBarColor(color = MaterialTheme.colors.primaryVariant)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colors.background)

        NavHost(
          navController = navController,
          graph = buildNavGraph(navController),
        )
      }
    }
  }
}
