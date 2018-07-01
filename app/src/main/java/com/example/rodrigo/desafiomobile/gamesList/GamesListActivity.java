package com.example.rodrigo.desafiomobile.gamesList;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.rodrigo.desafiomobile.R;
import com.example.rodrigo.desafiomobile.entity.GamesEntity;
import com.example.rodrigo.desafiomobile.gamesListDetail.GamesDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesListActivity extends AppCompatActivity implements GamesListView{

    @BindView(R.id.rv_games)
    RecyclerView rvGames;

    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    GamesListPresenter gamesListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_list);

        ButterKnife.bind(this);
        gamesListPresenter = new GamesListPresenter(this);
        gamesListPresenter.updateList();
    }

    @Override
    public void updateList(final List<GamesEntity> gamesList) {

        // Seta o adapter
        GamesListAdapter gamesListAdapter = new GamesListAdapter(gamesList, this);
        gamesListAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent
                        (GamesListActivity.this,
                                GamesDetailActivity.class);

                intent.putExtra("game_id", gamesList.get(position).getId());
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {
                //Toast.makeText(MoviesActivity.this, "Clique Longo", Toast.LENGTH_SHORT).show();

            }
        });

        rvGames.setAdapter(gamesListAdapter);

        // Criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(this, layoutManager.getOrientation());
        rvGames.setLayoutManager(layoutManager);
        rvGames.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

}
