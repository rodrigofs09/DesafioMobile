package com.example.rodrigo.desafiomobile.gamesList

import android.content.Context
import com.example.rodrigo.desafiomobile.dagger.ActivityContext
import com.example.rodrigo.desafiomobile.dagger.PerScene
import dagger.Module
import dagger.Provides

@Module
class GamesListModule(private val gamesListView: GamesListView, private val context: Context) {
    @Provides
    @ActivityContext
    @PerScene
    fun provideInnerContext(): Context{
        return context
    }

    @Provides
    @PerScene
    fun provideGamesListView(): GamesListView {
        return gamesListView
    }
}
