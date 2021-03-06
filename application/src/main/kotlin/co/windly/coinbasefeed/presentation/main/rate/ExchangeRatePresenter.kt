package co.windly.coinbasefeed.presentation.main.rate

import co.windly.coinbasefeed.domain.manager.FeedDomainManager
import co.windly.coinbasefeed.network.model.heartbeat.Heartbeat
import co.windly.coinbasefeed.network.model.ticker.Ticker
import co.windly.coinbasefeed.presentation.base.fragment.base.BaseFragmentPresenter
import co.windly.coinbasefeed.utility.log.WiLogger
import com.tinder.scarlet.State
import com.tinder.scarlet.WebSocket
import io.reactivex.android.schedulers.AndroidSchedulers
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

      // Subscribe to updates and start polling tickers.
      subscribeToUpdates()
      startPollingTickers()

      // Observe either ticker and heartbeat updates.
      observeTickers()
      observeHeartbeat()
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

  //region Ticker - Polling

  private fun startPollingTickers() {
    feedManager
      .startPollingTickers()
      .subscribe(
        { handleStartPollingTickersSuccess() },
        { handleStartPollingTickersError(it) }
      )
      .addTo(disposables)
  }

  private fun handleStartPollingTickersSuccess() {

    // Log the fact.
    WiLogger.v("Ticker updates polling has been started.")
  }

  private fun handleStartPollingTickersError(throwable: Throwable) {

    // Log an error.
    WiLogger.v("An error occurred while starting the ticker updates polling.")
    WiLogger.v(throwable)
  }

  //endregion

  //region Ticker - Observe

  private fun observeTickers() {
    feedManager
      .observeTickers()
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        { handleObserveTickerSuccess(it) },
        { handleObserveTickerError(it) }
      )
      .addTo(disposables)
  }

  private fun handleObserveTickerSuccess(tickers: List<Ticker>) {

    // Log the fact.
    WiLogger.v("An update of ticker list has been received.")
    WiLogger.v("Ticker: $tickers.")

    // Display the ticker updates at the chart.
    ifViewAttached { it.showTickerUpdates(tickers) }
  }

  private fun handleObserveTickerError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while observing ticker updates.")
    WiLogger.e(throwable)
  }

  //endregion

  //region Heartbeat

  private fun observeHeartbeat() {
    feedManager
      .observeHeartbeat()
      .subscribe(
        { handleObserveHeartbeatSuccess(it) },
        { handleObserveHeartbeatError(it) }
      )
      .addTo(disposables)
  }

  private fun handleObserveHeartbeatSuccess(heartbeat: Heartbeat) {

    // Log the fact.
    WiLogger.v("An update of heartbeat has been received.")
    WiLogger.v("Heartbeat: $heartbeat.")
  }

  private fun handleObserveHeartbeatError(throwable: Throwable) {

    // Log an error.
    WiLogger.e("An error occurred while observing heartbeat updates.")
    WiLogger.e(throwable)
  }

  //endregion
}
