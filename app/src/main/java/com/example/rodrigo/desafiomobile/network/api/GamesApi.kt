package com.example.rodrigo.desafiomobile.network.api

import com.example.rodrigo.desafiomobile.network.service.GamesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GamesApi {
    private val retrofit= Retrofit.Builder()
            .baseUrl("https://dl.dropboxusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun gamesService(): GamesService = retrofit.create(GamesService::class.java)
}