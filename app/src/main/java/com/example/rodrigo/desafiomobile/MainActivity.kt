package com.example.rodrigo.desafiomobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.rodrigo.desafiomobile.cicerone.BackButtonListener
import com.example.rodrigo.desafiomobile.gamesList.GamesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val gamesFragment: GamesFragment by lazy { GamesFragment() }
    private val secondFragment: GamesFragment by lazy { GamesFragment() }
    private val fragmentAdapter = MyPagerAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewpagerMain.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpagerMain)

    }

    inner class MyPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    gamesFragment
                }
                else -> {
                    return secondFragment
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
                    return "Games"
                }
            }
        }
    }

    override fun onBackPressed() {
        val fragment = fragmentAdapter.getItem( viewpagerMain.currentItem )
        if (fragment is BackButtonListener && fragment.onBackPressed()) {

        } else {
            finish()
        }
    }

}