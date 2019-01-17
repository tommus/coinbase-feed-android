package co.windly.coinbasefeed.network.model.subscribe

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonValue

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
enum class SubscriptionType(

  @JsonValue
  val text: String
) {

  SUBSCRIBE("subscribe"),

  UNSUBSCRIBE("unsubscribe")
}
