package com.example.rodrigo.desafiomobile.gamesFragment

import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.example.rodrigo.desafiomobile.cicerone.SceneNavigator
import com.example.rodrigo.desafiomobile.dagger.PerFlow
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

@Module
class FlowModule(private val fragmentActivity: FragmentActivity, private val fm: FragmentManager, private val containerId: Int){

    @Provides
    @PerFlow
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @PerFlow
    fun provideNavigator(): SceneNavigator = SceneNavigator(fragmentActivity, fm, containerId)

    @Provides
    @PerFlow
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router
}

