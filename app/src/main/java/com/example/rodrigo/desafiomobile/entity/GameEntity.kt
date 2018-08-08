package com.example.rodrigo.desafiomobile.entity

import com.google.gson.annotations.SerializedName

data class GameEntity (
        @SerializedName("id")
        val id:Int,

        @SerializedName("name")
        val name: String,

        @SerializedName("image")
        val image: String,

        @SerializedName("release_date")
        val releaseDate: String,

        @SerializedName("trailer")
        val trailer: String,

        @SerializedName("platforms")
        val platforms: List<String>

)