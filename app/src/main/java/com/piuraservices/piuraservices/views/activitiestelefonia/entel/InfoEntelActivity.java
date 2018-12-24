package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ColorFilter;
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
import com.piuraservices.piuraservices.models.enosa.InfoReferencialEnosamodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReferencialClaromodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoReferencialEntelmodel;
import com.piuraservices.piuraservices.services.claro.ListaReferencialClaroclient;
import com.piuraservices.piuraservices.services.entel.ListaReferencialEntelclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiestelefonia.claro.InfoClaroActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoEntelActivity extends AppCompatActivity {
    ImageView imgtramites;
    ImageView imgreclamos;
    ProgressDialog progreso;
    //declare variables
    public TextView direccion;
    public TextView telefono;
    public TextView correo;
    public TextView horario;
    public TextView page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_entel);
        getSupportActionBar().setTitle("Empresa Entel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramitesentel);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamosentel);
        //vindear datos
        direccion = (TextView) findViewById(R.id.tv_direccion_entel);
        telefono = (TextView) findViewById(R.id.tv_telefono_entel);
        correo = (TextView) findViewById(R.id.tv_correo_entel);
        horario = (TextView) findViewById(R.id.tv_horario_entel);
        page = (TextView) findViewById(R.id.tv_web_entel);
        listainfoEntidad();


    }

    public void listainfoEntidad() {
        process();
        final String url = Config.URL_SERVER;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ListaReferencialEntelclient servicio = retrofit.create(ListaReferencialEntelclient.class);
        Call<List<InfoReferencialEntelmodel>> call = servicio.getInfoReferencialentel();
        call.enqueue(new Callback<List<InfoReferencialEntelmodel>>() {
            @Override
            public void onResponse(Call<List<InfoReferencialEntelmodel>> call, Response<List<InfoReferencialEntelmodel>> response) {
                try {
                    for (InfoReferencialEntelmodel info : response.body()) {
                        if (info.getId() == 5) {
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
            public void onFailure(Call<List<InfoReferencialEntelmodel>> call, Throwable t) {
                Toast.makeText(InfoEntelActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClickedtramites(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent("views.activities.InfoTramitesEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoTramitesEntelActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("views.activities.InfoReclamosEpsActivity");
                Intent intent = new Intent(getApplicationContext(),InfoReclamosEntelActivity.class);
                startActivity(intent);
            }
        });
    }
    public void onClickOpenGoogleMaps(View v) {

        String centralentel="Jr. Libertad 607, Piura, Tienda Piura";
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+centralentel);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Intent chooser = Intent.createChooser(mapIntent, "Abrir Google Maps");
        startActivity(chooser);

        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(Entel Piura)");
        //startActivity( new Intent(Intent.ACTION_VIEW, uri));
        //Uri uri = Uri.parse("https://www.google.com.pe/maps/place/Entel+Per%C3%BA/@-5.1955993,-80.6261248,19z/data=!4m12!1m6!3m5!1s0x904a107c378a0833:0x22691540f5193030!2sEntel+Per%C3%BA!8m2!3d-5.1955993!4d-80.6255776!3m4!1s0x904a107c378a0833:0x22691540f5193030!8m2!3d-5.1955993!4d-80.6255776");
        //Intent intent=new Intent(Intent.ACTION_VIEW,uri);
        //Intent chooser=Intent.createChooser(intent,"Abrir Google Maps");
        //startActivity(chooser);

        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.19449, -80.6328201(EPS Grau Piura)");
        //startActivity(new Intent(Intent.ACTION_VIEW,uri));
        //Uri gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");

        //String centralentel="Jr. Libertad 607, Piura, Tienda Piura";
        //Uri gmmIntentUri = Uri.parse("geo:0,0?q="+centralentel+"Entel Piura");
        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");
        //Intent chooser = Intent.createChooser(mapIntent, "Abrir Google Maps");
        //startActivity(chooser);
    }
    public void onClickOpenEmail(View v) {

        Intent intent= new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("email"));
        String[]s={"fonocliente@enel.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,s);
        intent.putExtra(Intent.EXTRA_SUBJECT,"");
        intent.putExtra(Intent.EXTRA_TEXT,"");
        intent.setType("message/rfc822");
        Intent chooser=Intent.createChooser(intent,"Enviar Email");
        startActivity(chooser);

    }
    public void onClickOpenWeb(View v) {
        //Intent intent = new Intent("views.activities.OpenWebActivity");
        Intent intent = new Intent(InfoEntelActivity.this,OpenWebEntelActivity.class);
        startActivity(intent);
    }
    public void onClickOpenCall(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String telentel= "+511 517 1717";
        if (telentel.trim().isEmpty()) {
            i.setData(Uri.parse("tel:+511 517 1717"));
        } else {
            i.setData(Uri.parse("tel:" + telentel));
        }
        if (ActivityCompat.checkSelfPermission(getApplication(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplication(), "Please conceda permisos para llamar", Toast.LENGTH_LONG).show();
            requestPermission();
        } else {
            startActivity(i);
        }
    }
    //permisos para llamadas
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
    }
    public void process() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoEntelActivity.this, ProgressDialog.BUTTON_POSITIVE);
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
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoEntelActivity.this);
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
