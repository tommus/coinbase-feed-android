package co.windly.coinbasefeed.network.model.subscribe

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonValue

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
enum class ProductId(

  @JsonValue
  val text: String
) {

  BTC_USD("BTC-USD"),

  ETH_USD("ETH-USD"),

  LTC_USD("LTC-USD")
}
