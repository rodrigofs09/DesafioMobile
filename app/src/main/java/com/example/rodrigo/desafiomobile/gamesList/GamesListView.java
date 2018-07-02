package com.example.rodrigo.desafiomobile.gamesList;

import com.example.rodrigo.desafiomobile.entity.GamesEntity;

import java.util.List;

public interface GamesListView {
    void updateList(List<GamesEntity> gamesLista);

    void AddListRecycler();

    void showMessage(String msg);
    void showLoading();
    void hideLoading();
}
