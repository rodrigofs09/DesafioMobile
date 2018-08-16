package com.example.rodrigo.desafiomobile.gamesDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rodrigo.desafiomobile.cicerone.BackButtonListener

import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.config.Config
import com.example.rodrigo.desafiomobile.model.GameEntity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.fragment_games_detail.*

class GamesDetailFragment : Fragment(), YouTubePlayer.OnInitializedListener, BackButtonListener {

    private lateinit var finalUrl: String

    private lateinit var gameEntity: GameEntity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        text_view_name.text = gameEntity.name
        gameDate.text = gameEntity.releaseDate

        val platforms = textToPlatforms(gameEntity.platforms)
        gamePlatforms.text = platforms

        val url = gameEntity.trailer
        val urlSemEspaco = url.split("=")
        finalUrl = urlSemEspaco[urlSemEspaco.size - 1]


        val frag = childFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        frag.initialize(Config.youTubeApiKey, this)

    }

    companion object {
        val className : String = GamesDetailFragment::class.java.simpleName
        fun newInstance(gameEntity: GameEntity): GamesDetailFragment = GamesDetailFragment().apply { gameEntity.let{this.gameEntity = it }}
    }


    private fun textToPlatforms(platforms: List<String>): String{
        var stringPlatforms = StringBuilder()
        platforms.indices.forEachIndexed { platform, i ->
            if(i == 0)
                stringPlatforms = StringBuilder(platforms[platform])
            else
                stringPlatforms.append(", ").append(platforms[platform])
        }
        return stringPlatforms.toString()
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.cueVideo(finalUrl)
    }
    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Toast.makeText(context,"Erro ao reproduzir video", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed(): Boolean {
        val childFragment = childFragmentManager.findFragmentById(R.id.gamesDetail)
        return if (childFragment != null && childFragment is BackButtonListener && childFragment.onBackPressed()) {
            childFragmentManager.popBackStack()
            true
        } else {
            if (isAdded)
                activity?.finish()
            true
        }
    }
}
