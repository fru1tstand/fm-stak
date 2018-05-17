package me.fru1t.stak

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StakModule::class, ComponentsModule::class])
interface StakComponent {
  fun inject(stakApplication: StakApplication)
}
