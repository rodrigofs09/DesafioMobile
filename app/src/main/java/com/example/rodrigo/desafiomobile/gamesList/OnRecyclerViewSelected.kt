package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.model.GameEntity

interface OnRecyclerViewSelected {
    fun onClick(gameEntity: GameEntity)
}