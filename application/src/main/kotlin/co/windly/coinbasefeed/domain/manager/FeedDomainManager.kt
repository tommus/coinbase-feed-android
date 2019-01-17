package co.windly.coinbasefeed.domain.manager

import co.windly.coinbasefeed.network.manager.FeedNetworkManager
import co.windly.coinbasefeed.network.model.ticker.Ticker
import com.tinder.scarlet.State
import com.tinder.scarlet.WebSocket
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class FeedDomainManager @Inject constructor() {

  //region Fields

  @Inject
  lateinit var network: FeedNetworkManager

  //endregion

  //region Event & State

  fun observeEvent(): Flowable<WebSocket.Event> =
    network
      .observeEvent()

  fun observeState(): Flowable<State> =
    network
      .observeState()

  fun observeText(): Flowable<String> =
    network
      .observeText()

  //endregion

  //region Subscription

  fun subscribe(): Completable =
    network
      .subscribe()

  //endregion

  //region Ticker

  fun observeTicker(): Flowable<Ticker> =
    network
      .observeTicker()

  //endregion
}
