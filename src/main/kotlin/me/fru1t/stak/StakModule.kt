package me.fru1t.stak

import dagger.Module
import dagger.Provides
import javafx.stage.Stage
import javax.inject.Named
import javax.inject.Singleton

/** Stak-specific dependency constants. */
object Stak {
  /** Name for the primary stage [Stage] object. */
  const val PRIMARY_STAGE_NAME = "PrimaryStage"
}

/** The main Stak module that provides JavaFX bootstrapped objects. */
@Module
class StakModule(private val primaryStage: Stage) {
  @Provides
  @Singleton
  @Named(Stak.PRIMARY_STAGE_NAME)
  fun providePrimaryStage(): Stage = primaryStage
}
