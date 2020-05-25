package me.jhonnatanmesquita.deboas;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stats extends AppCompatActivity {

    Button btn1, btn2, btn3;

    SharedPreferences sharedPreferences;

    ImageView imgStats;

    AnimationDrawable animStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        btn1 = findViewById(R.id.medCon);
        btn2 = findViewById(R.id.medTot);
        btn3 = findViewById(R.id.objMais);

        imgStats = findViewById(R.id.imgStats);

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");

        sharedPreferences = getSharedPreferences("CONF", Player.MODE_PRIVATE);

        Integer getTempo = sharedPreferences.getInt("Tempo", 0);
        Integer getSec = sharedPreferences.getInt("Sessões", 0);

        Integer getFoca = sharedPreferences.getInt("Foca", 0);
        Integer getSussa = sharedPreferences.getInt("Sussa", 0);
        Integer getDormir = sharedPreferences.getInt("Dormir", 0);

        final Integer maior = Math.max(getSussa,Math.max(getFoca,getDormir));

        if(maior == getSussa){
            btn3.setText("Ficar Sussa");
            imgStats.setImageResource(R.drawable.linha1);
        }else if(maior == getFoca) {
            btn3.setText("Olha a Foca!");
            imgStats.setImageResource(R.drawable.linha2);
        }else if(maior == getDormir) {
            btn3.setText("Dormir de boa");
            imgStats.setImageResource(R.drawable.linha3);
        }else{
            btn3.setText("");
        }

        animStats = (AnimationDrawable) imgStats.getDrawable();
        animStats.start();

        btn1.setText(String.valueOf(getSec) + " Sessões");
        btn2.setText(sdf.format(new Date(getTempo)));

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Numero total de sessões: " + String.valueOf(maior), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
