package co.windly.coinbasefeed.presentation.main

import android.os.Bundle
import co.windly.coinbasefeed.R
import co.windly.coinbasefeed.presentation.main.rate.ExchangeRateFragment
import co.windly.limbo.activity.fragment.LimboFragmentActivity
import javax.inject.Inject

class MainActivity : LimboFragmentActivity<MainView, MainPresenter>(), MainView, MainComponent.ComponentProvider {

  //region Component

  override lateinit var mainComponent: MainComponent

  //endregion

  //region Ui

  override fun getLayout() = R.layout.activity_main

  //endregion

  //region Presenter

  @Inject
  lateinit var mainPresenter: MainPresenter

  override fun createPresenter() = mainPresenter

  //endregion

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {

    // Initialize component.
    mainComponent = DaggerMainComponent.builder()
      .build()
    mainComponent.inject(this)

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
