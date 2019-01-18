package co.windly.coinbasefeed.domain.manager

import co.windly.coinbasefeed.network.manager.FeedNetworkManager
import co.windly.coinbasefeed.network.model.heartbeat.Heartbeat
import co.windly.coinbasefeed.network.model.ticker.Ticker
import co.windly.coinbasefeed.persistence.manager.FeedPersistenceManager
import com.tinder.scarlet.State
import com.tinder.scarlet.WebSocket
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedDomainManager @Inject constructor() {

  //region Fields

  @Inject
  lateinit var network: FeedNetworkManager

  @Inject
  lateinit var persistence: FeedPersistenceManager

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

  fun startPollingTickers(): Completable =
    network
      .observeTicker()
      .flatMapCompletable { persistence.saveTicker(it) }

  fun observeTickers(): Flowable<List<Ticker>> =
    persistence
      .observeTickers()

  //endregion

  //region Heartbeat

  fun observeHeartbeat(): Flowable<Heartbeat> =
    network
      .observeHeartbeat()

  //endregion
}
