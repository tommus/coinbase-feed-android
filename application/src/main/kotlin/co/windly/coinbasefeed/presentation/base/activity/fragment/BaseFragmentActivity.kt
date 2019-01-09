package co.windly.coinbasefeed.presentation.base.activity.fragment

import android.os.Bundle
import co.windly.limbo.activity.fragment.LimboFragmentActivity
import dagger.android.AndroidInjection

abstract class BaseFragmentActivity<V : BaseFragmentActivityView, P : BaseFragmentActivityPresenter<V>> :
    LimboFragmentActivity<V, P>(), BaseFragmentActivityView {

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {

    // Inject dependencies.
    AndroidInjection.inject(this)

    super.onCreate(savedInstanceState)
  }

  //endregion
}
