package co.windly.coinbasefeed.presentation.main.rate

import co.windly.coinbasefeed.network.model.ticker.Ticker
import co.windly.coinbasefeed.presentation.base.fragment.base.BaseFragmentView

interface ExchangeRateView : BaseFragmentView {

  //region Tickers

  fun showTickerUpdates(tickers: List<Ticker>)

  //endregion
}
