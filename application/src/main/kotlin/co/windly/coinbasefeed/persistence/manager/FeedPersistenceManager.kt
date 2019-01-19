package co.windly.coinbasefeed.persistence.manager

import co.windly.coinbasefeed.network.model.ticker.Ticker
import io.reactivex.BackpressureStrategy.LATEST
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.subjects.BehaviorSubject
import java.util.Collections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FeedPersistenceManager @Inject constructor() {

  //region Ticker

  private val inMemoryTickerDatabase =
    Collections.synchronizedList(mutableListOf<Ticker>())

  private val tickerListSubject = BehaviorSubject.create<List<Ticker>>()

  @SchedulerSupport(SchedulerSupport.NONE)
  fun saveTicker(ticker: Ticker): Completable =
    Completable
      .fromAction {
        inMemoryTickerDatabase += ticker
        tickerListSubject.onNext(inMemoryTickerDatabase)
      }

  @SchedulerSupport(SchedulerSupport.NONE)
  fun observeTickers(): Flowable<List<Ticker>> =
    tickerListSubject
      .toFlowable(LATEST)

  //endregion
}
