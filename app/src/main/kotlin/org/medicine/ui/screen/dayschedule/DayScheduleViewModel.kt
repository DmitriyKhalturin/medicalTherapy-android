package org.medicine.ui.screen.dayschedule

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:40.
 */
@HiltViewModel
class DayScheduleViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
) : BaseViewModel()
