package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.entity.GamesEntity

interface OnRecyclerViewSelected {
    fun onClick(gamesEntity: GamesEntity)
}