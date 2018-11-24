package co.windly.coinbasefeed.presentation.main

import dagger.Component

@Component
@MainScope
interface MainComponent {

  //region Injections

  fun inject(activity: MainActivity)

  //endregion

  //region Component Builder

  @Component.Builder
  interface Builder {

    fun build(): MainComponent
  }

  //endregion

  //region Component Provider

  interface ComponentProvider {

    val mainComponent: MainComponent
  }

  //endregion
}
