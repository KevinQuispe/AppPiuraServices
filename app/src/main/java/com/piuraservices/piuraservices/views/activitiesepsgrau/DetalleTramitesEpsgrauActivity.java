package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleTramitesEpsgrauActivity extends AppCompatActivity {

    TextView descTramite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tramites_epsgrau);
        getSupportActionBar().setTitle("Descripción de Tramite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descTramite=(TextView) findViewById(R.id.tv_desc_tramite_eps);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        descTramite.setText(descripcion);
    }
}
