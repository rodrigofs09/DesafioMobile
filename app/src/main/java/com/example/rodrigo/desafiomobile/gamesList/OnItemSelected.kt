package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.entity.GameEntity

interface OnItemSelected {
    fun onClick(gameEntity: GameEntity)
}