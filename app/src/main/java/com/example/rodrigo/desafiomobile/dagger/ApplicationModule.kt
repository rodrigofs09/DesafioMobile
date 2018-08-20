package com.example.rodrigo.desafiomobile.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context){
    @Provides
    @Singleton
    @ApplicationContext
    fun context() = context
}