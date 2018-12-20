package co.windly.coinbasefeed.presentation.main.rate

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ExchangeRateModule {

  //region Contribution

  @ContributesAndroidInjector
  abstract fun contributeExchangeRateAndroidInjector(): ExchangeRateFragment

  //endregion
}
