package co.windly.coinbasefeed.presentation.main.rate

import android.os.Bundle
import co.windly.coinbasefeed.R
import co.windly.coinbasefeed.presentation.base.fragment.base.BaseFragment
import javax.inject.Inject

class ExchangeRateFragment : BaseFragment<ExchangeRateView, ExchangeRatePresenter>(), ExchangeRateView {

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

  @Inject
  lateinit var exchangeRatePresenter: ExchangeRatePresenter

  override fun createPresenter(): ExchangeRatePresenter =
    exchangeRatePresenter

  //endregion
}
