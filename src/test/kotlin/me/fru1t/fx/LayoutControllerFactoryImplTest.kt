package me.fru1t.fx

import com.google.common.truth.Truth.assertThat
import me.fru1t.fx.annotation.Layout
import me.fru1t.fx.testing.FxApplicationTest
import me.fru1t.fx.testing.FxTest
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LayoutControllerFactoryImplTest : FxApplicationTest() {
  private lateinit var factory: LayoutControllerFactory

  @BeforeEach fun setUp() {
    factory = LayoutControllerFactoryImpl()
  }

  @Test fun create_noControllerAnnotation() {
    assertThrows(RuntimeException::class.java, { factory.create(ControllerWithNoLayout::class) })
  }

  @Test fun create_invalidResourcePath() {
    assertThrows(
        RuntimeException::class.java, { factory.create(ControllerWithInvalidLayoutPath::class) })
  }

  @Test fun create_noFxControllerAttribute() {
    assertThrows(
        RuntimeException::class.java, { factory.create(ControllerWithNoFxController::class) })
  }

  @FxTest fun create() {
    val factory: LayoutControllerFactory = LayoutControllerFactoryImpl()
    val controller = factory.create(NormalController::class)
    assertThat(controller).isNotNull()
    assertThat(controller.scene).isNotNull()
  }
}

class ControllerWithNoLayout : AbstractLayoutController()

@Layout("/invalid/path")
class ControllerWithInvalidLayoutPath : AbstractLayoutController()

@Layout("/LayoutControllerFactoryImplTest_noFxController.fxml")
class ControllerWithNoFxController : AbstractLayoutController()

@Layout("/LayoutControllerFactoryImplTest_normal.fxml")
class NormalController : AbstractLayoutController()
