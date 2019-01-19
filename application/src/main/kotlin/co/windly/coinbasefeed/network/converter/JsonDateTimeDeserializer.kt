package co.windly.coinbasefeed.network.converter

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.format.DateTimeFormat
import java.util.TimeZone

class JsonDateTimeDeserializer : JsonDeserializer<DateTime?>() {

  //region Configuration

  companion object {

    // Server time zone.
    val TIME_ZONE = TimeZone.getTimeZone("GMT+0100")

    // Time zone with offset.
    val TIME_ZONE_OFFSET = DateTimeZone.forTimeZone(TIME_ZONE)

    // Date time format.
    val DATE_TIME_FORMAT = "YYYY-MM-dd'T'HH:mm:ss.SSSSSS'Z'"

    // Date time formatter.
    val DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATE_TIME_FORMAT)
  }

  //endregion

  //region Deserialize

  override fun deserialize(parser: JsonParser, context: DeserializationContext?): DateTime? {

    // Retrieve value.
    val date = parser.text

    // Return null in case if there is not date specified.
    if (date.isBlank()) {
      return null
    }

    // Retrieve device's current time zone.
    val current = TimeZone.getDefault()

    // Retrieve a milliseconds offset (which takes into account daylight change).
    val offset = current.rawOffset

    // Otherwise parse date using specified formatter and timezone.
    return DATE_TIME_FORMATTER
      .parseDateTime(date)
      .withZone(TIME_ZONE_OFFSET)
      .plusMillis(offset)
  }

  //endregion
}
