package co.windly.coinbasefeed.presentation.main.rate

import co.windly.coinbasefeed.domain.manager.FeedDomainManager
import co.windly.coinbasefeed.presentation.base.fragment.base.BaseFragmentPresenter
import co.windly.coinbasefeed.utility.log.WiLogger
import com.tinder.scarlet.Event
import com.tinder.scarlet.State
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ExchangeRatePresenter @Inject constructor(
    private val feedManager: FeedDomainManager
) : BaseFragmentPresenter<ExchangeRateView>() {

  //region Attach View

  override fun attachView(view: ExchangeRateView) {
    super.attachView(view)

    // Observe state & event & text.
    observeState()
    observeEvent()
    observeText()
  }

  //endregion

  //region State

  private fun observeState() {

    feedManager
        .observeState()
        .subscribe(
            { handleObserveStateSuccess(it) },
            { handleObserveStateError(it) }
        )
        .addTo(disposables)
  }

  private fun handleObserveStateSuccess(state: State) {

    // Log the fact.
    WiLogger.v("Received new state.")
    WiLogger.v("State: $state.")
  }

  private fun handleObserveStateError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while observing states.")
    WiLogger.e(throwable.localizedMessage)
  }

  //endregion

  //region Event

  private fun observeEvent() {

    feedManager
        .observeEvent()
        .subscribe(
            { handleObserveEventSuccess(it) },
            { handleObserveEventError(it) }
        )
        .addTo(disposables)
  }

  private fun handleObserveEventSuccess(event: Event) {

    // Log the fact.
    WiLogger.v("Received new event.")
    WiLogger.v("Event: $event.")
  }

  private fun handleObserveEventError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while observing events.")
    WiLogger.e(throwable.localizedMessage)
  }

  //endregion

  //region Text

  private fun observeText() {

    feedManager
        .observeText()
        .subscribe(
            { handleObserveTextSuccess(it) },
            { handleObserveTextError(it) }
        )
        .addTo(disposables)
  }

  private fun handleObserveTextSuccess(text: String) {

    // Log the fact.
    WiLogger.v("Received new text.")
    WiLogger.v("Text: $text.")
  }

  private fun handleObserveTextError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while observing texts.")
    WiLogger.e(throwable.localizedMessage)
  }

  //endregion
}
