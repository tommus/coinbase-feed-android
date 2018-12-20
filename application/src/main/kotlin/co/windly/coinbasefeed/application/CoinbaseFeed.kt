package co.windly.coinbasefeed.application

import android.app.Activity
import android.app.Application
import co.windly.coinbasefeed.utility.log.WiLogger
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CoinbaseFeed : Application(), ApplicationComponent.ComponentProvider, HasActivityInjector {

  //region Components

  override lateinit var applicationComponent: ApplicationComponent

  //endregion

  //region Injector

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun activityInjector(): AndroidInjector<Activity> =
    activityInjector

  //endregion

  //region Lifecycle

  override fun onCreate() {
    super.onCreate()

    // Initialize dependency graph.
    initializeDependencyInjection()

    // Initialize components.
    initializeDebugBridge()
    initializeLogger()
  }

  //endregion

  //region Debug Bridge

  @Inject
  lateinit var stethoInitializer: Stetho.Initializer

  private fun initializeDebugBridge() {
    Stetho
      .initialize(stethoInitializer)
  }

  //endregion

  //region Dependency Injection

  private fun initializeDependencyInjection() {

    // Initialize application component.
    applicationComponent = DaggerApplicationComponent.builder()
      .application(this)
      .build()
    applicationComponent.inject(this)
  }

  //endregion

  //region Logger

  private fun initializeLogger() {
    WiLogger
      .init(true)
  }

  //endregion
}
