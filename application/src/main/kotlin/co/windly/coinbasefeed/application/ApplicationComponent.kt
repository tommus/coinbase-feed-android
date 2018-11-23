package co.windly.coinbasefeed.application

import co.windly.coinbasefeed.utility.debug.StethoModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, StethoModule::class])
interface ApplicationComponent {

  //region Injection

  fun inject(application: CoinbaseFeedApplication)

  //endregion

  //region Component Builder

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: CoinbaseFeedApplication): Builder

    fun build(): ApplicationComponent
  }

  //endregion

  //region Component Provider

  interface ComponentProvider {

    val applicationComponent: ApplicationComponent
  }

  //endregion
}
