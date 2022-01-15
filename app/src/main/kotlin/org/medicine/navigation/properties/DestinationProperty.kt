package org.medicine.navigation.properties

import org.medicine.navigation.Destination
import org.medicine.navigation.exception.DestinationNotInitialized
import org.medicine.navigation.viewmodel.NavigationViewModel
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 20:09.
 */
class DestinationProperty<T : Destination> : ReadWriteProperty<NavigationViewModel<T>, T> {

  private var value: T? = null

  override fun getValue(thisRef: NavigationViewModel<T>, property: KProperty<*>): T {
    return value ?: throw DestinationNotInitialized()
  }

  override fun setValue(thisRef: NavigationViewModel<T>, property: KProperty<*>, value: T) {
    this.value = value
  }
}
