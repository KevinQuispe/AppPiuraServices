package com.piuraservices.piuraservices.views.activitiesadmin.adminclaro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class ClaroInfoTramitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claro_info_tramites);
        getSupportActionBar().setTitle("Información Trámites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
