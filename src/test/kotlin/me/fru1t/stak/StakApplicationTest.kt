package me.fru1t.stak

import com.google.common.truth.Truth.assertThat
import javafx.stage.Stage
import me.fru1t.fx.testing.FxApplicationTest
import me.fru1t.fx.testing.FxTest

class StakApplicationTest : FxApplicationTest() {
  @FxTest fun start() {
    val streamToolsApplication = StakApplication()
    val stage = Stage()
    streamToolsApplication.start(stage)
    assertThat(stage.isShowing).isTrue()
  }
}
