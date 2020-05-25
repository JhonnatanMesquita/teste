package me.jhonnatanmesquita.deboas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Integer x = 0;

    Button btnProximo;
    ImageView imagem;
    TextView txtTuto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        btnProximo = findViewById(R.id.btnProximo);
        imagem = findViewById(R.id.imgTuto);
        txtTuto = findViewById(R.id.txtTuto);

        if(x == 0){
            txtTuto.setText(R.string.tela_um);
            imagem.setImageResource(R.drawable.welcomeredefor);
        }

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x++;
                switch (x){
                    case 1: txtTuto.setText(R.string.tela_dois);
                            imagem.setImageResource(R.drawable.tiririca2);
                            break;
                    case 2: txtTuto.setText(R.string.tela_tres);
                            imagem.setImageResource(R.drawable.tiririca1);
                            break;
                    case 3: Intent intent = new Intent(MainActivity.this, Objetivos.class);
                            startActivity(intent);
                            x = 0;
                            break;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (x == 0) {
            txtTuto.setText(R.string.tela_um);
        }
    }
}
