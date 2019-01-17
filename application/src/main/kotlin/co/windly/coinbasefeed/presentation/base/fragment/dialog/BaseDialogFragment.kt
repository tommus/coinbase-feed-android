package co.windly.coinbasefeed.presentation.base.fragment.dialog

import android.content.Context
import co.windly.limbo.fragment.dialog.LimboDialogFragment
import dagger.android.support.AndroidSupportInjection

abstract class BaseDialogFragment<V : BaseDialogFragmentView, P : BaseDialogFragmentPresenter<V>> :
  LimboDialogFragment<V, P>(), BaseDialogFragmentView {

  //region Lifecycle

  override fun onAttach(context: Context?) {

    // Inject dependencies.
    AndroidSupportInjection.inject(this)

    super.onAttach(context)
  }

  //endregion
}
