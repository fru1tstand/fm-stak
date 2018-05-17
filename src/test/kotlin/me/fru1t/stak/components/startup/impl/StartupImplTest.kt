package me.fru1t.stak.components.startup.impl

import com.google.common.truth.Truth.assertThat
import javafx.stage.Stage
import me.fru1t.fx.testing.FxApplicationTest
import me.fru1t.fx.testing.FxTest

class StartupImplTest : FxApplicationTest() {
  @FxTest fun boot() {
    val stage = Stage()
    val startupImpl = StartupImpl(stage)

    startupImpl.boot()

    assertThat(stage.isShowing).isTrue()
  }
}
