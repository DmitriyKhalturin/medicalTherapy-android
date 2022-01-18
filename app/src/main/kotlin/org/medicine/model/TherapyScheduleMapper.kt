package org.medicine.model

import org.medicine.schedule.data.Deal
import org.medicine.schedule.data.Medicine
import org.medicine.source.database.entity.DealEntity
import org.medicine.source.database.entity.MedicineEntity
import java.time.LocalDate

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for MedicalTherapy on 12.01.2022 9:54.
 */
object TherapyScheduleMapper {

  fun mapMedicineToSchedule(medicines: List<MedicineEntity>): List<Medicine> = medicines.map {
    Medicine(
      it.id,
      it.name,
      (it.startDate..it.endDate),
      it.type.sourceToSchedule(),
    )
  }

  fun mapDealToSchedule(deals: List<DealEntity>): List<Deal> {
    val buffer = hashMapOf<LocalDate, Deal>()

    deals.forEach {
      val date = it.dateTime.toLocalDate()
      val value = buffer[date]

      buffer[date] = value?.copy(
        name = value.name,
        count = value.count + 1,
      ) ?: Deal(
        it.id,
        it.name,
        date,
        count = 1,
      )
    }

    return buffer.values.toList()
  }
}
