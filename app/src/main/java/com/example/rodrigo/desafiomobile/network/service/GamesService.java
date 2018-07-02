package com.example.rodrigo.desafiomobile.network.service;

import com.example.rodrigo.desafiomobile.entity.GamesListEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * metodo retrofit  para acessar url do json
 * lista de jogos do json
 */
public interface GamesService {
    @GET("s/1b7jlwii7jfvuh0/games")
    Call<GamesListEntity> getGames();

}
