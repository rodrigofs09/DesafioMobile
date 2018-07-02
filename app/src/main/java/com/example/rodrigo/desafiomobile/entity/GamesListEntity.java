package com.example.rodrigo.desafiomobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GamesListEntity {
    @SerializedName("games")
    @Expose
    private List<GamesEntity> games = null;

    public List<GamesEntity> getGames() {
        return games;
    }

}
