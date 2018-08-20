package com.example.rodrigo.desafiomobile.gamesFragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rodrigo.desafiomobile.MainActivity

import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.cicerone.BackButtonListener
import com.example.rodrigo.desafiomobile.cicerone.RouterProvider
import com.example.rodrigo.desafiomobile.cicerone.SceneNavigator
import com.example.rodrigo.desafiomobile.gamesList.GamesListFragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

open class GamesFragment : Fragment(), RouterProvider, BackButtonListener {

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var navigator: SceneNavigator

    val component: FlowComponent? by lazy {
        context?.let {
            DaggerFlowComponent.builder()
                    .applicationComponent(MainActivity.daggerComponent)
                    .flowModule(FlowModule(activity as FragmentActivity, childFragmentManager, R.id.sceneContainer))
                    .build()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        component?.inject(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games, container, false)
            ?: super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun getRouter(): Router{
        return cicerone.router}

    override fun onBackPressed(): Boolean {
        val childFragment = childFragmentManager.findFragmentById(R.id.sceneContainer)
        return childFragment != null && childFragment is BackButtonListener && childFragment.onBackPressed()
    }

    class MainFragment: GamesFragment(){

        companion object {
            val className: String = MainFragment::class.java.simpleName
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            if(childFragmentManager.findFragmentById(R.id.sceneContainer) == null)
                cicerone.router.replaceScreen(GamesListFragment.className)
        }
    }

    class SecondFragment: GamesFragment(){

        companion object {
            val className: String = SecondFragment::class.java.simpleName
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            if(childFragmentManager.findFragmentById(R.id.sceneContainer) == null)
                cicerone.router.replaceScreen(GamesListFragment.className)
        }
    }
}
