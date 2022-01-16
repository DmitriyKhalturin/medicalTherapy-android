package org.medicine.ui.screen.medicineform.model

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 16.01.2022 18:57.
 */
sealed class MedicineFormIntent {
  object EnterScreen : MedicineFormIntent()
}
