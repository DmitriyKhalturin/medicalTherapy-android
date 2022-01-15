package org.medicine.navigation.viewmodel

import org.medicine.common.viewmodel.BaseViewModel
import org.medicine.navigation.Destination
import org.medicine.navigation.properties.DestinationProperty

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 15.01.2022 19:11.
 */
open class NavigationViewModel<T: Destination> : BaseViewModel() {

  var destination by DestinationProperty<T>()
}
