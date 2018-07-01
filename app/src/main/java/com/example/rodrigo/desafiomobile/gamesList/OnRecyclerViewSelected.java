package com.example.rodrigo.desafiomobile.gamesList;

import android.view.View;

public interface OnRecyclerViewSelected {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
