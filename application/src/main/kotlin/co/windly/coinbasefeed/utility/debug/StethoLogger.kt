package co.windly.coinbasefeed.utility.debug

import com.facebook.stetho.inspector.network.NetworkEventReporterImpl
import com.facebook.stetho.inspector.network.SimpleTextInspectorWebSocketFrame

object StethoLogger {

  //region Reporter

  private val REPORTER = NetworkEventReporterImpl.get()

  //endregion

  //region Connection

  fun reportConnectionCreated(url: String) {
    if (REPORTER.isEnabled) {
      REPORTER
        .webSocketCreated("", url)
    }
  }

  fun reportConnectionClosed() {
    if (REPORTER.isEnabled) {
      REPORTER
        .webSocketClosed("")
    }
  }

  //endregion

  //region Frame

  fun reportFrameSent(message: String) {
    if (REPORTER.isEnabled) {
      REPORTER
        .webSocketFrameSent(SimpleTextInspectorWebSocketFrame("", message))
    }
  }

  fun reportFrameReceived(message: String) {
    if (REPORTER.isEnabled) {
      REPORTER
        .webSocketFrameReceived(SimpleTextInspectorWebSocketFrame("", message))
    }
  }

  fun reportFrameError(message: String) {
    if (REPORTER.isEnabled) {
      REPORTER
        .webSocketFrameError("", message)
    }
  }

  //endregion
}
