package com.example.rodrigo.desafiomobile.gamesList;

import com.example.rodrigo.desafiomobile.entity.GamesListEntity;
import com.example.rodrigo.desafiomobile.network.api.GamesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesListPresenter {

    private GamesListView gamesListView;

    GamesListEntity gameListEntity;

    GamesListPresenter(GamesListView gamesListView){
        this.gamesListView = gamesListView;
    }

    void updateList(){

        gamesListView.showLoading();

        final GamesApi gamesApi = GamesApi.getInstance();

        gamesApi.getGames().enqueue(new Callback<GamesListEntity>() {
            @Override
            public void onResponse(Call<GamesListEntity> call, Response<GamesListEntity> response) {
                GamesListEntity gameListEntity = response.body();
                if(gameListEntity != null){
                    gamesListView.updateList(gameListEntity.getGames());
                } else{
                    gamesListView.showMessage("Falha no acesso ao servidor");
                }
                gamesListView.hideLoading();
            }

            @Override
            public void onFailure(Call<GamesListEntity> call, Throwable t) {
                gamesListView.hideLoading();
                gamesListView.showMessage("Falha ao acessar servidor");
            }
        });

    }

}
