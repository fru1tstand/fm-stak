package me.fru1t.fx

import javafx.scene.Scene

/**
 * The base class for layout controllers that manage inflated Fxml layouts in the form of a scene.
 * This class provides event methods for the entire controller lifecycle.
 */
abstract class AbstractLayoutController {
  lateinit var scene: Scene
    internal set

  // Optional methods that may be hooked into.
  /**
   * Called after both the [scene] has been inflated and all controller defined Fxml fields have
   * been instantiated. Use this in place of `@FXML fun initialize()`.
   */
  open fun onSceneCreate() {}
}
