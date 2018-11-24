package co.windly.coinbasefeed.presentation.splash

import dagger.Component

@Component
@SplashScope
interface SplashComponent {

  //region Injections

  fun inject(activity: SplashActivity)

  //endregion

  //region Component Builder

  @Component.Builder
  interface Builder {

    fun build(): SplashComponent
  }

  //endregion

  //region Component Provider

  interface ComponentProvider {

    val splashComponent: SplashComponent
  }

  //endregion
}
