package co.windly.coinbasefeed.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import co.windly.coinbasefeed.R
import co.windly.coinbasefeed.presentation.main.MainActivity
import co.windly.limbo.activity.base.LimboActivity
import javax.inject.Inject

class SplashActivity : LimboActivity<SplashView, SplashPresenter>(), SplashView, SplashComponent.ComponentProvider {

  //region Component

  override lateinit var splashComponent: SplashComponent

  //endregion

  //region Ui

  override fun getLayout() = R.layout.activity_splash

  //endregion

  //region Presenter

  @Inject
  lateinit var splashPresenter: SplashPresenter

  override fun createPresenter() = splashPresenter

  //endregion

  //region Navigation

  override fun navigateToMainView() {

    // Prepare intent.
    val intent = Intent(this, MainActivity::class.java)

    // Start activity.
    startActivity(intent)
  }

  //endregion

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {

    // Initialize component.
    splashComponent = DaggerSplashComponent.builder()
      .build()
    splashComponent.inject(this)

    super.onCreate(savedInstanceState)

    // Start counting the automatic continue timer.
    getPresenter().observeAutomaticContinue()
  }

  //endregion
}
