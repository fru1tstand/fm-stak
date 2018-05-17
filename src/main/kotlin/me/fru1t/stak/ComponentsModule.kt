package me.fru1t.stak

import dagger.Binds
import dagger.Module
import me.fru1t.stak.components.startup.Startup
import me.fru1t.stak.components.startup.impl.StartupImpl
import javax.inject.Singleton

/** Provides default bindings for interface to implementation mappings. */
@Module
abstract class ComponentsModule {
  @Binds @Singleton abstract fun bindStartup(startupImpl: StartupImpl): Startup
}
