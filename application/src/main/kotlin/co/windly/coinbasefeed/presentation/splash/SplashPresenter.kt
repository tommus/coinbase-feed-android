package co.windly.coinbasefeed.presentation.splash

import co.windly.coinbasefeed.presentation.base.activity.base.BaseActivityPresenter
import co.windly.coinbasefeed.utility.log.WiLogger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject

class SplashPresenter @Inject constructor() : BaseActivityPresenter<SplashView>() {

  //region Automatic Continue

  object Configuration {
    const val AUTO_CONTINUE_DELAY = 3_000L
  }

  fun observeAutomaticContinue() {

    Observable
        .timer(Configuration.AUTO_CONTINUE_DELAY, MILLISECONDS)
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { this.handleObserveAutomaticContinueSuccess(it) },
            { this.handleObserveAutomaticContinueError(it) }
        )
        ?.let { addDisposable(it) }
  }

  private fun handleObserveAutomaticContinueSuccess(delay: Long) {

    // Log the fact.
    WiLogger.v("Automatic continue time passed: %d", delay)
    WiLogger.v("Navigating to main view.")

    // Navigate to main view.
    ifViewAttached { it.navigateToMainView() }
  }

  private fun handleObserveAutomaticContinueError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while processing the automatic continue delay.")
    WiLogger.e(throwable)

    // Navigate to main view anyway.
    ifViewAttached { it.navigateToMainView() }
  }

  //endregion
}
