package com.example.rodrigo.desafiomobile.network.api

import com.example.rodrigo.desafiomobile.entity.GamesListEntity
import com.example.rodrigo.desafiomobile.network.service.GamesService
import io.reactivex.Observable

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class GamesApi {

    private val service: GamesService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        service = retrofit.create<GamesService>(GamesService::class.java)
    }

    fun loadGames(): Observable<GamesListEntity> {
        return service.listGames()
    }
}