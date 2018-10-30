package com.piuraservices.piuraservices.views.activitiesenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleReclamosEnosaActivity extends AppCompatActivity {

    TextView descReclamo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_reclamos_enosa);
        getSupportActionBar().setTitle("Descripción de reclamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descReclamo=(TextView) findViewById(R.id.tv_desc_reclamo_enosa);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        descReclamo.setText(descripcion);

    }
}
