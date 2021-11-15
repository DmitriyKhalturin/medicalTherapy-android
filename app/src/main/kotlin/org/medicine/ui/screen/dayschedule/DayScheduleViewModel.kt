package org.medicine.ui.screen.dayschedule

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.navigation.RouteArgumentsName
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:40.
 */
@HiltViewModel
class DayScheduleViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
  private val scheduleDate = savedStateHandle.get<LocalDate>(RouteArgumentsName.Date.name)

  init {
    if (scheduleDate == null) {
      val a = scheduleDate
    }
  }
}
