package com.example.rodrigo.desafiomobile.gamesList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rodrigo.desafiomobile.R;
import com.example.rodrigo.desafiomobile.entity.GamesEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesViewHolder> {

    private List<GamesEntity> gamesList;
    private Context context;

    private OnRecyclerViewSelected onRecyclerViewSelected;

    // Construtor que recebe a lista
    GamesListAdapter(List<GamesEntity> gamesList, Context context ) {
        this.gamesList = gamesList;
        this.context = context;
    }


    // Infla o componente view
    @NonNull
    @Override
    public GamesViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_item_list, parent, false);
        return new GamesViewHolder(v);
    }

    // Seta os dados nas views
    @Override
    public void onBindViewHolder (@NonNull final GamesViewHolder holder, final int position){
        GamesEntity gamesEntity = gamesList.get(position);
        holder.txGameName.setText(gamesEntity.getName());
        Glide.with(context)
                .load(gamesEntity.getImage())
                .centerCrop()
                .into(holder.imgBackgroud);
    }

    // Retorna o tamanho da lista
    @Override
    public int getItemCount () {
        return gamesList.size();
    }

    // Mapeamento dos componentes da view
    class GamesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_game_name)
        TextView txGameName;

        @BindView(R.id.image_view_background)
        ImageView imgBackgroud;

        GamesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        // Seta o clique rápido
        @OnClick(R.id.container)
        void onItemClick(View view) {
            if (onRecyclerViewSelected != null)
                onRecyclerViewSelected.onClick(view, getAdapterPosition());

        }

    }

    // Seta clique
    public void setOnRecyclerViewSelected (OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}
