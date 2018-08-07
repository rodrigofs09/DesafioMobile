package com.example.rodrigo.desafiomobile.gamesListDetail

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.config.Config
import com.example.rodrigo.desafiomobile.entity.GamesEntity
import com.example.rodrigo.desafiomobile.gamesList.GamesListFragment.Companion.GAME_EXTRA_KEY
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import kotlinx.android.synthetic.main.fragment_games_detail.*

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GamesDetailFragment : Fragment(), YouTubePlayer.OnInitializedListener {
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var finalUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_games_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val gamesEntity: GamesEntity = activity?.intent?.getSerializableExtra(GamesListFragment.GAME_EXTRA_KEY) as GamesEntity
        val gamesEntity: GamesEntity = GAME_EXTRA_KEY

        text_view_name.text = gamesEntity.name
        gameDate.text = gamesEntity.releaseDate

        val platforms = textToPlatforms(gamesEntity.platforms)
        gamePlatforms.text = platforms

        val url = gamesEntity.trailer
        val urlSemEspaco = url.split("=")
        finalUrl = urlSemEspaco[urlSemEspaco.size - 1]


        val frag = childFragmentManager.findFragmentById(R.id.youtube_fragment) as YouTubePlayerSupportFragment
        frag.initialize(Config.youTubeApiKey, this)

    }

    override fun onDetach() {
        super.onDetach()
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
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
}
