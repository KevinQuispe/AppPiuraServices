package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleReclamosClaroActivity extends AppCompatActivity {

    TextView descReclamo;
    TextView nombreReclamo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamos_claro);
        getSupportActionBar().setTitle("Descripción de reclamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        descReclamo=(TextView) findViewById(R.id.tv_desc_reclamo_claro);
        nombreReclamo=(TextView) findViewById(R.id.tv_nombre_reclamo_claro);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        String nombre = parametros.getString("nombreKey");
        descReclamo.setText(descripcion);
        nombreReclamo.setText(nombre);

    }
}
