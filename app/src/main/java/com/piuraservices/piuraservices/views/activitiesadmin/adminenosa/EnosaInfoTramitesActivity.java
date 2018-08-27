package com.piuraservices.piuraservices.views.activitiesadmin.adminenosa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class EnosaInfoTramitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_tramites);
        getSupportActionBar().setTitle("Información Trámites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
