package org.medicine.navigation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import dagger.hilt.android.qualifiers.ApplicationContext
import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.navigation.Destination
import org.medicine.navigation.properties.DestinationProperty
import javax.inject.Inject

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 19:11.
 */
open class NavigationViewModel<T: Destination> : BaseViewModel() {

  var destination by DestinationProperty<T>()

  @SuppressLint("StaticFieldLeak")
  private var context: Context? = null

  @Inject
  fun injectContext(@ApplicationContext applicationContext: Context) {
    context = applicationContext
  }

  override fun onExceptionHandler(e: Throwable) {
    context?.also {
      Toast.makeText(it, "Exception: ${e.javaClass.simpleName}", LENGTH_SHORT).show()
      Log.e("CEH >>>", e.message, e)
    }
  }
}
