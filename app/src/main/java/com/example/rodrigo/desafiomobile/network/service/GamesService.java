package com.example.rodrigo.desafiomobile.network.service;

import com.example.rodrigo.desafiomobile.entity.GamesListEntity;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GamesService {
    @GET("s/1b7jlwii7jfvuh0/games")
    Call<GamesListEntity> getGames();
}