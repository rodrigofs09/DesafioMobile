package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.model.GamesListEntity
import com.example.rodrigo.desafiomobile.network.api.GamesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesListPresenter (private val gamesListView: GamesListView) {

    fun updateList() {
        gamesListView.showLoading()

        val call = GamesApi().gamesService().list()
        call.enqueue(object : Callback<GamesListEntity?> {
            override fun onResponse(call: Call<GamesListEntity?>?,
                                    response: Response<GamesListEntity?>?) {

                response?.body()?.let {
                    gamesListView.displayGames(it)
                }
                        ?: gamesListView.showMessage("Erro ao acessar informações")
                gamesListView.hideLoading()
            }

            override fun onFailure(call: Call<GamesListEntity?>?,
                                   t: Throwable?) {
                gamesListView.hideLoading()
                gamesListView.showMessage("Falha ao acessar servidor")
            }
        })
    }

}