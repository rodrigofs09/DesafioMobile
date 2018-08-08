package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.entity.GameEntity

interface OnRecyclerViewSelected {
    fun onClick(gameEntity: GameEntity)
}