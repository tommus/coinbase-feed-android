package co.windly.coinbasefeed.presentation.main

import co.windly.coinbasefeed.presentation.main.rate.ExchangeRateModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(
    modules = [
      ExchangeRateModule::class
    ]
)
interface MainComponent : AndroidInjector<MainActivity> {

  //region Builder

  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<MainActivity>()

  //endregion
}
