package co.windly.coinbasefeed.presentation.splash

import android.content.Intent
import android.os.Bundle
import co.windly.coinbasefeed.R
import co.windly.coinbasefeed.presentation.base.activity.base.BaseActivity
import co.windly.coinbasefeed.presentation.main.MainActivity
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashView, SplashPresenter>(), SplashView {

  //region Ui

  override fun getLayout() = R.layout.activity_splash

  //endregion

  //region Presenter

  @Inject
  lateinit var splashPresenter: SplashPresenter

  override fun createPresenter(): SplashPresenter =
      splashPresenter

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
    super.onCreate(savedInstanceState)

    // Start counting the automatic continue timer.
    getPresenter().observeAutomaticContinue()
  }

  //endregion
}
