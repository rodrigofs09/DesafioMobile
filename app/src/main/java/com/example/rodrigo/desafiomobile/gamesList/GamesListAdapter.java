package com.example.rodrigo.desafiomobile.gamesList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodrigo.desafiomobile.R;
import com.example.rodrigo.desafiomobile.entity.GamesEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GamesListAdapter extends RecyclerView.Adapter<GamesListAdapter.GamesViewHolder> {

    private List<GamesEntity> gamesList;
    private Context context;

    OnRecyclerViewSelected onRecyclerViewSelected;

    // Construtor que recebe a lista
    GamesListAdapter(List<GamesEntity> gamesList, Context context ) {
        this.gamesList = gamesList;
        this.context = context;
    }


    // Infla o componente view
    @Override
    public GamesViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.games_item_list, parent, false);
        return new GamesViewHolder(v);
    }

    // Seta os dados nas views
    @Override
    public void onBindViewHolder ( final GamesViewHolder holder, final int position){
        GamesEntity gamesEntity = gamesList.get(position);
        holder.txGameName.setText(gamesEntity.getName());
        Picasso.with(context)
                .load(gamesEntity.getImage())
                .centerCrop()
                .fit()
                .into(holder.imgBackgroud);
    }

    // Retorna o tamanho da lista
    @Override
    public int getItemCount () {
        return gamesList.size();
    }

    // Mapeamento dos componentes da view
    public class GamesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tx_game_name)
        TextView txGameName;

        @BindView(R.id.image_view_background)
        ImageView imgBackgroud;

        public GamesViewHolder(View itemView) {
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

    public void setOnRecyclerViewSelected (OnRecyclerViewSelected onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}
