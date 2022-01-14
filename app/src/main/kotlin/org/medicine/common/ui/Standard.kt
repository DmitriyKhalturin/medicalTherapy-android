package org.medicine.common.ui

import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 14.01.2022 11:25.
 */

fun SystemUiController.setSystemUiColors(statusBarColor: Color, navigationBarColor: Color) {
  setStatusBarColor(statusBarColor)
  setNavigationBarColor(navigationBarColor)
}
