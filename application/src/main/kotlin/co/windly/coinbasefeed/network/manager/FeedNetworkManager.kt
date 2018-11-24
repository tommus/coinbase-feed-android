package co.windly.coinbasefeed.network.manager

import co.windly.coinbasefeed.network.service.FeedService
import com.tinder.scarlet.Event
import com.tinder.scarlet.State
import io.reactivex.Flowable
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.annotations.SchedulerSupport.IO
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@SchedulerSupport(value = IO)
class FeedNetworkManager @Inject constructor() {

  //region Service

  @Inject
  lateinit var service: FeedService

  //endregion

  //region Event & State

  fun observeEvent(): Flowable<Event> =
    service
      .observeEvent()
      .subscribeOn(Schedulers.io())

  fun observeState(): Flowable<State> =
    service
      .observeState()
      .subscribeOn(Schedulers.io())

  //endregion
}
