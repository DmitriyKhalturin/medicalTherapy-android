package org.medicine.ui.screen.overview.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 7:11.
 */
sealed class OverviewViewState {
  object Initial : OverviewViewState()
  object FetchingTherapies : OverviewViewState()
  object NoOneTherapies : OverviewViewState()
  data class Therapies(
    val activeTherapies: List<TherapyModel>,
    val archivedTherapies: List<TherapyModel>
  ) : OverviewViewState()
}
