package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.dagger.PerScene
import com.example.rodrigo.desafiomobile.gamesFragment.FlowComponent
import dagger.Component

@PerScene
@Component(dependencies = [FlowComponent::class], modules = [GamesListModule::class])
interface GamesListComponent {
    fun inject(gamesListFragment: GamesListFragment)
}