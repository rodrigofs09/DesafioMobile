package com.example.rodrigo.desafiomobile.gamesList

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.entity.GameEntity
import com.example.rodrigo.desafiomobile.entity.GamesListEntity
import com.example.rodrigo.desafiomobile.gamesListDetail.GamesDetailFragment

import kotlinx.android.synthetic.main.custom_progress_bar.*
import kotlinx.android.synthetic.main.fragment_games_list.*

class GamesListFragment : GamesFragment(), GamesListView, OnRecyclerViewSelected {

    private val gamesListPresenter: GamesListPresenter = GamesListPresenter(this)

    private lateinit var adapter: GamesListAdapter

    override fun displayGames(gamesListEntity: GamesListEntity) {
        adapter.data = (gamesListEntity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        adapter = GamesListAdapter(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Lista de Games"

        // Coloca o adapter na Recycler View
        rvGames.adapter = adapter
        rvGames.layoutManager = LinearLayoutManager(context)
        rvGames.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter.onRecyclerViewSelected = (this)

        // Presenter atualiza informações da lista
        gamesListPresenter.updateList()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.sceneContainer) == null)
            cicerone.router.replaceScreen(GamesListFragment.className, 1)
    }


    companion object {
        lateinit var gameExtraKey : GameEntity
        val className : String = GamesListFragment::class.java.simpleName

    }

    override fun onClick(gameEntity: GameEntity) {

        val gamesDetailFragment = GamesDetailFragment.newInstance(gameEntity)
        val transaction = fragmentManager?.beginTransaction()

        transaction?.addToBackStack(null)
        transaction?.add(R.id.gamesList, gamesDetailFragment)?.commit()

    }

    override fun showMessage(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()}
    override fun showLoading(){linear_layout_loading.visibility = View.VISIBLE}
    override fun hideLoading(){linear_layout_loading.visibility = View.GONE }
}