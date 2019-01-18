package co.windly.coinbasefeed.network.manager

import co.windly.coinbasefeed.network.model.heartbeat.Heartbeat
import co.windly.coinbasefeed.network.model.subscribe.Channel
import co.windly.coinbasefeed.network.model.subscribe.Channel.HEARTBEAT
import co.windly.coinbasefeed.network.model.subscribe.Channel.TICKER
import co.windly.coinbasefeed.network.model.subscribe.ProductId.BTC_USD
import co.windly.coinbasefeed.network.model.subscribe.Subscribe
import co.windly.coinbasefeed.network.model.subscribe.SubscriptionType.SUBSCRIBE
import co.windly.coinbasefeed.network.model.ticker.Ticker
import co.windly.coinbasefeed.network.module.WebSocketModule.Connection
import co.windly.coinbasefeed.network.service.FeedService
import co.windly.coinbasefeed.utility.debug.StethoLogger
import com.tinder.scarlet.State
import com.tinder.scarlet.WebSocket
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@SchedulerSupport(value = IO)
class FeedNetworkManager @Inject constructor() {

  //region Service

  @Inject
  lateinit var service: FeedService

  //endregion

  //region Event & State

  fun observeEvent(): Flowable<WebSocket.Event> =
    service
      .observeEvent()
      .subscribeOn(Schedulers.io())

  fun observeState(): Flowable<State> =
    service
      .observeState()
      .subscribeOn(Schedulers.io())
      .doOnNext {
        when (it) {
          is State.Connected -> {
            StethoLogger.reportConnectionCreated(Connection.URL)
          }
          is State.Disconnected -> {
            StethoLogger.reportConnectionClosed()
          }
        }
      }

  fun observeText(): Flowable<String> =
    service
      .observeText()
      .subscribeOn(Schedulers.io())

  //endregion

  //region Subscription

  fun subscribe(): Completable =
    Completable
      .fromAction {

        // Prepare model.
        val model = Subscribe(
          type = SUBSCRIBE,
          productIds = listOf(BTC_USD),
          channels = listOf(TICKER, HEARTBEAT)
        )

        // Send subscription request.
        service
          .subscribe(model)
      }
      .subscribeOn(Schedulers.io())

  //endregion

  //region Ticker

  fun observeTicker(): Flowable<Ticker> =
    service
      .observeTicker()
      .filter { it.type == Channel.TICKER.text }
      .subscribeOn(Schedulers.io())

  //endregion

  //region Heartbeart

  fun observeHeartbeat(): Flowable<Heartbeat> =
    service
      .observeHeartbeat()
      .filter { it.type == Channel.HEARTBEAT.text }
      .subscribeOn(Schedulers.io())

  //endregion
}
