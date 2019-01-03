package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleContactoClaroActivity extends AppCompatActivity {
    TextView centroatencion;
    TextView direccion;
    TextView telefono;
    TextView horario;
    TextView tiposervicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_claro);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //vindear datos
        centroatencion=(TextView) findViewById(R.id.tv_centro_contacto_claro);
        direccion=(TextView) findViewById(R.id.tv_direccion_contacto_claro);
        telefono=(TextView) findViewById(R.id.tv_telefono_contacto_claro);
        horario=(TextView) findViewById(R.id.tv_horario_contacto_claro);
        tiposervicio=(TextView) findViewById(R.id.tv_tiposervicio_contcto_claro);
        recibeParametros();

    }
    public  void recibeParametros(){
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String center=parametros.getString("centerKey");
        String direction = parametros.getString("direccionKey");
        String phone = parametros.getString("telefonoKey");
        String hoararioatencion = parametros.getString("horarioKey");
        String type = parametros.getString("tiposervicioKey");
        centroatencion.setText(center);
        direccion.setText(direction);
        telefono.setText(phone);
        horario.setText(hoararioatencion);
        tiposervicio.setText(type);
    }
}
