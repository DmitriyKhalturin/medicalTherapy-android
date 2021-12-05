package org.medicine.ui.screen.overview.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 7:21.
 */
sealed class OverviewIntent {
  object EnterScreen: OverviewIntent()
}
