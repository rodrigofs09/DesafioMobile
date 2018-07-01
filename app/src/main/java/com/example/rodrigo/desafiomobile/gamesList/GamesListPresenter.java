package com.example.rodrigo.desafiomobile.gamesList;

import com.example.rodrigo.desafiomobile.entity.GamesEntity;
import com.example.rodrigo.desafiomobile.entity.GamesListEntity;
import com.example.rodrigo.desafiomobile.network.api.GamesApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesListPresenter {
    private GamesListView gamesListView;
    private List<GamesEntity> gamesList = new ArrayList<>();
    GamesListEntity gameListEntity;

    GamesListPresenter(GamesListView gamesListView){
        this.gamesListView = gamesListView;
    }

    void updateList(){
        final GamesApi gamesApi = GamesApi.getInstance();
        gamesListView.showLoading();
        gamesApi.getGames().enqueue(new Callback<GamesListEntity>() {
            @Override
            public void onResponse(Call<GamesListEntity> call, Response<GamesListEntity> response) {
                gameListEntity = response.body();
                if(gameListEntity != null){
                    gamesListView.updateList(gameListEntity.getGames());
                } else{
                    gamesListView.showMessage("Erro ao carregar dados");
                }
                gamesListView.hideLoading();
            }

            @Override
            public void onFailure(Call<GamesListEntity> call, Throwable t) {
                gamesListView.hideLoading();
                gamesListView.showMessage("Erro ao acessar servidor");
            }
        });

    }

}
