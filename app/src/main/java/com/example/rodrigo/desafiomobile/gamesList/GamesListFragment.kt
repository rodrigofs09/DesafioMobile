package com.example.rodrigo.desafiomobile.gamesList

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.entity.GamesListEntity
import com.example.rodrigo.desafiomobile.gamesDetail.GamesDetailFragment
import com.example.rodrigo.desafiomobile.gamesFragment.GamesFragment

import kotlinx.android.synthetic.main.custom_progress_bar.*
import kotlinx.android.synthetic.main.fragment_games_list.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class GamesListFragment : Fragment(), GamesListView {

    @Inject
    lateinit var gamesListPresenter: GamesListPresenter

    private lateinit var adapter: GamesListAdapter

    @Inject
    lateinit var router: Router

    private val component: GamesListComponent? by lazy {
        context?.let {
            DaggerGamesListComponent.builder()
                    .flowComponent( (parentFragment as GamesFragment).component)
                    .gamesListModule(GamesListModule(this, it))
                    .build()
        }
    }

    override fun displayGames(gamesListEntity: GamesListEntity) {
        adapter.data = (gamesListEntity)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component?.inject(this)
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
        adapter.onItemSelected.subscribe {
            router.navigateTo(GamesDetailFragment.className, it)
        }

        gamesListPresenter.updateList()

    }

    companion object {
        val className : String = GamesListFragment::class.java.simpleName
    }

    override fun showMessage(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()}
    override fun showLoading(){linearLayoutLoading?.visibility = View.VISIBLE}
    override fun hideLoading(){linearLayoutLoading?.visibility = View.GONE }
}