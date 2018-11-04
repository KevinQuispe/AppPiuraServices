package com.piuraservices.piuraservices.views.activitiestelefonia.movistar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleTramitesMovistarActivity extends AppCompatActivity {

    TextView desctramite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tramites_movistar);
        getSupportActionBar().setTitle("Descripción de tramite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        desctramite=(TextView) findViewById(R.id.tv_desc_tramite_movistar);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        desctramite.setText(descripcion);
    }
}
