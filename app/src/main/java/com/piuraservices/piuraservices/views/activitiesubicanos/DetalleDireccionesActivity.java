package com.piuraservices.piuraservices.views.activitiesubicanos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleDireccionesActivity extends AppCompatActivity {

    TextView oficina;
    TextView direccion;
    TextView telefono;
    TextView horario;
    TextView tipoatencion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_direcciones);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oficina=(TextView) findViewById(R.id.tv_oficina_contacto_mapa);
        direccion=(TextView) findViewById(R.id.tv_direccion_contacto_mapa);
        telefono=(TextView) findViewById(R.id.tv_telefono_contacto_mapa);
        horario=(TextView) findViewById(R.id.tv_horario_contacto_mapa);
        tipoatencion=(TextView) findViewById(R.id.tv_tipoatencion_mapa);
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String nombres = parametros.getString("nombreKey");
        String direction = parametros.getString("direccionKey");
        String telefonos = parametros.getString("telefonoKey");
        String horarios = parametros.getString("horarioKey");
        String tipoatencions = parametros.getString("tipoatencionKey");
        oficina.setText(nombres);
        direccion.setText(direction);
        telefono.setText(telefonos);
        horario.setText(horarios);
        tipoatencion.setText(tipoatencions);
    }
}
