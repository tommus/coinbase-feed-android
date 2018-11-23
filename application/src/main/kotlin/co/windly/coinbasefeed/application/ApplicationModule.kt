package co.windly.coinbasefeed.application

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

  //region Context

  @Provides
  internal fun provideApplicationContext(application: CoinbaseFeedApplication): Context =
    application

  //endregion
}
