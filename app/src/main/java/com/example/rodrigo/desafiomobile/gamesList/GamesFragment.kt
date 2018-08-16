package com.example.rodrigo.desafiomobile.gamesList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.cicerone.RouterProvider
import com.example.rodrigo.desafiomobile.cicerone.SceneNavigator
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class GamesFragment : Fragment(), RouterProvider {

    var cicerone: Cicerone<Router> = Cicerone.create()

    lateinit var navigator: SceneNavigator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = SceneNavigator(activity, childFragmentManager, R.id.sceneContainer)

    }
    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.sceneContainer) == null)
            cicerone.router.replaceScreen(GamesListFragment.className, 1)
    }

    override fun getRouter(): Router{
        return cicerone.router}

}
