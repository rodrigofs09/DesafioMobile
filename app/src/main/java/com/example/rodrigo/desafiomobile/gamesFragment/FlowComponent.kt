package com.example.rodrigo.desafiomobile.gamesFragment

import com.example.rodrigo.desafiomobile.dagger.ApplicationComponent
import com.example.rodrigo.desafiomobile.dagger.PerFlow
import dagger.Component
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@PerFlow
@Component(dependencies = [ApplicationComponent::class], modules = [FlowModule::class])
interface FlowComponent: ApplicationComponent {
    fun inject(gamesFragment: GamesFragment)
    fun provideCicerone(): Cicerone<Router>
    fun provideRouter(): Router
}