package com.example.rodrigo.desafiomobile.gamesList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.rodrigo.desafiomobile.R
import com.example.rodrigo.desafiomobile.entity.GamesEntity
import com.example.rodrigo.desafiomobile.entity.GamesListEntity
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.games_item_list.*

class GamesListAdapter(private val context: Context) : RecyclerView.Adapter<GamesListAdapter.ViewHolder>() {

    var gamesList: GamesListEntity? = null

    private lateinit var onRecyclerViewSelected: OnRecyclerViewSelected

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).
                inflate(R.layout.games_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = gamesList?.games?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        gamesList?.let{safeGamesList ->
            val gamesEntity: GamesEntity = safeGamesList.games[position]
            holder.txGameName.text = gamesEntity.name
            Glide.with(context)
                    .load(gamesEntity.image)
                    .centerCrop()
                    .into(holder.imageViewBackground)
            holder.container.setOnClickListener { onRecyclerViewSelected.onClick(safeGamesList.games[position]) }
        }
    }

    fun setOnRecylerViewSelected(listener: OnRecyclerViewSelected) {
        onRecyclerViewSelected = listener
    }

    fun setData(gamesListEntity: GamesListEntity){
        gamesList = gamesListEntity
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {
        override val containerView: View? = itemView

    }

}