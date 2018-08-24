package com.example.rodrigo.desafiomobile.network.service

import com.example.rodrigo.desafiomobile.entity.GamesListEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface GamesService {
    @GET("s/1b7jlwii7jfvuh0/games")
    fun listGames(): Observable<GamesListEntity>
}