package org.medicine.ui.screen.applicationinfo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.SystemUiController

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:01.
 */

@Composable
fun ApplicationInfoScreen(
  systemUiController: SystemUiController,
  viewModel: ApplicationInfoViewModel,
) {
  systemUiController.setStatusBarColor(MaterialTheme.colors.background)
  systemUiController.setNavigationBarColor(MaterialTheme.colors.background)

  Text(
    modifier = Modifier.fillMaxSize(),
    text = "Описание программы. Далее вставим фич-реквестре.",
  )
}
