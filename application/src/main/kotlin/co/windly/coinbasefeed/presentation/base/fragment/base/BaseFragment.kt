package co.windly.coinbasefeed.presentation.base.fragment.base

import android.content.Context
import co.windly.limbo.fragment.base.LimboFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseFragment<V : BaseFragmentView, P : BaseFragmentPresenter<V>> : LimboFragment<V, P>(),
  BaseFragmentView {

  //region Lifecycle

  override fun onAttach(context: Context?) {

    // Inject dependencies.
    AndroidSupportInjection.inject(this)

    super.onAttach(context)
  }

  //endregion
}
