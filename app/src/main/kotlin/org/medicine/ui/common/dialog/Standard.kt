package org.medicine.ui.common.dialog

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.*
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import org.medicine.tools.time.plusDay
import org.medicine.tools.time.toLocalDate
import org.medicine.tools.time.toMilliseconds
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 21:52.
 */

internal fun showDatePickerDialog(activity: AppCompatActivity, date: LocalDate, minValidDate: LocalDate? = null, maxValidDate: LocalDate? = null, callback: (LocalDate) -> Unit) {
  val constraints = CalendarConstraints.Builder()
  val validators = mutableListOf<CalendarConstraints.DateValidator>()

  minValidDate?.let {
    validators.add(DateValidatorPointForward.from(it.toMilliseconds()))
  }

  maxValidDate?.let {
    validators.add( DateValidatorPointBackward.before(it.toMilliseconds()))
  }

  if (validators.isNotEmpty()) {
    constraints.setValidator(CompositeDateValidator.allOf(validators))
  }

  val fragmentManager = activity.supportFragmentManager
  val builder = MaterialDatePicker.Builder
    .datePicker()
  val datePicker = builder.setSelection(date.toMilliseconds().plusDay())
    .setCalendarConstraints(constraints.build())
    .build()

  datePicker.addOnPositiveButtonClickListener {
    callback(it.toLocalDate())
  }
  datePicker.show(fragmentManager, null)
}

internal fun showTimePickerDialog(activity: AppCompatActivity, date: LocalTime, callback: (LocalTime) -> Unit) {
  val fragmentManager = activity.supportFragmentManager
  val builder = MaterialTimePicker.Builder()
  val timePicker = builder
    .setTimeFormat(CLOCK_24H)
    .setHour(date.hour)
    .setMinute(date.minute)
    .build()

  timePicker.addOnPositiveButtonClickListener {
    callback(LocalTime.of(timePicker.hour, timePicker.minute))
  }
  timePicker.show(fragmentManager, null)
}
