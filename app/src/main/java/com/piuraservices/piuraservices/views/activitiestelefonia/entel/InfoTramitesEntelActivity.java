package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class InfoTramitesEntelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tramites_entel);
        getSupportActionBar().setTitle("Infomación Tramites Entel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
