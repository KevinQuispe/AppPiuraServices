package com.piuraservices.piuraservices.views.activitiestelefonia.movistar;

import android.Manifest;
import android.app.ProgressDialog;
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
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoReferencialMovistarmodel;
import com.piuraservices.piuraservices.services.claro.ListaReferencialClaroclient;
import com.piuraservices.piuraservices.services.epsgrau.ListaReferencialEpsclient;
import com.piuraservices.piuraservices.services.telefonia.ListaReferencialMovistarclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesenosa.EnosaActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.claro.InfoClaroActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoMovistarActivity extends AppCompatActivity {

    ImageView imgtramites;
    ImageView imgreclamos;
    ProgressDialog ringProgressDialog;
    ProgressDialog progreso;
    //decrlare variables para informacion referencial
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
        setContentView(R.layout.activity_info_movistar);
        getSupportActionBar().setTitle("Empresa Movistar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesmovistar);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosmovistar);
        //vindear datos
        direccion = (TextView) findViewById(R.id.tv_direccion_movistar);
        telefono = (TextView) findViewById(R.id.tv_telefono_movistar);
        correo = (TextView) findViewById(R.id.tv_correo_movistar);
        horario = (TextView) findViewById(R.id.tv_horario_movistar);
        page = (TextView) findViewById(R.id.tv_web_movistar);
        //call metodo
        listainfoEntidad();

    }

    public void listainfoEntidad() {
        process();
        final String url = Config.URL_SERVER;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ListaReferencialMovistarclient servicio = retrofit.create(ListaReferencialMovistarclient.class);
        Call<List<InfoReferencialMovistarmodel>> call = servicio.getInfoReferencialmovistar();
        call.enqueue(new Callback<List<InfoReferencialMovistarmodel>>() {
            @Override
            public void onResponse(Call<List<InfoReferencialMovistarmodel>> call, Response<List<InfoReferencialMovistarmodel>> response) {
                try {
                    for (InfoReferencialMovistarmodel info : response.body()) {
                        if (info.getId() == 3) {
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
            public void onFailure(Call<List<InfoReferencialMovistarmodel>> call, Throwable t) {
                Toast.makeText(InfoMovistarActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickedtramites(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(), InfoTramitesMovistarActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("views.activities.InfoReclamosEpsActivity");
                Intent intent = new Intent(getApplicationContext(), InfoReclamosMovistarActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickOpenGoogleMaps(View v) {

        String centralmovistar = "Movistar, Loreto, Piura";
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + centralmovistar);
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
                        if (info.getId() == 3) {
                            Log.e("DIRECCION", info.getDireccion() + "\nTELEFONO:" + info.getTelefono());
                            sendemail = info.getCorreo().toString();
                            //metodo para  enviar correo
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
                //Toast.makeText(InfoMovistarActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                //metodo para  enviar correo
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("email"));
                String[] email = {"registrocontactos@movistar.com.pe"};
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
        //Intent intent = new Intent("views.activities.OpenWebActivity");
        Intent intent = new Intent(InfoMovistarActivity.this, OpenWebMovistarActivity.class);
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
                            if (info.getId() == 3) {
                                Log.e("DIRECCION", info.getDireccion() + "\nTELEFONO:" + info.getTelefono());
                                calltelefono = info.getTelefono().toString();
                                //funciona para llamar
                                Intent i = new Intent(Intent.ACTION_DIAL);
                                String movistar = calltelefono;
                                if (movistar.trim().isEmpty()) {
                                    i.setData(Uri.parse("tel:" + movistar));
                                } else {
                                    i.setData(Uri.parse("tel:" + movistar));
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
                    //Toast.makeText(InfoMovistarActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    //funciona para llamar
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    String movistar = "(073)28-4032";
                    if (movistar.trim().isEmpty()) {
                        i.setData(Uri.parse("tel:" + movistar));
                    } else {
                        i.setData(Uri.parse("tel:" + movistar));
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

    //new with timer
    public void loadingpage(View view) {
        ringProgressDialog = ProgressDialog.show(InfoMovistarActivity.this, "Please wait ...", "Loading...", true);
        ringProgressDialog.setCancelable(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ringProgressDialog.dismiss();
            }
        }).start();
    }

    //
    public void process() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoMovistarActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();

    }

}
