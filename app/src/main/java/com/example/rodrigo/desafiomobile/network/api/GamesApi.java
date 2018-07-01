package com.example.rodrigo.desafiomobile.network.api;

import com.example.rodrigo.desafiomobile.entity.GamesListEntity;
import com.example.rodrigo.desafiomobile.network.service.GamesService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GamesApi {
    private static GamesApi instance;
    private GamesService gamesService;

    public static GamesApi getInstance(){
        if(instance == null){
            instance = new GamesApi();
        }

        return instance;
    }

    private GamesApi(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addConverterFactory(defaultConvertFactory())
                .build();

        this.gamesService = retrofit.create(GamesService.class);

    }

    private Converter.Factory defaultConvertFactory() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return GsonConverterFactory.create(gson);
    }

    public Call<GamesListEntity> getGames(){
        return gamesService.getGames();
    }

}
