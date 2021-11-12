package org.medicine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.medicine.common.BaseViewModel
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 11.11.2021 0:40.
 */
class DayScheduleViewModel @AssistedInject constructor(
  @Assisted val date: LocalDate,
) : BaseViewModel() {

  class Factory(private val date: LocalDate) : ViewModelProvider.Factory {

    @Inject
    lateinit var builder: Builder

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = builder.build(date) as T
  }

  @AssistedFactory
  interface Builder {
    fun build(date: LocalDate): DayScheduleViewModel
  }
}
