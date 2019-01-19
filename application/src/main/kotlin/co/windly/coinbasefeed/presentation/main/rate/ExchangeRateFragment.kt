package co.windly.coinbasefeed.presentation.main.rate

import android.graphics.Color
import android.os.Bundle
import co.windly.coinbasefeed.R
import co.windly.coinbasefeed.network.model.ticker.Ticker
import co.windly.coinbasefeed.presentation.base.fragment.base.BaseFragment
import com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import kotlinx.android.synthetic.main.fragment_exchange_rate.chartView
import org.joda.time.DateTime
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.text.DecimalFormat
import javax.inject.Inject
import kotlin.math.absoluteValue

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
    val HISTORY_LENGHT = 3

    // Date formatter.
    val DATE_FORMATTER = DateTimeFormat.shortTime()

    // Amount formatter.
    val AMOUNT_FORMATTER = "\$##.00"

    // Time formatter.
    val TIME_AXIS_FORMATTER = IAxisValueFormatter { value, axis ->
      LocalTime
        .fromMillisOfDay(value.toLong())
        .toString(DATE_FORMATTER)
    }

    // Money formatter.
    val MONEY_AXIS_FORMATTER = IAxisValueFormatter { value, axis ->
      DecimalFormat(Configuration.AMOUNT_FORMATTER)
        .format(value)
    }
  }

  override fun showTickerUpdates(tickers: List<Ticker>) {

    // Calculate time min and max values for X axis.
    val maxTime = DateTime.now()
    val minTime = maxTime.minusMinutes(Configuration.HISTORY_LENGHT)

    // Prepare rate entries for Y axis.
    val rateEntries = tickers
      .filter { it.time != null }
      .filter { it.time!!.isAfter(minTime) }
      .map { Entry(it.time!!.millisOfDay.toFloat(), it.price.amount.toFloat()) }

    // Prepare rate data set.
    val rateData = LineDataSet(rateEntries, "BTC-USD")
      .apply {
        mode = LineDataSet.Mode.LINEAR
        isHighlightEnabled = false
        setDrawValues(false)
      }

    // Find lowest rate value.
    val lowestRate = rateEntries.minBy { it.y }

    // Calculate granularity for X axis.
    val xAxisGranularity =
      (minTime.millisOfDay - maxTime.millisOfDay).absoluteValue / Configuration.HISTORY_LENGHT

    // Prepare time min and max entries for X axis.
    val minRateEntries = listOf(
      Entry(minTime.millisOfDay.toFloat(), lowestRate?.y ?: 0.0f),
      Entry(maxTime.millisOfDay.toFloat(), lowestRate?.y ?: 0.0f)
    )

    // Prepare time data set.
    val minRateData = LineDataSet(minRateEntries, "Min")
      .apply {
        mode = LineDataSet.Mode.STEPPED
        circleHoleColor = Color.TRANSPARENT
        color = Color.RED
        isHighlightEnabled = false
        setCircleColor(Color.RED)
        setDrawValues(false)
      }

    // Prepare chart.
    with(chartView) {

      // Configure right chart.
      axisRight.isEnabled = false

      // Configure description.
      description.isEnabled = false

      // Configure data.
      data = LineData(rateData, minRateData)
    }

    // Prepare X axis.
    with(chartView.xAxis) {
      position = BOTTOM
      granularity = xAxisGranularity.toFloat()
      valueFormatter = Configuration.TIME_AXIS_FORMATTER
      setDrawGridLines(false)
    }

    // Prepare Y axis.
    with(chartView.axisLeft) {
      granularity = 0.01f
      valueFormatter = Configuration.MONEY_AXIS_FORMATTER
      setDrawGridLines(false)
    }

    // Invalidate chart.
    chartView.invalidate()
  }

  //endregion
}
