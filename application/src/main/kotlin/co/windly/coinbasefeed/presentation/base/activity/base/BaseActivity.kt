package co.windly.coinbasefeed.presentation.base.activity.base

import android.os.Bundle
import co.windly.limbo.activity.base.LimboActivity
import dagger.android.AndroidInjection

abstract class BaseActivity<V : BaseActivityView, P : BaseActivityPresenter<V>> : LimboActivity<V, P>(),
    BaseActivityView {

  //region Lifecycle

  override fun onCreate(savedInstanceState: Bundle?) {

    // Inject dependencies.
    AndroidInjection.inject(this)

    super.onCreate(savedInstanceState)
  }

  //endregion
}
