package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleTramitesEpsgrauActivity extends AppCompatActivity {

    TextView descTramite;
    TextView nombreTramite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tramites_epsgrau);
        getSupportActionBar().setTitle("Descripción de trámite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descTramite=(TextView) findViewById(R.id.tv_desc_tramite_eps);
        nombreTramite=(TextView) findViewById(R.id.tv_nombre_tramite_eps);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String nombre = parametros.getString("nombreKey");
        String descripcion = parametros.getString("descripcionKey");
        nombreTramite.setText(nombre);
        descTramite.setText(descripcion);

    }
}
