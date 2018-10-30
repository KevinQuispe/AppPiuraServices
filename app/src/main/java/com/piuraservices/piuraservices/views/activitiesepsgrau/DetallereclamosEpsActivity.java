package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetallereclamosEpsActivity extends AppCompatActivity {

    TextView descReclamo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallereclamos_eps);
        getSupportActionBar().setTitle("Descripción de reclamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descReclamo=(TextView) findViewById(R.id.tv_desc_reclamo_eps);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        descReclamo.setText(descripcion);

        Log.i("DisplayMessage", "RECIBIR message = " + descReclamo);
    }
}
