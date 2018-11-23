package co.windly.coinbasefeed.application

import android.app.Application
import com.facebook.stetho.Stetho
import javax.inject.Inject

class CoinbaseFeedApplication : Application(), ApplicationComponent.ComponentProvider {

  //region Components

  override lateinit var applicationComponent: ApplicationComponent

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
    // TODO:
  }

  //endregion
}
