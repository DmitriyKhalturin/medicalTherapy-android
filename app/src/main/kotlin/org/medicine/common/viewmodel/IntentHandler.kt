package org.medicine.common.viewmodel

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 7:16.
 */
interface IntentHandler<T> {
  fun obtainIntent(intent: T)
}
