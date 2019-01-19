package co.windly.coinbasefeed.network.model.ticker

import co.windly.coinbasefeed.network.converter.JsonDateTimeDeserializer
import co.windly.coinbasefeed.network.converter.JsonMoneyDeserializer
import co.windly.coinbasefeed.network.converter.JsonMoneyDeserializer.Companion.CURRENCY_BTC
import co.windly.coinbasefeed.network.model.subscribe.Channel
import co.windly.coinbasefeed.network.model.subscribe.Channel.TICKER
import co.windly.coinbasefeed.network.model.subscribe.ProductId
import co.windly.coinbasefeed.network.model.subscribe.ProductId.BTC_USD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.joda.money.Money
import org.joda.money.Money.zero
import org.joda.time.DateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Ticker(

  @get:JsonProperty("type")
  var type: Channel = TICKER,

  @get:JsonProperty("sequence")
  var sequence: Long = 0L,

  @get:JsonProperty("product_id")
  var product: ProductId = BTC_USD,

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("price")
  var price: Money = zero(CURRENCY_BTC),

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("open_24h")
  var open24h: Money = zero(CURRENCY_BTC),

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("volume_24h")
  var volume24h: Money = zero(CURRENCY_BTC),

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("low_24h")
  var low24h: Money = zero(CURRENCY_BTC),

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("high_24h")
  var high24h: Money = zero(CURRENCY_BTC),

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("volume_30d")
  var volume30d: Money = zero(CURRENCY_BTC),

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("best_bid")
  var bestBid: Money = zero(CURRENCY_BTC),

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("best_ask")
  var bestAsk: Money = zero(CURRENCY_BTC),

  @get:JsonProperty("side")
  var side: String = "",

  @get:JsonDeserialize(using = JsonDateTimeDeserializer::class)
  @get:JsonProperty("time")
  var time: DateTime? = null,

  @get:JsonProperty("trade_id")
  var tradeId: Long = 0L,

  @get:JsonDeserialize(using = JsonMoneyDeserializer::class)
  @get:JsonProperty("last_size")
  var lastSize: Money = zero(CURRENCY_BTC)
)
