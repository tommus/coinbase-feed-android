package co.windly.coinbasefeed.presentation.main

import android.os.Bundle
import android.support.v4.app.Fragment
import co.windly.coinbasefeed.R
import co.windly.coinbasefeed.presentation.base.activity.fragment.BaseFragmentActivity
import co.windly.coinbasefeed.presentation.main.rate.ExchangeRateFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseFragmentActivity<MainView, MainPresenter>(), MainView, HasSupportFragmentInjector {

  //region Injector

  @Inject
  lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

  override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> =
      fragmentInjector

  //endregion

  //region Ui

  override fun getLayout() = R.layout.activity_main

  //endregion

  //region Presenter

  @Inject
  lateinit var mainPresenter: MainPresenter

  override fun createPresenter(): MainPresenter =
      mainPresenter

  //endregion

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    // Load fragment.
    loadFragment()
  }

  //endregion

  //region Fragment

  private fun loadFragment() {

    // Search for fragment.
    val fragment = findFragment(ExchangeRateFragment::class.java)

    // In case if fragment is not found - load new instance.
    if (fragment == null) {
      loadRootFragment(R.id.fragmentContainer, ExchangeRateFragment.createInstance())
    }
  }

  //endregion
}
