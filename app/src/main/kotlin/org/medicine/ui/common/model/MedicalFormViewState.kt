package org.medicine.ui.common.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 19:30.
 */
sealed class MedicalFormViewState<T> {
  class Initial<T> : MedicalFormViewState<T>()
  data class Object<T>(val objectId: Long?, val `object`: T) : MedicalFormViewState<T>()
  data class SaveOnSuccessful<T>(val objectId: Long) : MedicalFormViewState<T>()
  data class DeleteOnSuccessful<T>(val objectId: Long) : MedicalFormViewState<T>()
}
