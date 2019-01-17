package co.windly.coinbasefeed.presentation.main.rate

import co.windly.coinbasefeed.domain.manager.FeedDomainManager
import co.windly.coinbasefeed.network.model.ticker.Ticker
import co.windly.coinbasefeed.presentation.base.fragment.base.BaseFragmentPresenter
import co.windly.coinbasefeed.utility.log.WiLogger
import com.tinder.scarlet.State
import com.tinder.scarlet.WebSocket
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
    WiLogger.e(throwable)
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

  private fun handleObserveEventSuccess(event: WebSocket.Event) {

    // Log the fact.
    WiLogger.v("Received new event.")
    WiLogger.v("Event: $event.")

    // If the connections has been established...
    if (event is WebSocket.Event.OnConnectionOpened<*>) {

      // ...subscribe to updates and observe ticker.
      subscribeToUpdates()
      observeTicker()
    }
  }

  private fun handleObserveEventError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while observing events.")
    WiLogger.e(throwable)
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
    WiLogger.e(throwable)
  }

  //endregion

  //region Subscription

  private fun subscribeToUpdates() {
    feedManager
      .subscribe()
      .subscribe(
        { handleSubscribeToUpdatesSuccess() },
        { handleSubscribeToUpdatesError(it) }
      )
      .addTo(disposables)
  }

  private fun handleSubscribeToUpdatesSuccess() {

    // Log the fact.
    WiLogger.v("Successfully subscribed to updates.")
  }

  private fun handleSubscribeToUpdatesError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while subscribing to updates.")
    WiLogger.e(throwable)
  }

  //endregion

  //region Ticker

  private fun observeTicker() {
    feedManager
      .observeTicker()
      .subscribe(
        { handleObserveTickerSuccess(it) },
        { handleObserveTickerError(it) }
      )
      .addTo(disposables)
  }

  private fun handleObserveTickerSuccess(ticker: Ticker) {

    // Log the fact.
    WiLogger.v("An update of ticker has been received.")
    WiLogger.v("Ticker: $ticker.")
  }

  private fun handleObserveTickerError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while observing ticker updates.")
    WiLogger.e(throwable)
  }

  //endregion
}
