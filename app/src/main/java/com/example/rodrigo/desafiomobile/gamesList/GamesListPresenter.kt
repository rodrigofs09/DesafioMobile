package com.example.rodrigo.desafiomobile.gamesList

import com.example.rodrigo.desafiomobile.network.api.GamesApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class GamesListPresenter @Inject constructor(private val gamesListView: GamesListView) {

    fun updateList() {
        gamesListView.showLoading()

        val call = GamesApi()

        call.loadGames()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                     gamesListView.displayGames(it)
                     gamesListView.hideLoading()
                }
    }

}