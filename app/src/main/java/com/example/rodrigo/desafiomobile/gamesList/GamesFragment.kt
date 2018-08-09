package com.example.rodrigo.desafiomobile.gamesList

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.SceneNavigator
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GamesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    var cicerone: Cicerone<Router> = Cicerone.create()

    var navigator: SceneNavigator = SceneNavigator(activity as FragmentActivity, childFragmentManager, R.id.sceneContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        val className : String = GamesFragment::class.java.simpleName
    }

}
