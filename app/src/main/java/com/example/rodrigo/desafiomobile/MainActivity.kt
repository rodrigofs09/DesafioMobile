package com.example.rodrigo.desafiomobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.rodrigo.desafiomobile.gamesList.GamesFragment
import com.example.rodrigo.desafiomobile.gamesListDetail.VazioFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gamesFragment: GamesFragment by lazy { GamesFragment() }
    private val vazioFragment: VazioFragment by lazy { VazioFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)

    }

    inner class MyPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    gamesFragment
                }
                else -> {
                    return vazioFragment
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
                    return "Vazio"
                }
            }
        }
    }

}