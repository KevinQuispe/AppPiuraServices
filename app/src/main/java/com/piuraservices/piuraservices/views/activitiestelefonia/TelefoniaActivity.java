package com.piuraservices.piuraservices.views.activitiestelefonia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.piuraservices.piuraservices.R;

public class TelefoniaActivity extends AppCompatActivity {

    //DECLARE VARIABLES PARA IMG TELEFONIA
    ImageView imgMovistar;
    ImageView imgClaro;
    ImageView imgEntel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefonia);
        getSupportActionBar().setTitle("Empresas de telefonia");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgMovistar=(ImageView) findViewById(R.id.img_movistar);
        imgClaro=(ImageView) findViewById(R.id.img_claro);
        imgEntel=(ImageView) findViewById(R.id.img_entel);


    }

    public void onClickedMovistar(View v) {
        imgMovistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoMovistarActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickedClaro(View v) {
        imgClaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoClaroActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickedEntel(View v) {
        imgEntel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoEntelActivity.class);
                startActivity(intent);
            }
        });
    }

}
