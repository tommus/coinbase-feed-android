package co.windly.coinbasefeed.network.module

import co.windly.coinbasefeed.network.service.FeedService
import com.tinder.scarlet.MessageAdapter
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.StreamAdapter
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.messageadapter.gson.GsonMessageAdapter
import com.tinder.scarlet.retry.BackoffStrategy
import com.tinder.scarlet.retry.ExponentialWithJitterBackoffStrategy
import com.tinder.scarlet.streamadapter.rxjava2.RxJava2StreamAdapterFactory
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class WebSocketModule {

  //region Services

  @Provides
  @Singleton
  internal fun provideFeedService(scarlet: Scarlet) =
    scarlet.create<FeedService>()

  //endregion

  //region Scarlet

  @Provides
  @Singleton
  internal fun provideScarlet(
    builder: Scarlet.Builder,
    backoffStrategy: BackoffStrategy,
    messageAdapterFactory: MessageAdapter.Factory,
    streamAdapterFactory: StreamAdapter.Factory,
    webSocketFactory: WebSocket.Factory
  ) =
    builder
      .backoffStrategy(backoffStrategy)
      .addMessageAdapterFactory(messageAdapterFactory)
      .addStreamAdapterFactory(streamAdapterFactory)
      .webSocketFactory(webSocketFactory)

  //endregion

  //region Scarlet - Backoff Strategy

  object Duration {
    const val BASE = 1_000L
    const val MAX = 8_000L
  }

  @Provides
  @Singleton
  internal fun provideBackoffStrategy(): BackoffStrategy =
    ExponentialWithJitterBackoffStrategy(
      Duration.BASE,
      Duration.MAX
    )

  //endregion

  //region Scarlet - Message Adapter Factory

  @Provides
  @Singleton
  internal fun provideMessageAdapterFactory(): MessageAdapter.Factory =
    GsonMessageAdapter.Factory()

  //endregion

  //region Scarlet - Stream Adapter Factory

  @Provides
  @Singleton
  internal fun provideStreamAdapterFactory(): StreamAdapter.Factory =
    RxJava2StreamAdapterFactory()

  //endregion

  //region Web Socket Factory

  object Connection {
    const val URL = "wss://ws-feed.pro.coinbase.com"
  }

  @Provides
  @Singleton
  internal fun provideWebSocketFactory(okHttpClient: OkHttpClient) =
    okHttpClient
      .newWebSocketFactory(Connection.URL)

  //endregion
}
