package org.medicine.ui.common.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 23.01.2022 19:30.
 */
sealed class MedicalFormIntent<T> {
  class EnterScreen<T> : MedicalFormIntent<T>()
  data class FillForm <T>(val `object`: T) : MedicalFormIntent<T>()
  data class CreateOrSaveObject<T>(val objectId: Long?, val `object`: T) : MedicalFormIntent<T>()
  data class DeleteObject<T>(val objectId: Long) : MedicalFormIntent<T>()
}
