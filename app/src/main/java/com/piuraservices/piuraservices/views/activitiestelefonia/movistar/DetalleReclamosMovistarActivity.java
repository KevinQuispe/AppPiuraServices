package com.piuraservices.piuraservices.views.activitiestelefonia.movistar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleReclamosMovistarActivity extends AppCompatActivity {

    TextView descReclamo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamos_movistar);
        getSupportActionBar().setTitle("Descripción de reclamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        descReclamo=(TextView) findViewById(R.id.tv_desc_reclamo_movistar);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        descReclamo.setText(descripcion);
    }
}
