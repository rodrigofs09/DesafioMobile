package com.example.rodrigo.desafiomobile.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GamesEntity (
        @SerializedName("id")
        var id:Int,

        @SerializedName("name")
        var name: String,

        @SerializedName("image")
        var image: String,

        @SerializedName("release_date")
        var releaseDate: String,

        @SerializedName("trailer")
        var trailer: String,

        @SerializedName("platforms")
        var platforms: List<String>

) : Serializable