package co.windly.coinbasefeed.network.module

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Named
import javax.inject.Singleton

@Module
class HttpModule {

  //region OkHttp Client

  @Provides
  @Singleton
  internal fun provideOkHttpClient(builder: OkHttpClient.Builder) =
    builder
      .build()

  @Provides
  @Singleton
  internal fun provideOkHttpClientBuilder(
    @Named("logging-interceptor") interceptor: Interceptor
  ) =
    OkHttpClient.Builder()
      .connectTimeout(30, SECONDS)
      .readTimeout(30, SECONDS)
      .writeTimeout(30, SECONDS)
      .addInterceptor(interceptor)

  //endregion

  //region Logging

  @Provides
  @Singleton
  @Named("logging-interceptor")
  internal fun provideLoggingInterceptor(level: Level): Interceptor =
    HttpLoggingInterceptor()
      .setLevel(level)

  @Provides
  @Singleton
  internal fun provideLoggingLevel() =
    Level.BODY

  //endregion
}
