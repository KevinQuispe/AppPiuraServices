package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleTramitesEntelActivity extends AppCompatActivity {

    TextView desctramite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tramites_entel);
        getSupportActionBar().setTitle("Descripción de tramite");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        desctramite=(TextView) findViewById(R.id.tv_desc_tramites_entel);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        desctramite.setText(descripcion);
    }
}
