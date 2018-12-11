package com.piuraservices.piuraservices.views.activitiesenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleContactoEnosaActivity extends AppCompatActivity {

    TextView oficinalugar;
    TextView direccionempresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_enosa);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        oficinalugar=(TextView) findViewById(R.id.tv_oficina_enosa);
        direccionempresa=(TextView) findViewById(R.id.tv_direccion_contacto_enosa);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();

        String oficina=parametros.getString("oficinaKey");
        String direccion = parametros.getString("direccionKey");
        oficinalugar.setText(oficina);
        direccionempresa.setText(direccion);
    }
}
