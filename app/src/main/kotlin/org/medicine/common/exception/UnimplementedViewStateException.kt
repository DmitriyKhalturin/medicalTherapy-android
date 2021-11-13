package org.medicine.common.exception

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 13.11.2021 7:44.
 */
class UnimplementedViewStateException(intent: Any, state: Any): Exception("State: $state received intent: $intent.")
