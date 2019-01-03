package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;

public class DetalleContactoEpsActivity extends AppCompatActivity {

    TextView oficina;
    TextView direccion;
    TextView telefono;
    TextView horario;
    TextView tiposervicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto_eps);
        getSupportActionBar().setTitle("Detalle de contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oficina=(TextView) findViewById(R.id.tv_oficina_contacto_eps);
        direccion=(TextView) findViewById(R.id.tv_direccion_contacto_eps);
        telefono=(TextView) findViewById(R.id.tv_telefono_contacto_eps);
        horario=(TextView) findViewById(R.id.tv_horario_contacto_eps);
        tiposervicio=(TextView) findViewById(R.id.tv_tiposervicio_contcto_eps);
        recibeParametros();
    }
    public  void recibeParametros(){
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String empresa = parametros.getString("nombreKey");
        String direction = parametros.getString("direccionKey");
        String horarios = parametros.getString("horarioKey");
        String telefonos = parametros.getString("telefonoKey");
        String tipoatencions = parametros.getString("tiposervicioKey");
        oficina.setText(empresa);
        direccion.setText(direction);
        telefono.setText(telefonos);
        horario.setText(horarios);
        tiposervicio.setText(tipoatencions);
    }
}
