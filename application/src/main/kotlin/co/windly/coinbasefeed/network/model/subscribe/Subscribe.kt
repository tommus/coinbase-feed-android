package co.windly.coinbasefeed.network.model.subscribe

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class Subscribe(

  @get:JsonProperty("type")
  val type: SubscriptionType,

  @get:JsonProperty("product_ids")
  val productIds: List<ProductId>,

  @get:JsonProperty("channels")
  val channels: List<Channel>
)
