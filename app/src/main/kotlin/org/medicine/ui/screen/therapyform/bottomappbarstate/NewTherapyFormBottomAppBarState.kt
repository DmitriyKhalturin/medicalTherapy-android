package org.medicine.ui.screen.therapyform.bottomappbarstate

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.medicine.navigation.bottomappbarstate.BottomAppBarState
import org.medicine.tools.EMPTY_STRING

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 07.01.2022 14:08.
 */
class NewTherapyFormBottomAppBarState(
  private val backOnClick: () -> Unit,
) : BottomAppBarState {
  @Composable override fun statusBarColor(): Color = MaterialTheme.colors.background
  @Composable override fun navigationBarColor(): Color = MaterialTheme.colors.primaryVariant
  override val floatingActionButtonPosition: FabPosition = FabPosition.End

  @Composable override fun BuildFloatingActionButton() {
    FloatingActionButton(
      onClick = {
        backOnClick()
      },
      content = {
        Icon(Icons.Filled.ArrowBack, EMPTY_STRING)
      }
    )
  }

  override val isVisibleBottomAppBar: Boolean = true
  @Composable override fun BuildBottomAppBarContent(context: RowScope) = Unit
}
