package co.windly.coinbasefeed.utility.debug

import android.content.Context
import com.facebook.stetho.DumperPluginsProvider
import com.facebook.stetho.InspectorModulesProvider
import com.facebook.stetho.Stetho
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StethoModule {

  //region Stetho Initializer

  @Provides
  @Singleton
  internal fun provideStethoInitailizer(
      inspector: InspectorModulesProvider,
      dumper: DumperPluginsProvider,
      builder: Stetho.InitializerBuilder
  ): Stetho.Initializer =
      builder
          .enableWebKitInspector(inspector)
          .enableDumpapp(dumper)
          .build()

  //endregion

  //region Stetho Initializer Builder

  @Provides
  @Singleton
  internal fun provideStethoInitiailizerBuilder(context: Context): Stetho.InitializerBuilder =
      Stetho.newInitializerBuilder(context)

  //endregion

  //region Dumper Plugins Provider

  @Provides
  @Singleton
  internal fun provideDumperPluginsProvider(context: Context): DumperPluginsProvider =
      DumperPluginsProvider { Stetho.DefaultDumperPluginsBuilder(context).finish() }

  //endregion

  //region Inspector Modules Provider

  @Provides
  @Singleton
  internal fun provideInspectorModulesProvider(context: Context): InspectorModulesProvider =
      Stetho.defaultInspectorModulesProvider(context)

  //endregion
}
