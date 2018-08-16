package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.entity.GamesListEntity

interface GamesListView {

    fun displayGames(gamesListEntity: GamesListEntity)
    fun showMessage(msg : String)
    fun showLoading()
    fun hideLoading()
}