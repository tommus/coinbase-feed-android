package co.windly.coinbasefeed.network.model.heartbeat

import co.windly.coinbasefeed.network.converter.JsonDateTimeDeserializer
import co.windly.coinbasefeed.network.model.subscribe.Channel
import co.windly.coinbasefeed.network.model.subscribe.Channel.HEARTBEAT
import co.windly.coinbasefeed.network.model.subscribe.ProductId
import co.windly.coinbasefeed.network.model.subscribe.ProductId.BTC_USD
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.joda.time.DateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Heartbeat(

  @get:JsonProperty("type")
  var type: Channel = HEARTBEAT,

  @get:JsonProperty("sequence")
  var sequence: Long = 0L,

  @get:JsonProperty("last_trade_id")
  var lastTradeId: Long = 0L,

  @get:JsonProperty("product_id")
  var product: ProductId = BTC_USD,

  @get:JsonDeserialize(using = JsonDateTimeDeserializer::class)
  @get:JsonProperty("time")
  var time: DateTime? = null
)
