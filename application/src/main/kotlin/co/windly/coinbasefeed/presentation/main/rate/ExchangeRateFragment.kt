package co.windly.coinbasefeed.presentation.main.rate

import android.graphics.Color
import android.os.Bundle
import co.windly.coinbasefeed.R
import co.windly.coinbasefeed.network.model.ticker.Ticker
import co.windly.coinbasefeed.presentation.base.fragment.base.BaseFragment
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_exchange_rate.chartView
import org.joda.time.DateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.text.DecimalFormat
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

  //region Tickers

  object Configuration {

    // In minutes.
    val HISTORY_LENGHT = 5
  }

  override fun showTickerUpdates(tickers: List<Ticker>) {

    // Grab a current timestamp and calculate the first history timestamp.
    val now = DateTime.now()
    val start = now.minusMinutes(Configuration.HISTORY_LENGHT)

    // Filter the tickers to match the history gap.
    val priceEntries = tickers
      .filter { it.time.isNotEmpty() }
      .filter { DateTime.parse(it.time).isAfter(start) }
      .map { Entry(start.millisOfDay.toFloat(), it.price.toFloat()) }

    // Configure prices data set.
    val priceDataSet = LineDataSet(priceEntries, "BTC-USD")
      .apply {
        mode = LineDataSet.Mode.LINEAR
        setDrawFilled(true)
        isHighlightEnabled = false
        setDrawValues(false)
      }

    // Calculate min prices.
    val minPrice = priceEntries.minBy { it.y }?.y ?: 0.0f

    // Prepare a list of initial entries.
    val minEntries = listOf(
      Entry(start.millisOfDay.toFloat(), minPrice),
      Entry(now.millisOfDay.toFloat(), minPrice)
    )

    // Configure min prices data set.
    val minDataSet = LineDataSet(minEntries, "Min")
      .apply {
        mode = LineDataSet.Mode.STEPPED
        color = Color.argb(50, 140, 234, 255)
        isHighlightEnabled = false
        setDrawValues(false)
      }

    // Configure chart.
    with(chartView) {
      axisRight.isEnabled = false

      // Configure X axis.
      with(xAxis) {
        setValueFormatter { value, _ -> LocalTime.fromMillisOfDay(value.toLong()).toString(DateTimeFormat.shortTime()) }
        position = XAxis.XAxisPosition.BOTH_SIDED
        setDrawGridLines(false)
        granularity = 10000.0f
      }

      // Configure Y axis.
      with(axisLeft) {
        setValueFormatter { value, _ -> DecimalFormat("\$##.00").format(value) }
        setDrawGridLines(false)
        granularity = 0.01f
      }

      // Configure description.
      description.isEnabled = false

      // Configure touch options.
      setTouchEnabled(false)

      // Configure data.
      data = LineData(priceDataSet, minDataSet)

      // Invalidate chart.
      invalidate()
    }
  }

  //endregion
}
