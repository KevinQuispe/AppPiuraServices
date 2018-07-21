package com.piuraservices.piuraservices.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.piuraservices.piuraservices.R;

public class EPS_grauActivity extends AppCompatActivity {

    ImageView imgTramites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eps_grau);
        imgTramites=(ImageView) findViewById(R.id.img_tramites);



    }
    public void onClicked( View v){
        imgTramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activities.InformacionTramitesActivity");
                startActivity(intent);
            }
        });
    }
}
