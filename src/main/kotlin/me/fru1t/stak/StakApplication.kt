package me.fru1t.stak

import javafx.application.Application
import javafx.stage.Stage
import kotlinx.coroutines.experimental.launch
import me.fru1t.stak.server.StakServer

fun main(args: Array<String>) {
  val serverJob = launch {
    StakServer.start()
  }

  Application.launch(StakApplication::class.java, *args)
  serverJob.cancel() // TODO(kodlee): This doesn't work.
}

/** The primary entry point for the Stak application. Sets up and launches the primary stage. */
class StakApplication : Application() {
  override fun start(primaryStage: Stage?) {
    primaryStage!!.show()
  }
}
