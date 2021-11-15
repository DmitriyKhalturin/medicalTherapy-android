package org.medicine.ui.screen.therapyform

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.navigation.RouteArgumentsName
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 6:03.
 */
@HiltViewModel
class TherapyFormViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
): BaseViewModel() {
  private val therapyId = requireNotNull(savedStateHandle.get<Long>(RouteArgumentsName.Id.name))
}
