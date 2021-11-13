package org.medicine.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * Created by Dmitriy Khalturin <dmitry.halturin.86@gmail.com>
 * for Medicine on 12.11.2021 11:36.
 */
open class BaseViewModel : ViewModel() {

  private val supervisor = SupervisorJob()

  protected val exceptionHandler = CoroutineExceptionHandler { _, e ->
    onExceptionHandler(e)
  }

  open fun onExceptionHandler(e: Throwable) = Unit

  protected val coroutineContext = (supervisor + exceptionHandler)

  protected inline fun launch(crossinline block: suspend CoroutineScope.() -> Unit) =
    viewModelScope.launch(coroutineContext) {
      block()
    }

  protected inline fun <T> async(crossinline block: suspend CoroutineScope.() -> T): Deferred<T> =
    viewModelScope.async(coroutineContext) {
      block()
    }

  @DelicateCoroutinesApi
  protected inline fun launchGlobal(crossinline block: suspend CoroutineScope.() -> Unit) =
    GlobalScope.launch(exceptionHandler) {
      block()
    }

  @DelicateCoroutinesApi
  protected inline fun <T> asyncGlobal(crossinline block: suspend CoroutineScope.() -> T): Deferred<T> =
    GlobalScope.async(exceptionHandler) {
      block()
    }

  // TODO: implement later: produce, actor, broadcast

  override fun onCleared() {
    coroutineContext.cancelChildren()
    super.onCleared()
  }
}
