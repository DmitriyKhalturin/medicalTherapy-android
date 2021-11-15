package org.medicine.ui.screen.overview.composable

import androidx.compose.runtime.Composable

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.11.2021 9:47.
 */
enum class TherapiesTab(val title: String, val content: @Composable () -> Unit) {
  Active("Active", { ActiveTherapies() }),
  Archived("Archived", { ArchivedTherapies() });
}
