package com.example.rodrigo.desafiomobile.dagger

import android.content.Context
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent{
    @ApplicationContext
    fun context(): Context
}