package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.piuraservices.piuraservices.R;

public class InfoContactosEntelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contactos_entel);
        getSupportActionBar().setTitle("Información de Contactos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
