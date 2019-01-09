package co.windly.coinbasefeed.network.service

import com.tinder.scarlet.Event
import com.tinder.scarlet.State
import com.tinder.scarlet.ws.Receive
import io.reactivex.Flowable

interface FeedService {

  //region Service Info

  @Receive
  fun observeEvent(): Flowable<Event>

  @Receive
  fun observeState(): Flowable<State>

  @Receive
  fun observeText(): Flowable<String>

  //endregion
}
