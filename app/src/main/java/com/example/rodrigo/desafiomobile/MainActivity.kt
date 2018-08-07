package com.example.rodrigo.desafiomobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentAdapter = MyPagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
    }

    private fun setupHomeAsUp() {
        val shouldShow = 1 < supportFragmentManager.backStackEntryCount
        supportActionBar?.setDisplayHomeAsUpEnabled(shouldShow)
    }

    override fun onSupportNavigateUp(): Boolean =
            supportFragmentManager.popBackStack().run { true }
}
