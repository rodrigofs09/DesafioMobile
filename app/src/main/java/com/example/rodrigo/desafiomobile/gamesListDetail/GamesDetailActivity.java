package com.example.rodrigo.desafiomobile.gamesListDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rodrigo.desafiomobile.R;
import com.example.rodrigo.desafiomobile.entity.GamesEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rodrigo.desafiomobile.gamesList.GamesListActivity.EXTRA;

public class GamesDetailActivity extends AppCompatActivity{

    @BindView(R.id.image_view_header)
    ImageView imageHeader;

    @BindView(R.id.text_view_nome)
    TextView nome;

    @BindView(R.id.gameData)
    TextView gameData;

    @BindView(R.id.gamePlataformas)
    TextView gamePlataformas;

    private GamesEntity gamesEntity;

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_games_detail);

        ButterKnife.bind(this);

        // Insere botão de voltar na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        gamesEntity = (GamesEntity) intent.getSerializableExtra(EXTRA);

        Picasso.with(this)
                .load(gamesEntity.getImage())
                .centerCrop()
                .fit()
                .into(imageHeader);
        setTitle(gamesEntity.getName());
        nome.setText(gamesEntity.getName());
        gameData.setText(gamesEntity.getReleaseDate());

        String plataformas = textoPlataformas(gamesEntity.getPlatforms());
        gamePlataformas.setText(plataformas);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String textoPlataformas(List<String> plataformas){
        String StringPlataformas = "";
        for(int i = 0; i < plataformas.size(); i++){
            if(i == 0)
                StringPlataformas = plataformas.get(i);
            else
                StringPlataformas = StringPlataformas + ", " + plataformas.get(i);
        }

        return StringPlataformas;
    }
}
