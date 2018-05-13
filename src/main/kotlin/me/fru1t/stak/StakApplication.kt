package me.fru1t.stak

import javafx.application.Application
import javafx.stage.Stage
import me.fru1t.stak.server.StakServer
import kotlin.concurrent.thread

fun main(args: Array<String>) {
  val serverThread = thread {
    StakServer.start()
  }

  Application.launch(StakApplication::class.java, *args)
  serverThread.interrupt()
}

/** The primary entry point for the Stak application. Sets up and launches the primary stage. */
class StakApplication : Application() {
  override fun start(primaryStage: Stage?) {
    primaryStage!!.show()
  }
}
