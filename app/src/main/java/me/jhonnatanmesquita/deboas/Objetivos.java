package me.jhonnatanmesquita.deboas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Objetivos extends AppCompatActivity {

    Toolbar toolbar;

    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;

    ImageView imgLinha1, imgLinha2, imgLinha3;

    AnimationDrawable animLinha1, animLinha2, animLinha3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objetivos);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Objetivos");

        LinearLayout linha1 = findViewById(R.id.linha1);
        LinearLayout linha2 = findViewById(R.id.linha2);
        LinearLayout linha3 = findViewById(R.id.linha3);

        imgLinha1 = findViewById(R.id.imgLinha1);
        imgLinha2 = findViewById(R.id.imgLinha2);
        imgLinha3 = findViewById(R.id.imgLinha3);

        imgLinha1.setImageResource(R.drawable.linha1);
        animLinha1 = (AnimationDrawable) imgLinha1.getDrawable();
        animLinha1.start();

        imgLinha2.setImageResource(R.drawable.linha2);
        animLinha2 = (AnimationDrawable) imgLinha2.getDrawable();
        animLinha2.start();

        imgLinha3.setImageResource(R.drawable.linha3);
        animLinha3 = (AnimationDrawable) imgLinha3.getDrawable();
        animLinha3.start();


        sharedPreferences = getSharedPreferences("CONF", MODE_PRIVATE);

        editor = sharedPreferences.edit();


        linha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer getSussa = sharedPreferences.getInt("Sussa", 0);

                getSussa++;

                editor.putInt("Sussa", getSussa);
                editor.apply();

                Intent intent = new Intent(v.getContext(), Player.class);
                intent.putExtra("Objetivo", "Ficar Sussa");
                startActivity(intent);
            }
        });

        linha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer getFoca = sharedPreferences.getInt("Foca", 0);

                getFoca++;

                editor.putInt("Foca", getFoca);
                editor.apply();

                Intent intent = new Intent(v.getContext(), Player.class);
                intent.putExtra("Objetivo", "Olha a foca!");
                startActivity(intent);
            }
        });

        linha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer getDormir = sharedPreferences.getInt("Dormir", 0);

                getDormir++;

                editor.putInt("Dormir", getDormir);
                editor.apply();

                Intent intent = new Intent(v.getContext(), Player.class);
                intent.putExtra("Objetivo", "Dormir de boa");
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.button_item);
        ImageView btn = item.getActionView().findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Stats.class);
                startActivity(intent);
            }
        });
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        animLinha1.stop();
        animLinha2.stop();
        animLinha3.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        animLinha1.start();
        animLinha2.start();
        animLinha3.start();
    }

}
