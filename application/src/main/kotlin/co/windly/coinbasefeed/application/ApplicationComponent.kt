package co.windly.coinbasefeed.application

import co.windly.coinbasefeed.network.module.HttpModule
import co.windly.coinbasefeed.network.module.WebSocketModule
import co.windly.coinbasefeed.presentation.main.MainModule
import co.windly.coinbasefeed.presentation.splash.SplashModule
import co.windly.coinbasefeed.utility.debug.StethoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    HttpModule::class,
    MainModule::class,
    SplashModule::class,
    StethoModule::class,
    WebSocketModule::class
  ]
)
interface ApplicationComponent : AndroidInjector<CoinbaseFeed> {

  //region Component Builder

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: CoinbaseFeed): Builder

    fun build(): ApplicationComponent
  }

  //endregion

  //region Component Provider

  interface ComponentProvider {

    val applicationComponent: ApplicationComponent
  }

  //endregion
}
