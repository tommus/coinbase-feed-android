package co.windly.coinbasefeed.network.adapter

import co.windly.coinbasefeed.utility.debug.StethoLogger
import com.fasterxml.jackson.databind.ObjectMapper
import com.tinder.scarlet.Message
import com.tinder.scarlet.MessageAdapter
import com.tinder.scarlet.utils.getRawType
import java.lang.reflect.Type

class StethoMessageAdapter<T>(
  private val objectMapper: ObjectMapper,
  private val clazz: Class<T>
) : MessageAdapter<T> {

  //region Message

  override fun fromMessage(message: Message): T {
    return when (message) {
      is Message.Text -> {
        StethoLogger.reportFrameReceived(message.value)
        objectMapper.readValue(message.value, clazz)
      }
      is Message.Bytes -> objectMapper.readValue(message.value, clazz)
    }
  }

  override fun toMessage(data: T): Message {
    val body = objectMapper.writeValueAsString(data)
    StethoLogger.reportFrameSent(body)
    return Message.Text(body)
  }

  //endregion

  //region Factory

  class Factory constructor(private val objectMapper: ObjectMapper = DEFAULT_OBJECT_MAPPER) : MessageAdapter.Factory {

    //region Create

    override fun create(type: Type, annotations: Array<Annotation>): MessageAdapter<*> =
      StethoMessageAdapter(objectMapper, type.getRawType())

    //endregion

    //region Object Mapper

    companion object {
      private val DEFAULT_OBJECT_MAPPER = ObjectMapper()
    }

    //endregion
  }

  //endregion
}
