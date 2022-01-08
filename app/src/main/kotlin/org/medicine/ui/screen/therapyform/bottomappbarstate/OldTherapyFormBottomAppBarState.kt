package org.medicine.ui.screen.therapyform.bottomappbarstate

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.medicine.navigation.bottomappbarstate.BottomAppBarState
import org.medicine.tools.EMPTY_STRING

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 07.01.2022 14:08.
 */
class OldTherapyFormBottomAppBarState(
  private val backOnClick: () -> Unit,
  private val deleteOnClick: () -> Unit,
) : BottomAppBarState {
  @Composable override fun statusBarColor(): Color = MaterialTheme.colors.background
  @Composable override fun navigationBarColor(): Color = MaterialTheme.colors.primaryVariant
  override val floatingActionButtonPosition: FabPosition = FabPosition.Center

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

  @Composable override fun BuildBottomAppBarContent(context: RowScope) = with(context) {
    IconButton(
      onClick = {
        deleteOnClick()
      },
      content = {
        Icon(Icons.Filled.Delete, EMPTY_STRING, tint = Color.White)
      }
    )
    Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
  }
}
