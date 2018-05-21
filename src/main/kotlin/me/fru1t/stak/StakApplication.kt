package me.fru1t.stak

import javafx.application.Application
import javafx.stage.Stage
import me.fru1t.stak.components.startup.Startup
import me.fru1t.stak.server.StakServer
import javax.inject.Inject
import kotlin.concurrent.thread

fun main(args: Array<String>) {
  val serverThread = thread {
    StakServer().run()
  }

  Application.launch(StakApplication::class.java, *args)
  serverThread.interrupt()
}

/** The primary entry point for the Stak application. Sets up and launches the primary stage. */
class StakApplication : Application() {
  @Inject lateinit var startup: Startup

  override fun start(primaryStage: Stage?) {
    DaggerStakComponent.builder().stakModule(StakModule(primaryStage!!)).build().inject(this)
    startup.boot()
  }
}
