package com.example.rodrigo.desafiomobile.gamesList

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.gamesListDetail.GamesDetailFragment

class GamesListMainActivity : AppCompatActivity(), GamesListFragment.OnFragmentInteractionListener, GamesDetailFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list_main)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()

            val gamesListFragment = GamesListFragment()
            transaction.add(R.id.fragment_content, gamesListFragment).commit()

        }

        supportFragmentManager.addOnBackStackChangedListener { setupHomeAsUp() }
    }

    private fun setupHomeAsUp() {
        val shouldShow = 0 < supportFragmentManager.backStackEntryCount
        supportActionBar?.setDisplayHomeAsUpEnabled(shouldShow)
    }

    override fun onSupportNavigateUp(): Boolean =
            supportFragmentManager.popBackStack().run { true }

}
