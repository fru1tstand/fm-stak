package me.fru1t.stak

import javafx.application.Application
import javafx.stage.Stage

fun main(args: Array<String>) {
  Application.launch(StakApplication::class.java, *args)
}

/** The primary entry point for the Stak application. Sets up and launches the primary stage. */
class StakApplication : Application() {
  override fun start(primaryStage: Stage?) {
    primaryStage!!.show()
  }
}
