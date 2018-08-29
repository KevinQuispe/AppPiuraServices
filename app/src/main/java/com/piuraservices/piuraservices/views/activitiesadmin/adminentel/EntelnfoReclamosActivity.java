package com.piuraservices.piuraservices.views.activitiesadmin.adminentel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.piuraservices.piuraservices.R;

public class EntelnfoReclamosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entelnfo_reclamos);
        getSupportActionBar().setTitle("Información Reclamos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
