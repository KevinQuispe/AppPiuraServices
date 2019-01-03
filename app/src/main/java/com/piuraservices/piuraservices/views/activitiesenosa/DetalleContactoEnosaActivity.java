package com.piuraservices.piuraservices.views.activitiesenosa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleContactoEnosaActivity extends AppCompatActivity {

    TextView centroatencion;
    TextView direccion;
    TextView telefono;
    TextView horario;
    TextView tiposervicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_enosa);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //vindear datos
        centroatencion=(TextView) findViewById(R.id.tv_centro_contacto_enosa);
        direccion=(TextView) findViewById(R.id.tv_direccion_contacto_enosa);
        telefono=(TextView) findViewById(R.id.tv_telefono_contacto_enosa);
        horario=(TextView) findViewById(R.id.tv_horario_contacto_enosa);
        tiposervicio=(TextView) findViewById(R.id.tv_tiposervicio_contcto_enosa);
        recibeParametros();

    }
    public void recibeParametros(){
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String center=parametros.getString("nombreKey");
        String direction = parametros.getString("direccionKey");
        String phone = parametros.getString("telefonoKey");
        String horarioatencion = parametros.getString("horarioKey");
        String type = parametros.getString("tiposervicioKey");
        centroatencion.setText(center);
        direccion.setText(direction);
        telefono.setText(phone);
        horario.setText(horarioatencion);
        tiposervicio.setText(type);
    }
    public void getTelfefono(){
        String call=telefono.getText().toString();

    }
}
