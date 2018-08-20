package com.example.rodrigo.desafiomobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rodrigo.desafiomobile.cicerone.BackButtonListener
import com.example.rodrigo.desafiomobile.dagger.ApplicationComponent
import com.example.rodrigo.desafiomobile.dagger.ApplicationModule
import com.example.rodrigo.desafiomobile.dagger.DaggerApplicationComponent
import com.example.rodrigo.desafiomobile.gamesFragment.GamesFragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import ru.terrakok.cicerone.commands.SystemMessage

class MainActivity : AppCompatActivity() {

    private val gamesFragment: GamesFragment.MainFragment by lazy { GamesFragment.MainFragment().apply { addFragment(this) } }
    private val secondFragment: GamesFragment.SecondFragment by lazy { GamesFragment.SecondFragment().apply { addFragment(this) } }

    private val mainCicerone = Cicerone.create()

    private val fragmentNames: Array<String> = arrayOf(GamesFragment.MainFragment.className, GamesFragment.SecondFragment.className)

    companion object {
        lateinit var daggerComponent: ApplicationComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daggerComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(applicationContext))
                .build()

        configureNavigationBar()

        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.navigation_home
        }

    }

    private fun configureNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> mainCicerone.router.replaceScreen(GamesFragment.MainFragment.className) //envia comandos para o navigator
                R.id.navigation_dashboard -> mainCicerone.router.replaceScreen(GamesFragment.SecondFragment.className)
            }
            true
        }
    }

    private fun addFragment(fragment: GamesFragment) {
        supportFragmentManager.beginTransaction()
                .add(R.id.flowContainer, fragment, fragment.javaClass.simpleName)
                .detach(fragment)
                .commitNow()
    }

    private val navigator = object : Navigator {
        override fun applyCommands(commands: Array<Command>) {
            for(command in commands) applyCommand(command)
        }

        private fun applyCommand(command: Command) {
            when(command){
                is Back -> finish()
                is SystemMessage -> Toast.makeText(this@MainActivity, command.message, Toast.LENGTH_SHORT).show()
                is Replace -> {
                    when(command.screenKey){
                        GamesFragment.MainFragment.className -> changeTab(gamesFragment)
                        GamesFragment.SecondFragment.className -> changeTab(secondFragment)
                    }
                }
            }

        }

        private fun changeTab(targetFragment: GamesFragment){
            val fragmentTransaction = supportFragmentManager.beginTransaction()

            fragmentNames.forEach {
                val fragment = supportFragmentManager.findFragmentByTag(it)
                if(fragment != null && !fragment.isDetached && fragment != targetFragment){
                    fragmentTransaction.detach(fragment)
                }
            }
            fragmentTransaction.attach(targetFragment).commitNow()
        }

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        mainCicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        mainCicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.flowContainer)
        if(fragment != null && fragment is BackButtonListener && fragment.onBackPressed()){}
        else {
            finish()
        }
    }
}