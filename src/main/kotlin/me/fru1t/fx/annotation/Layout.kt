package me.fru1t.fx.annotation

/**
 * Defines the layout resource path for an Fxml layout resource. This should be attached to any
 * class that extends [me.fru1t.fx.AbstractLayoutController] so that the
 * [me.fru1t.fx.LayoutControllerFactory] may automatically instantiate the controller class and
 * inflate the [Layout] this defines. The [resourcePath] should be absolute pointing to a file
 * within the resources folder. For reference, the absolute root path `/` points to the `resources`
 * folder.
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Layout(val resourcePath: String)
