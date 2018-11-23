package co.windly.coinbasefeed.presentation.splash

import co.windly.coinbasefeed.R
import co.windly.limbo.activity.base.LimboActivity

class SplashActivity : LimboActivity<SplashView, SplashPresenter>(), SplashView {

  //region Ui

  override fun getLayout() = R.layout.activity_splash

  //endregion

  //region Presenter

  override fun createPresenter(): SplashPresenter {
    // TODO: Inject presenter and return existing instance.
    return SplashPresenter()
  }

  //endregion
}
