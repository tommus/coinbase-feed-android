package co.windly.coinbasefeed.network.model.ticker

import co.windly.coinbasefeed.network.model.subscribe.ProductId
import co.windly.coinbasefeed.network.model.subscribe.ProductId.BTC_USD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Ticker(

  @get:JsonProperty("type")
  var type: String = "",

  @get:JsonProperty("sequence")
  var sequence: Long = 0L,

  @get:JsonProperty("product_id")
  var product: ProductId = BTC_USD,

  @get:JsonProperty("price")
  var price: String = "",

  @get:JsonProperty("open_24h")
  var open24h: String = "",

  @get:JsonProperty("volume_24h")
  var volume24h: String = "",

  @get:JsonProperty("low_24h")
  var low24h: String = "",

  @get:JsonProperty("high_24h")
  var high24h: String = "",

  @get:JsonProperty("volume_30d")
  var volume30d: String = "",

  @get:JsonProperty("best_bid")
  var bestBid: String = "",

  @get:JsonProperty("best_ask")
  var bestAsk: String = "",

  @get:JsonProperty("side")
  var side: String = "",

  @get:JsonProperty("time")
  var time: String = "",

  @get:JsonProperty("trade_id")
  var tradeId: Long = 0L,

  @get:JsonProperty("last_size")
  var lastSize: String = ""
)
