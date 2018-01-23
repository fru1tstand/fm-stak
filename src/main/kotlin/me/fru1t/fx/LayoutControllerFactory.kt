package me.fru1t.fx

import me.fru1t.slik.annotations.ImplementedBy
import kotlin.reflect.KClass

/** A factory class that creates [AbstractLayoutController]s. */
@ImplementedBy(LayoutControllerFactoryImpl::class)
interface LayoutControllerFactory {
  /** Instantiates a [controllerClass], inflating a new layout to go with it.*/
  fun <T : AbstractLayoutController> create(controllerClass: KClass<T>): T
}
