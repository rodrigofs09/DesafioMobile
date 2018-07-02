package com.example.rodrigo.desafiomobile.gamesListDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rodrigo.desafiomobile.R;
import com.example.rodrigo.desafiomobile.config.Config;
import com.example.rodrigo.desafiomobile.entity.GamesEntity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.rodrigo.desafiomobile.gamesList.GamesListActivity.EXTRA;

public class GamesDetailActivity extends AppCompatActivity implements YouTubePlayer.OnInitializedListener {

    // Bind do xml com a activiy pelo Butterknife
    @BindView(R.id.text_view_nome)
    TextView nome;

    @BindView(R.id.gameData)
    TextView gameData;

    @BindView(R.id.gamePlataformas)
    TextView gamePlataformas;

    private GamesEntity gamesEntity;
    private String final_url;

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

        // Recebe a partir da activity anterior todas as informações do json do jogo selecionado
        Intent intent = getIntent();
        gamesEntity = (GamesEntity) intent.getSerializableExtra(EXTRA);

        // Seta as informações na tela de detalhes do jogo
        setTitle(gamesEntity.getName());
        nome.setText(gamesEntity.getName());
        gameData.setText(gamesEntity.getReleaseDate());

        String plataformas = textoPlataformas(gamesEntity.getPlatforms());
        gamePlataformas.setText(plataformas);

        // Remove os espaços da url obtida e pega apenas o trecho após watch do url do youtube
        String url = gamesEntity.getTrailer();
        String [] url_sem_espaco = url.split("=");
        final_url = url_sem_espaco[url_sem_espaco.length - 1];

        // Aciona fragment do YouTube
        YouTubePlayerSupportFragment frag =
                (YouTubePlayerSupportFragment) getSupportFragmentManager().findFragmentById(R.id.youtube_fragment);
        frag.initialize(Config.YOUTUBE_API_KEY, this);


    }

    // Trata o botão voltar na action bar
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

    // Trata a lista de plataformas do json, convertendo para uma string
    public String textoPlataformas(List<String> plataformas){
        StringBuilder StringPlataformas = new StringBuilder();
        for(int i = 0; i < plataformas.size(); i++){
            if(i == 0)
                StringPlataformas = new StringBuilder(plataformas.get(i));
            else
                StringPlataformas.append(", ").append(plataformas.get(i));
        }

        return StringPlataformas.toString();

    }

    // Tratam funcionamento do player do YouTube no app
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo(final_url);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao reproduzir vídeo", Toast.LENGTH_LONG).show();
    }
}
