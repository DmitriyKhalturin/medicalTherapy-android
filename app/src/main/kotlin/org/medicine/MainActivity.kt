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

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:03.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      MedicalTherapyTheme {
        val navController = rememberNavController()
        val systemUiController = rememberSystemUiController()

        systemUiController.setStatusBarColor(color = MaterialTheme.colors.background)
        systemUiController.setNavigationBarColor(color = MaterialTheme.colors.background)

        NavHost(
          navController = navController,
          graph = buildNavGraph(navController, systemUiController),
        )
      }
    }
  }
}
