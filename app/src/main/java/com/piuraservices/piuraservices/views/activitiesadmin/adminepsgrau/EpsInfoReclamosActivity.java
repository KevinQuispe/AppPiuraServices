package com.piuraservices.piuraservices.views.activitiesadmin.adminepsgrau;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.services.http;

import org.apache.http.Header;

public class EpsInfoReclamosActivity extends AppCompatActivity {

    EditText nombrereclamo, descreclamo;
    Button btnreclamo;
    ProgressDialog progreso, progressDialog; //variable para loading

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_informcion_reclamos);
        getSupportActionBar().setTitle("Información de reclamos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nombrereclamo = (EditText) findViewById(R.id.et_nombrereclamoeps);
        descreclamo = (EditText) findViewById(R.id.et_descreclamoeps);
        btnreclamo = (Button) findViewById(R.id.btn_guardarreclamoseps);
        btnreclamo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn_guardarreclamoseps) {
                    registrarInfoReclamos();
                }
            }
        });
    }

    public void registrarInfoReclamos() {

            String nom, desc;
            nom = nombrereclamo.getText().toString();
            desc = descreclamo.getText().toString();
            int id_empresa=1;
            InfoReclamosEpsgraumodel info = new InfoReclamosEpsgraumodel(1,id_empresa, nom, desc);
            process1();
            http.post(getApplicationContext(), "informacion/createInfoReclamo", info, new TextHttpResponseHandler() {

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    progreso.dismiss();
                    System.out.println(responseString);
                    Toast.makeText(getApplicationContext(), "error en conexion", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    progreso.dismiss();
                    System.out.println(responseString);
                    Toast.makeText(getApplicationContext(), "Se registró con éxito", Toast.LENGTH_SHORT).show();

                }
            });

        }
    //loadingDialog
    public  void loading(){
        progressDialog = new ProgressDialog(EpsInfoReclamosActivity.this);
        progressDialog.setMessage("Loading"); // Setting Message
        progressDialog.setTitle("Procesando.."); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }
    public void process1() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.BUTTON_NEUTRAL);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }
    //metodo de carga
    public void process2() {
        ProgressDialog progreso = new ProgressDialog(EpsInfoReclamosActivity.this);
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        progreso.show();
        progreso.setCancelable(false);
    }
    //selected
    public void alertDialogo(){
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(EpsInfoReclamosActivity.this);
        alertaDeError2.setTitle("Advertencia");
        alertaDeError2.setMessage("Selecione una alternativa para continuar.");
        alertaDeError2.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertaDeError2.create();
        alertaDeError2.show();
    }
}

