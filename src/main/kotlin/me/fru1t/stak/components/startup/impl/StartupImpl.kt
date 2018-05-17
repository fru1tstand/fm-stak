package me.fru1t.stak.components.startup.impl

import javafx.stage.Stage
import me.fru1t.stak.Stak
import me.fru1t.stak.components.startup.Startup
import javax.inject.Inject
import javax.inject.Named

/** Default implementation of [Startup]. */
class StartupImpl @Inject constructor(
    @Named(Stak.PRIMARY_STAGE_NAME) private val primaryStage: Stage): Startup {
  override fun boot() {
    primaryStage.show()
  }
}
