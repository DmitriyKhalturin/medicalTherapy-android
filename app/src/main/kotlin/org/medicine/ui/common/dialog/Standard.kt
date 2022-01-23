package org.medicine.ui.common.dialog

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import org.medicine.tools.time.toLocalDate
import org.medicine.tools.time.toLong
import java.time.LocalDate
import java.time.LocalTime

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 21:52.
 */

internal fun showDatePickerDialog(activity: AppCompatActivity, date: LocalDate, callback: (LocalDate) -> Unit) {
  val fragmentManager = activity.supportFragmentManager
  val builder = MaterialDatePicker.Builder
    .datePicker()
  val datePicker = builder.setSelection(date.toLong())
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
    .setTimeFormat(TimeFormat.CLOCK_24H)
    .setHour(date.hour)
    .setMinute(date.minute)
    .build()

  timePicker.addOnPositiveButtonClickListener {
    callback(LocalTime.of(timePicker.hour, timePicker.minute))
  }
  timePicker.show(fragmentManager, null)
}
