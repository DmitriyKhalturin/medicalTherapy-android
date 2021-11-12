package org.medicine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.medicine.common.BaseViewModel
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:39.
 */
class MedicationViewModel @AssistedInject constructor(
  @Assisted val medicationId: Long,
) : BaseViewModel() {

  class Factory(private val medicationId: Long) : ViewModelProvider.Factory {

    @Inject
    lateinit var builder: Builder

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = builder.build(medicationId) as T
  }

  @AssistedFactory
  interface Builder {
    fun build(medicationId: Long): MedicationViewModel
  }
}
