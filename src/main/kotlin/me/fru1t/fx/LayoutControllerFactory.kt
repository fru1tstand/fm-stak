package me.fru1t.fx

import kotlin.reflect.KClass

/** A factory class that creates [AbstractLayoutController]s. */
interface LayoutControllerFactory {
  /** Instantiates a [controllerClass], inflating a new layout to go with it.*/
  fun <T : AbstractLayoutController> create(controllerClass: KClass<T>): T
}
