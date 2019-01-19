package co.windly.coinbasefeed.network.converter

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.joda.money.CurrencyUnit
import org.joda.money.Money

class JsonMoneyDeserializer : JsonDeserializer<Money>() {

  //region Configuration

  companion object {

    val CURRENCY_BTC =
      CurrencyUnit.registerCurrency("BTC", -1, 8, false)
  }

  //endregion

  //region Deserializer

  override fun deserialize(parser: JsonParser, context: DeserializationContext?): Money {

    // Retrieve value.
    val money = parser.text

    // Convert value as double.
    val amount = money.toDouble()

    // Convert value as money.
    return Money.of(CURRENCY_BTC, amount)
  }

  //endregion
}
