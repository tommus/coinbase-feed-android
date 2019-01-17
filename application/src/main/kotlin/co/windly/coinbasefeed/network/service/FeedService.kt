package co.windly.coinbasefeed.network.service

import co.windly.coinbasefeed.network.model.heartbeat.Heartbeat
import co.windly.coinbasefeed.network.model.subscribe.Subscribe
import co.windly.coinbasefeed.network.model.ticker.Ticker
import com.tinder.scarlet.State
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Flowable

interface FeedService {

  //region Service Info

  @Receive
  fun observeEvent(): Flowable<WebSocket.Event>

  @Receive
  fun observeState(): Flowable<State>

  @Receive
  fun observeText(): Flowable<String>

  //endregion

  //region Subscription

  @Send
  fun subscribe(data: Subscribe)

  //endregion

  //region Ticker

  @Receive
  fun observeTicker(): Flowable<Ticker>

  //endregion

  //region Heartbeat

  @Receive
  fun observeHeartbeat(): Flowable<Heartbeat>

  //endregion
}
