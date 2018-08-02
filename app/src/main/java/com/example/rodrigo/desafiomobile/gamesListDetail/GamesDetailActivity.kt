package com.example.rodrigo.desafiomobile.gamesListDetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.config.Config
import com.example.rodrigo.desafiomobile.entity.GamesEntity
import com.example.rodrigo.desafiomobile.gamesList.GamesListActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.activity_games_detail.*

class GamesDetailActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var finalUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val gamesEntity: GamesEntity = intent.getSerializableExtra(GamesListActivity.game) as GamesEntity

        title = gamesEntity.name
        text_view_name.text = gamesEntity.name
        gameDate.text = gamesEntity.releaseDate

        val platforms = textToPlatforms(gamesEntity.platforms)
        gamePlatforms.text = platforms

        val url = gamesEntity.trailer
        val urlSemEspaco = url.split("=")
        finalUrl = urlSemEspaco[urlSemEspaco.size - 1]

        val frag = supportFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        frag.initialize(Config.youTubeApiKey, this)
    }

    private fun textToPlatforms(platforms: List<String>): String{
        var stringPlatforms = StringBuilder()
        for (i in platforms.indices){
            if(i == 0)
                stringPlatforms = StringBuilder(platforms[i])
            else
                stringPlatforms.append(", ").append(platforms[i])

        }
        return stringPlatforms.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.cueVideo(finalUrl)
    }
    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Toast.makeText(this, "Erro ao reproduzir video", Toast.LENGTH_LONG).show()
    }

}