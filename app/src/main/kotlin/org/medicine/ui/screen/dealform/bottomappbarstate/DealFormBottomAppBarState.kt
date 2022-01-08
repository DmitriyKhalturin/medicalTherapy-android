package org.medicine.ui.screen.dealform.bottomappbarstate

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.medicine.navigation.bottomappbarstate.BottomAppBarState

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 07.01.2022 14:08.
 */
class DealFormBottomAppBarState() : BottomAppBarState {
  @Composable override fun statusBarColor(): Color = MaterialTheme.colors.background
  @Composable override fun navigationBarColor(): Color = MaterialTheme.colors.background
  override val floatingActionButtonPosition: FabPosition = FabPosition.Center
  @Composable override fun BuildFloatingActionButton() = Unit
  override val isVisibleBottomAppBar: Boolean = false
  @Composable override fun BuildBottomAppBarContent(context: RowScope) = Unit
}
