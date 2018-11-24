package co.windly.coinbasefeed.utility.log

import timber.log.Timber

object WiLogger {

  //endregion

  //region Initialization

  fun init(enable: Boolean) {
    if (enable) {
      Timber.plant(Timber.DebugTree())
    }
  }

  //endregion

  //region Verbose

  fun v(message: String, vararg objects: Any) {
    Timber.v(message, *objects)
  }

  fun v(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.v(throwable, message, *objects)
  }

  fun v(error: Throwable) {
    Timber.v(error)
  }

  //endregion

  //region Debug

  fun d(message: String, vararg objects: Any) {
    Timber.d(message, *objects)
  }

  fun d(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.d(throwable, message, *objects)
  }

  fun d(error: Throwable) {
    Timber.d(error)
  }

  //endregion

  //region Info

  fun i(message: String, vararg objects: Any) {
    Timber.i(message, *objects)
  }

  fun i(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.i(throwable, message, *objects)
  }

  fun i(error: Throwable) {
    Timber.i(error)
  }

  //endregion

  //region Warning

  fun w(message: String, vararg objects: Any) {
    Timber.w(message, *objects)
  }

  fun w(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.w(throwable, message, *objects)
  }

  fun w(error: Throwable) {
    Timber.w(error)
  }

  //endregion

  //region Error

  fun e(message: String, vararg objects: Any) {
    Timber.e(message, *objects)
  }

  fun e(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.e(throwable, message, *objects)
  }

  fun e(error: Throwable) {
    Timber.e(error)
  }

  //endregion

  //region What a Terrible Failure

  fun wtf(message: String, vararg objects: Any) {
    Timber.wtf(message, *objects)
  }

  fun wtf(throwable: Throwable, message: String, vararg objects: Any) {
    Timber.wtf(throwable, message, *objects)
  }

  fun wtf(error: Throwable) {
    Timber.wtf(error)
  }

  //endregion
}
