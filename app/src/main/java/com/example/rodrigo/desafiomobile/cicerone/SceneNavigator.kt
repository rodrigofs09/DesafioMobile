package com.example.rodrigo.desafiomobile.cicerone

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.example.rodrigo.desafiomobile.entity.GameEntity
import com.example.rodrigo.desafiomobile.gamesList.GamesListFragment
import com.example.rodrigo.desafiomobile.gamesDetail.GamesDetailFragment
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class SceneNavigator @Inject constructor(fragmentActivity: FragmentActivity, fm: FragmentManager, containerId: Int) : SupportAppNavigator(fragmentActivity, fm, containerId) {
    override fun createActivityIntent(context: Context, screenKey: String, data: Any?): Intent? {
        return null
    }

    override fun createFragment(screenKey: String?, gameEntity: Any?): Fragment? {
        return when (screenKey){
            GamesListFragment.className -> return GamesListFragment()
            GamesDetailFragment.className -> {
                if(gameEntity is GameEntity){
                    return GamesDetailFragment.newInstance(gameEntity)
                } else{
                    throw IllegalArgumentException("Trying to open GamesDetailFragment without providing current game")
                }
            }
            else -> null
        }
    }

}