package co.windly.coinbasefeed.domain.manager

import co.windly.coinbasefeed.network.manager.FeedNetworkManager
import com.tinder.scarlet.Event
import com.tinder.scarlet.State
import io.reactivex.Flowable
import javax.inject.Inject

class FeedDomainManager @Inject constructor() {

  //region Fields

  @Inject
  lateinit var network: FeedNetworkManager

  //endregion

  //region Event & State

  fun observeEvent(): Flowable<Event> =
    network
      .observeEvent()

  fun observeState(): Flowable<State> =
    network
      .observeState()

  //endregion
}
