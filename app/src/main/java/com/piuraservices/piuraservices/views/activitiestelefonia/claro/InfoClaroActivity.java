package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReferencialClaromodel;
import com.piuraservices.piuraservices.services.claro.ListaReferencialClaroclient;
import com.piuraservices.piuraservices.services.epsgrau.ListaReferencialEpsclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesenosa.EnosaActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.InfoMovistarActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoClaroActivity extends AppCompatActivity {
    ImageView imgtramites;
    ImageView imgreclamos;
    ProgressDialog progreso;
    //declare variables
    public TextView direccion;
    public TextView telefono;
    public TextView correo;
    public TextView horario;
    public TextView page;
    String calltelefono="";
    String sendemail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_claro);
        getSupportActionBar().setTitle("Empresa Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesclaro);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosclaro);
        //vindear datos
        direccion = (TextView) findViewById(R.id.tv_direccion_claro);
        telefono = (TextView) findViewById(R.id.tv_telefono_claro);
        correo = (TextView) findViewById(R.id.tv_email_claro);
        horario = (TextView) findViewById(R.id.tv_horario_claro);
        page = (TextView) findViewById(R.id.tv_web_claro);

        listainfoEntidad();

    }
    public void listainfoEntidad() {
        process();
        final String url = Config.URL_SERVER;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ListaReferencialClaroclient servicio = retrofit.create(ListaReferencialClaroclient.class);
        Call<List<InfoReferencialClaromodel>> call = servicio.getInfoReferencialclaro();
        call.enqueue(new Callback<List<InfoReferencialClaromodel>>() {
            @Override
            public void onResponse(Call<List<InfoReferencialClaromodel>> call, Response<List<InfoReferencialClaromodel>> response) {
                try {
                    for (InfoReferencialClaromodel info : response.body()) {
                        if (info.getId() == 4) {
                            Log.e("DIRECCION", info.getDireccion() + "\nCORREO:" + info.getCorreo());
                            direccion.setText(info.getDireccion().toString());
                            telefono.setText(info.getTelefono().toString());
                            correo.setText(info.getCorreo().toString());
                            horario.setText(info.getHorario().toString());
                            page.setText(info.getWebentidad().toString());
                            progreso.hide();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<List<InfoReferencialClaromodel>> call, Throwable t) {
                Toast.makeText(InfoClaroActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickedtramites(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoTramitesClaroActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("views.activities.InfoReclamosEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoReclamosClaroActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickOpenGoogleMaps(View v) {

        String centralclaro="Claro Piura, Open Plaza";
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+centralclaro);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Intent chooser = Intent.createChooser(mapIntent, "Abrir Google Maps");
        startActivity(chooser);

    }
    //funcion para enviar email
    public void onClickOpenEmail(View v) {
        final String url = Config.URL_SERVER;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ListaReferencialEpsclient servicio = retrofit.create(ListaReferencialEpsclient.class);
        Call<List<InfoReferencialEpsgraumodel>> call = servicio.getInfoReferencialeps();
        call.enqueue(new Callback<List<InfoReferencialEpsgraumodel>>() {
            @Override
            public void onResponse(Call<List<InfoReferencialEpsgraumodel>> call, Response<List<InfoReferencialEpsgraumodel>> response) {
                try {
                    for (InfoReferencialEpsgraumodel info : response.body()) {
                        if (info.getId() == 4) {
                            Log.e("DIRECCION", info.getDireccion() + "\nTELEFONO:" + info.getTelefono());
                            sendemail = info.getCorreo().toString();
                            //metodo para enviar email
                            Intent intent = new Intent(Intent.ACTION_SEND);
                            intent.setData(Uri.parse("email"));
                            String[] email = {sendemail};
                            intent.putExtra(Intent.EXTRA_EMAIL, email);
                            intent.putExtra(Intent.EXTRA_SUBJECT, " ");
                            intent.putExtra(Intent.EXTRA_TEXT, " ");
                            intent.setType("message/rfc822");
                            Intent chooser = Intent.createChooser(intent, "Enviar Email");
                            startActivity(chooser);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<InfoReferencialEpsgraumodel>> call, Throwable t) {
                //Toast.makeText(InfoClaroActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                //metodo para enviar email
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("email"));
                String[] email = {"contacto@claro.com.pe"};
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, " ");
                intent.putExtra(Intent.EXTRA_TEXT, " ");
                intent.setType("message/rfc822");
                Intent chooser = Intent.createChooser(intent, "Enviar Email");
                startActivity(chooser);
            }
        });
    }
    public void onClickOpenWeb(View v) {

        Intent intent = new Intent(InfoClaroActivity.this,OpenWebClaroActivity.class);
        startActivity(intent);
    }
    //funcion para hacer llamadas
    public void onClickOpenCall(View v) {
        try {
            final String url = Config.URL_SERVER;
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            ListaReferencialEpsclient servicio = retrofit.create(ListaReferencialEpsclient.class);
            Call<List<InfoReferencialEpsgraumodel>> call = servicio.getInfoReferencialeps();
            call.enqueue(new Callback<List<InfoReferencialEpsgraumodel>>() {
                @Override
                public void onResponse(Call<List<InfoReferencialEpsgraumodel>> call, Response<List<InfoReferencialEpsgraumodel>> response) {
                    try {
                        for (InfoReferencialEpsgraumodel info : response.body()) {
                            if (info.getId() == 4) {
                                Log.e("DIRECCION", info.getDireccion() + "\nTELEFONO:" + info.getTelefono());
                                calltelefono = info.getTelefono().toString();
                                //funciona para llamar
                                Intent i = new Intent(Intent.ACTION_DIAL);
                                String claro = calltelefono;
                                if (claro.trim().isEmpty()) {
                                    i.setData(Uri.parse("tel:" + claro));
                                } else {
                                    i.setData(Uri.parse("tel:" + claro));
                                }
                                if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    Toast.makeText(getApplication(), "Please conceda permisos para llamar", Toast.LENGTH_LONG).show();
                                    requestPermission();
                                } else {
                                    startActivity(i);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<List<InfoReferencialEpsgraumodel>> call, Throwable t) {
                    //Toast.makeText(InfoClaroActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    //funciona para llamar
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    String claro = "946 573 918";
                    if (claro.trim().isEmpty()) {
                        i.setData(Uri.parse("tel:" + claro));
                    } else {
                        i.setData(Uri.parse("tel:" + claro));
                    }
                    if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplication(), "Please conceda permisos para llamar", Toast.LENGTH_LONG).show();
                        requestPermission();
                    } else {
                        startActivity(i);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //permisos para llamadas
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }
    public void process() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoClaroActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        //progreso.setCancelable(false);
    }

    public void warningmessage() {
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoClaroActivity.this);
        alertaDeError2.setTitle("Advertencia");
        alertaDeError2.setMessage("Recargar la información .");
        alertaDeError2.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertaDeError2.create();
        alertaDeError2.show();
    }

}
