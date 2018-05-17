package me.fru1t.stak.components.startup

/**
 * Responsible for cold booting Stak from a shut-down state; that is, loading any necessary data
 * in memory, setting up the primary stage, and performing any other startup tasks.
 */
interface Startup {
  /** Entry-point for startup tasks. */
  fun boot()
}
