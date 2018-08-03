package com.example.rodrigo.desafiomobile.gamesList

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.entity.GamesEntity
import com.example.rodrigo.desafiomobile.entity.GamesListEntity
import com.example.rodrigo.desafiomobile.gamesListDetail.GamesDetailActivity
import kotlinx.android.synthetic.main.activity_games_list.*
import kotlinx.android.synthetic.main.custom_progress_bar.*
import java.io.Serializable

class GamesListActivity : AppCompatActivity(), GamesListView, OnRecyclerViewSelected{

    companion object {
        var game = "game"
    }

    private var gamesListPresenter: GamesListPresenter = GamesListPresenter(this)

    override fun onClick(gamesEntity: GamesEntity) {
        val intent = Intent(this, GamesDetailActivity::class.java)
        intent.putExtra(game, gamesEntity as Serializable)
        startActivity(intent)
    }

    private lateinit var adapter: GamesListAdapter

    override fun displayGames(gamesListEntity: GamesListEntity) {
        adapter.setData(gamesListEntity)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)

        adapter = GamesListAdapter(this)

        // Coloca o adapter na Recycler View
        rvGames.adapter = adapter
        rvGames.layoutManager = LinearLayoutManager(this)
        rvGames.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapter.setOnRecylerViewSelected(this)

        // Presenter atualiza informações da lista
        gamesListPresenter.updateList()

    }

    override fun showMessage(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()}
    override fun showLoading(){linear_layout_loading.visibility = View.VISIBLE}
    override fun hideLoading(){linear_layout_loading.visibility = View.GONE }
}
