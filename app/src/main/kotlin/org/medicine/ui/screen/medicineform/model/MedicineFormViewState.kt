package org.medicine.ui.screen.medicineform.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.01.2022 19:00.
 */
sealed class MedicineFormViewState {
  object Initial : MedicineFormViewState()
  data class Medicine(val therapyId: Long, val medicineId: Long?, val medicine: MedicineFormModel) : MedicineFormViewState()
  object SaveOnSuccessful : MedicineFormViewState()
  object DeleteOnSuccessful : MedicineFormViewState()
}
