package com.example.rodrigo.desafiomobile.entity

import com.google.gson.annotations.SerializedName

data class GamesListEntity(@SerializedName("games")
                           val games: List<GameEntity>)