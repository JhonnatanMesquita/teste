package me.jhonnatanmesquita.deboas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Player extends AppCompatActivity {

    private Handler handler = new Handler();

    public ImageView imgPlayer, navBtn;
    public ProgressBar progressBar;
    private Runnable update;
    public MediaPlayer musica;
    public TextView iniMus, durMus, objetivo;

    AnimationDrawable animPlayer;

    Integer tempo;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        navBtn = findViewById(R.id.navBtn);

        musica = MediaPlayer.create(this, R.raw.meditacao);
        progressBar = findViewById(R.id.progressBar);
        navBtn = findViewById(R.id.navBtn);
        durMus = findViewById(R.id.durMus);

        Intent intent = getIntent();
        String objv = intent.getStringExtra("Objetivo");

        imgPlayer = findViewById(R.id.imgPlayer);

        if (objv.contains("Ficar Sussa")){
            imgPlayer.setImageResource(R.drawable.linha1);
        } else if (objv.contains("Olha a foca!")){
            imgPlayer.setImageResource(R.drawable.linha2);
        }else if (objv.contains("Dormir de boa")){
            imgPlayer.setImageResource(R.drawable.linha3);
        } else{
            imgPlayer.setImageResource(R.drawable.linha1);
        }

        animPlayer = (AnimationDrawable) imgPlayer.getDrawable();
        animPlayer.start();

        objetivo = findViewById(R.id.objetivo);
        objetivo.setText(objv);

        sharedPreferences = getSharedPreferences("CONF", MODE_PRIVATE);

        editor = sharedPreferences.edit();

        tempo = sharedPreferences.getInt("Tempo", 0);

        navBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

                if(!musica.isPlaying()){
                    musica.start();
                    progressBar.setMax( musica.getDuration() );
                    String duracao = sdf.format(new Date( musica.getDuration()));
                    durMus.setText(duracao);
                    navBtn.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                    update = new Runnable() {
                        @Override
                        public void run() {
                            iniMus = findViewById(R.id.iniMus);
                            String d = sdf.format(new Date( musica.getCurrentPosition()));
                            progressBar.setProgress( musica.getCurrentPosition());
                            iniMus.setText(d);
                            handler.postDelayed( this , 1000);
                        }
                    };
                    handler.postDelayed(update, 250);
                }
                else{
                    musica.pause();
                    navBtn.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                }
            }
        });

        musica.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                editor = sharedPreferences.edit();

                Integer getSec = sharedPreferences.getInt("Sessões", 0);

                getSec++;

                editor.putInt("Sessões", getSec);
                editor.apply();

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Integer nwTempo = musica.getCurrentPosition() + tempo;

        editor.putInt("Tempo", nwTempo);
        editor.apply();

        musica.stop();

    }
}
