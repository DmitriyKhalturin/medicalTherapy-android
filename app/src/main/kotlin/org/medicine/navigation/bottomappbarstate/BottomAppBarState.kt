package org.medicine.navigation.bottomappbarstate

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 07.01.2022 14:08.
 */
interface BottomAppBarState {
  @Composable fun statusBarColor(): Color
  @Composable fun navigationBarColor(): Color
  val floatingActionButtonPosition: FabPosition
  @Composable fun BuildFloatingActionButton()
  val isVisibleBottomAppBar: Boolean
  @Composable fun BuildBottomAppBarContent(context: RowScope)
}
