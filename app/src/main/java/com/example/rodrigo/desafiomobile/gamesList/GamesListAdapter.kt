package com.example.rodrigo.desafiomobile.gamesList

import android.content.Context

import com.bumptech.glide.Glide

import com.example.rodrigo.desafiomobile.R.layout.games_item_list
import com.example.rodrigo.desafiomobile.entity.GameEntity
import com.example.rodrigo.desafiomobile.entity.GamesListEntity
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

import kotlinx.android.synthetic.main.games_item_list.view.*

class GamesListAdapter(private val context: Context) : GroupAdapter<ViewHolder>() {
        var onRecyclerViewSelected: OnRecyclerViewSelected? = null
        var data: GamesListEntity? = null
            set(value) {
                field = value
                clear()
                value?.games?.forEach { game -> add(GameItem(game)) }
            }

        inner class GameItem(private val gameEntity: GameEntity): Item() {

            override fun getLayout() = games_item_list

            override fun bind(viewHolder: ViewHolder, position: Int) {
                viewHolder.itemView.txGameName.text = gameEntity.name
                Glide.with(context)
                        .load(gameEntity.image)
                        .centerCrop()
                        .into(viewHolder.itemView.imageViewBackground)
                viewHolder.itemView.setOnClickListener { onRecyclerViewSelected?.onClick(gameEntity) }
            }

        }
    }