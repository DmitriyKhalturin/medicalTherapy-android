package org.medicine.viewmodel

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import org.medicine.common.BaseViewModel
import org.medicine.navigation.RouteArgumentsName
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:40.
 */
@HiltViewModel
class DealFormViewModel @Inject constructor(
  savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
  private val dealId = requireNotNull(savedStateHandle.get<Long>(RouteArgumentsName.Id.name))
}
