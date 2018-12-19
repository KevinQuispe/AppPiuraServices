package com.piuraservices.piuraservices.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class ContactoDetalleActivity extends AppCompatActivity {
    TextView oficinalugar,direccionempresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_detalle);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oficinalugar=(TextView) findViewById(R.id.tv_nombre_empresa);
        direccionempresa=(TextView) findViewById(R.id.tv_contacto_empresa);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String oficina=parametros.getString("oficinaKey");
        String direccion = parametros.getString("direccionKey");
        oficinalugar.setText(oficina);
        direccionempresa.setText(direccion);
    }

}
