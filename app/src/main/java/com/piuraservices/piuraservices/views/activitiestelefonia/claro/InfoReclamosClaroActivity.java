package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class InfoReclamosClaroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reclamos_claro);
        getSupportActionBar().setTitle("Infomación Reclamos Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
