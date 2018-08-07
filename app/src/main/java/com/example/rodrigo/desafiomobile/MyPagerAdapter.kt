package com.example.rodrigo.desafiomobile

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.rodrigo.desafiomobile.gamesList.GamesListFragment
import com.example.rodrigo.desafiomobile.gamesListDetail.teste

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                GamesListFragment()
            }
            else -> {
                return teste()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Lista de games"
            else -> {
                return "Resto"
            }
        }
    }

}