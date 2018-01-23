package me.fru1t.fx

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import me.fru1t.fx.annotation.Layout
import me.fru1t.slik.annotations.Inject
import me.fru1t.slik.annotations.Singleton
import kotlin.reflect.KClass

/** Default implementation of [LayoutControllerFactory]. */
@Inject @Singleton class LayoutControllerFactoryImpl : LayoutControllerFactory {
  override fun <T : AbstractLayoutController> create(controllerClass: KClass<T>): T {
    // FxmlResource annotation holds the location of the Fxml resource file
    val controllerAnnotation =
      controllerClass.java.getDeclaredAnnotation(Layout::class.java)
          ?: throw RuntimeException(
              "${controllerClass.java.name} requires a ${Layout::class.qualifiedName} annotation.")

    // Extract path
    val resourceUrl =
      controllerClass.java.getResource(controllerAnnotation.resourcePath)
          ?: throw RuntimeException(
              "Couldn't find the resource file at ${controllerAnnotation.resourcePath} defined " +
                  "in ${controllerClass.java.name}")

    // Inflate the Fxml
    val loader = FXMLLoader(resourceUrl)
    val fxmlRoot: Parent = loader.load()

    // Grab the controller
    val controller = loader.getController<T>()
        ?: throw RuntimeException(
            "Fxml layout ${controllerAnnotation.resourcePath} has no fx:controller attribute.")

    // Set up fields within the controller
    controller.scene = Scene(fxmlRoot)
    controller.onSceneCreate()

    return controller
  }
}
