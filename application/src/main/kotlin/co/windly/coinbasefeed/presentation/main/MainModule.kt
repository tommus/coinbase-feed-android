package co.windly.coinbasefeed.presentation.main

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainComponent::class])
abstract class MainModule {

  //region Binding

  @Binds
  @IntoMap
  @ClassKey(MainActivity::class)
  abstract fun bindMainActivityInjectorFactory(builder: MainComponent.Builder): AndroidInjector.Factory<*>

  //endregion
}
