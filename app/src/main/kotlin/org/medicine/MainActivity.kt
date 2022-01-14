package org.medicine

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import org.medicine.common.ui.setSystemUiColors
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

        rememberSystemUiController()
          .setSystemUiColors(
            MaterialTheme.colors.background,
            MaterialTheme.colors.background
          )

        NavHost(
          modifier = Modifier.background(MaterialTheme.colors.background),
          navController = navController,
          graph = buildNavGraph(navController),
        )
      }
    }
  }
}
