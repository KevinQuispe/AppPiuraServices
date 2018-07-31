package com.piuraservices.piuraservices.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.piuraservices.piuraservices.R;

public class EnosaActivity extends AppCompatActivity {

    ImageView imgtramites;
    ImageView imgreclamos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enosa);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesenosa);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosenosa);
}

    public void onClicked(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activities.InformacionTramitesActivity");
                startActivity(intent);
            }
        });
    }
    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activities.InformacionTramitesActivity");
                startActivity(intent);
            }
        });
    }
}
