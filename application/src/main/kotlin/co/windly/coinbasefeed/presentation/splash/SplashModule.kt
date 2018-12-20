package co.windly.coinbasefeed.presentation.splash

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SplashModule {

  //region Contribution

  @ContributesAndroidInjector
  abstract fun contriuteSplashAndroidInjector(): SplashActivity

  //endregion
}
