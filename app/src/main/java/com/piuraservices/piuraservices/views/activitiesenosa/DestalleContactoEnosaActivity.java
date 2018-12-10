package com.piuraservices.piuraservices.views.activitiesenosa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.piuraservices.piuraservices.R;

public class DestalleContactoEnosaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destalle_contacto_enosa);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
