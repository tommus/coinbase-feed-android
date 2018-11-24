package co.windly.coinbasefeed.presentation.main.rate

import android.os.Bundle
import co.windly.coinbasefeed.R
import co.windly.limbo.fragment.base.LimboFragment

class ExchangeRateFragment : LimboFragment<ExchangeRateView, ExchangeRatePresenter>(), ExchangeRateView {

  //region Fragment

  companion object {
    fun createInstance(): ExchangeRateFragment {

      // Prepare bundle.
      val bundle = Bundle()

      // Instantiate and return fragment.
      return ExchangeRateFragment().apply {
        arguments = bundle
      }
    }
  }

  //endregion

  //region Ui

  override fun getLayout() = R.layout.fragment_exchange_rate

  //endregion

  //region Presenter

  override fun createPresenter() = ExchangeRatePresenter()

  //endregion
}
