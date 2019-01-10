package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;

import java.io.File;

public class DetallereclamosEpsActivity extends AppCompatActivity {

    TextView descReclamo;
    TextView nombreReclamo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detallereclamos_eps);
        getSupportActionBar().setTitle("Descripción de reclamo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        descReclamo=(TextView) findViewById(R.id.tv_desc_reclamo_eps);
        nombreReclamo=(TextView) findViewById(R.id.tv_nombre_reclamo_eps);
        recibeDatosInfoReclamos();

    }
    //recibe datos del los reclamos
    public void recibeDatosInfoReclamos(){
        //recibir parametros
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcionurl = parametros.getString("descripcionKey");
        String nombre = parametros.getString("nombreKey ");
        descReclamo.setText(descripcionurl);
        //nombreReclamo.setText(nombre);
        Toast.makeText(DetallereclamosEpsActivity.this,"Click en el Link",Toast.LENGTH_SHORT).show();

    }
    //abrir navegador web pasandole la urle del reclamo
      public void openNavegadorWeb(View v){
          Intent thisForm = getIntent();
          Bundle parametros = thisForm.getExtras();
          String descripcionurl = parametros.getString("descripcionKey");
              Uri uri = Uri.parse(descripcionurl.toString());
              Intent intent = new Intent(Intent.ACTION_VIEW, uri);
              startActivity(intent);
          }

    //metodo abrir infor en la web al hacer click
    public void onClickOpenweb(View v) {
        //capturar los datos
        Intent thisForm = getIntent();
        Bundle parametros = thisForm.getExtras();
        String descripcion = parametros.getString("descripcionKey");
        String descripcionreclamo =descripcion;
        //pasare el dato al intent
        Intent intent = new Intent(DetallereclamosEpsActivity.this, OpenWebEpsGrauActivity.class);
        parametros.putString("descripcionKey",descripcionreclamo);
        intent.putExtras(parametros);
        startActivity(intent);
    }

    //metodo onItemLongCLick
    private void onItemLongCLick(int position) {
        final int pos = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
        builder.setMessage("¿Que desea hacer?").setPositiveButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //editarDetalle(lista.get(pos));
            }
        })
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //editarDetalle(lista.get(pos));
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
