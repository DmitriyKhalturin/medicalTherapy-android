package org.medicine.navigation.exception

import org.medicine.navigation.Route

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.11.2021 20:52.
 */
class DestinationShouldBeParcelable(val route: Route) : Exception()
