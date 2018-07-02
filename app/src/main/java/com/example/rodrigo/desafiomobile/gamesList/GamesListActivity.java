package com.example.rodrigo.desafiomobile.gamesList;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.rodrigo.desafiomobile.R;
import com.example.rodrigo.desafiomobile.entity.GamesEntity;
import com.example.rodrigo.desafiomobile.gamesListDetail.GamesDetailActivity;

public class GamesListActivity extends AppCompatActivity implements GamesListView{

    // Bind do xml com a activity pelo ButterKnife
    @BindView(R.id.rv_games)
    RecyclerView rv_games;

    // Tela de progresso
    @BindView(R.id.linear_layout_loading)
    LinearLayout loadingLayout;

    GamesListPresenter gamesListPresenter;

    public GamesListAdapter gamesListAdapter;

    private List<GamesEntity> gamesList;

    // Variável para armazenar informações dos jogos do json e enviar para a activity de detalhes
    public final static String EXTRA = "game";

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_games_list);

        ButterKnife.bind(this);

        // array para armazenar dados
        gamesList = new ArrayList<>();

        // Seta o adapter na recycler view
        rv_games.setAdapter(new GamesListAdapter(gamesList, this));
        rv_games.setLayoutManager(new LinearLayoutManager(this));

        // presenter atualiza informações da activity
        gamesListPresenter = new GamesListPresenter(this);
        gamesListPresenter.updateList();

    }

    // Cria lista do recycler view evitando o erro do recycler view null
    @Override
    public void AddListRecycler() {
        gamesListAdapter = new GamesListAdapter(gamesList, this);
        gamesListAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected(){
            @Override
            public void onClick(View view, int position){
                // Trata o clique e envia informação do jogo selecionado para a próxima activity
                Intent intent = new Intent(GamesListActivity.this, GamesDetailActivity.class);
                intent.putExtra(EXTRA, gamesList.get(position));
                startActivity(intent);
            }
        });

        rv_games.setAdapter(gamesListAdapter);

        // Criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rv_games.setLayoutManager(layoutManager);
        rv_games.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void updateList(final List<GamesEntity> gamesLista) {

        gamesList = gamesLista;

        // Seta adapter
        gamesListAdapter = new GamesListAdapter(gamesList, this);
        gamesListAdapter.setOnRecyclerViewSelected(new OnRecyclerViewSelected() {
            @Override
            public void onClick(View view, int position) {
                // Vai para a activity de detalhes armazenando informações do jogo selecionado
                Intent intent = new Intent(GamesListActivity.this, GamesDetailActivity.class);
                intent.putExtra(EXTRA, gamesList.get(position));
                startActivity(intent);
            }

        });

        rv_games.setAdapter(gamesListAdapter);

        // Criação do gerenciador de layouts
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        rv_games.setLayoutManager(layoutManager);
        rv_games.addItemDecoration(dividerItemDecoration);
    }

    // Exibe mensagens de erro
    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // Trata tela de progresso
    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

}
