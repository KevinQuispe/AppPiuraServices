package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;
import com.piuraservices.piuraservices.services.epsgrau.ListaReferencialEpsclient;
import com.piuraservices.piuraservices.utils.Config;

import java.text.BreakIterator;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EPS_grauActivity extends AppCompatActivity {
    ImageView imgtramites;
    ImageView imgreclamos;
    ProgressDialog ringProgressDialog;
    ProgressDialog progreso;
    TextView direccion;
    TextView telefono;
    TextView correo;
    TextView horario;
    TextView page;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eps_grau);
        getSupportActionBar().setTitle("Entidad EPS Grau S.A");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgtramites = (ImageView) findViewById(R.id.img_tramiteseps);
        imgreclamos = (ImageView) findViewById(R.id.img_reclamoseps);
        //infor getentidad
        direccion = (TextView) findViewById(R.id.tv_direccion_eps);
        telefono = (TextView) findViewById(R.id.tv_telefono_eps);
        correo = (TextView) findViewById(R.id.tv_email_eps);
        horario = (TextView) findViewById(R.id.tv_horario_eps);
        page = (TextView) findViewById(R.id.tv_web_eps);

        //call peticoon to retrofit
        //listaentidadasync();

        //lista funcion call lista ok
        //listainfoEntidad();

    }

    //retorna info ok
    private void listaentidadasync() {
        new Peticion().execute();
    }

    class Peticion extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            final String url = Config.URL_SERVER;
            Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
            ListaReferencialEpsclient servicio = retrofit.create(ListaReferencialEpsclient.class);
            Call<List<InfoReferencialEpsgraumodel>> response = servicio.getInfoReferencialeps();

            try {
                for (InfoReferencialEpsgraumodel info : response.execute().body()) {
                    Log.e("DIRECCION", info.getDireccion() + "CORREO:" + info.getCorreo());

                    if (info.getId() == 1) {
                        direccion.setText(info.getDireccion().toString());
                        telefono.setText(response.execute().body().get(1).getTelefono().toString());
                        correo.setText(info.getCorreo().toString());
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    //lista informacion de entidad
    public void listainfoEntidad() {
        // process();
        final String url = Config.URL_SERVER;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ListaReferencialEpsclient servicio = retrofit.create(ListaReferencialEpsclient.class);

        Call<List<InfoReferencialEpsgraumodel>> call = servicio.getInfoReferencialeps();
        call.enqueue(new Callback<List<InfoReferencialEpsgraumodel>>() {
            @Override
            public void onResponse(Call<List<InfoReferencialEpsgraumodel>> call, Response<List<InfoReferencialEpsgraumodel>> response) {
                for (InfoReferencialEpsgraumodel info : response.body()) {
                    if (info.getId() == 1) {
                        Log.e("DIRECCION", info.getDireccion() + "\nCORREO:" + info.getCorreo());
                        direccion.setText(info.getDireccion().toString());
                        telefono.setText(info.getTelefono().toString());
                        correo.setText(info.getCorreo().toString());
                        horario.setText(info.getHorario().toString());
                        page.setText(info.getWebentidad().toString());

                        //direccion.setText(response.body().get(1).getDireccion());
                        //telefono.setText(response.body().get(2).getTelefono());
                        //correo.setText(response.body().get(3).getCorreo());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<InfoReferencialEpsgraumodel>> call, Throwable t) {
                Toast.makeText(EPS_grauActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //metodo para listar datos referenciales de la entidad
    public void listainfoEntidadCall() {
        process();
        final String url = Config.URL_SERVER;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        ListaReferencialEpsclient servicio = retrofit.create(ListaReferencialEpsclient.class);
        Call<InfoReferencialEpsgraumodel> call = servicio.getInfoReferencial(1);
        call.enqueue(new Callback<InfoReferencialEpsgraumodel>() {
            @Override
            public void onResponse(Call<InfoReferencialEpsgraumodel> call, Response<InfoReferencialEpsgraumodel> response) {
                try {
                    direccion.setText(response.body().getDireccion().toString());
                    telefono.setText(response.body().getTelefono().toString());
                    System.out.println(response);
                    progreso.hide();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<InfoReferencialEpsgraumodel> call, Throwable t) {
                try {
                    Toast.makeText(EPS_grauActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
                    progreso.hide();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //tramites lista
    public void onClickedtramites(View v) {
        imgtramites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activitiesepsgrau.InfoTramitesEpsActivity");
                //Intent intent = new Intent(getApplicationContext(), com.piuraservices.piuraservices.views.activitiesepsgrau.InfoTramitesEpsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickedreclamos(View v) {
        imgreclamos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activitiesepsgrau.InfoReclamosEpsActivity");
                //Intent intent = new Intent(getApplicationContext(),InfoReclamosEpsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClickOpenGoogleMaps(View v) {

        //Uri uri = Uri.parse("https://www.google.com.pe/maps/place/EPS+GRAU+S.A./@-5.1909756,-80.6422337,17z/data=!4m12!1m6!3m5!1s0x904a1a85053ff85b:0xd40ce7e54eb08b5e!2sEPS+GRAU+S.A.!8m2!3d-5.1908153!4d-80.6415363!3m4!1s0x904a1a85053ff85b:0xd40ce7e54eb08b5e!8m2!3d-5.1908153!4d-80.6415363");
        //Uri uri = Uri.parse("geo:41.3825581,2.1704375?z=16&q=-5.1907272,-80.6634602(EPS Grau Piura)");
        //startActivity( new Intent(Intent.ACTION_VIEW, uri));
        //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //Intent chooser = Intent.createChooser(intent, "Abrir Google Maps");
        //startActivity(chooser);
        String centralepsgrau="EPS GRAU S.A., La Arena, Piura";
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+centralepsgrau);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        Intent chooser = Intent.createChooser(mapIntent, "Abrir Google Maps");
        startActivity(chooser);
    }

    public void onClickOpenEmail(View v) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("email"));
        String[] s = {"epsgrau.imageninstitucional@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL, s);
        intent.putExtra(Intent.EXTRA_SUBJECT, " ");
        intent.putExtra(Intent.EXTRA_TEXT, " ");
        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent, "Enviar Email");
        startActivity(chooser);

    }

    public void onClickOpenWeb(View v) {
        //Uri uri = Uri.parse("https://www.epsgrau.pe/webpage/desktop/views/");
        //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //startActivity(intent);
        //WebView webView=new WebView(this);
        //webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("https://www.epsgrau.pe/webpage/desktop/views/");
        //Intent intent = new Intent("views.activities.OpenWebActivity");
        loadingpage(v);
        Intent intent = new Intent(EPS_grauActivity.this, OpenWebEpsGrauActivity.class);
        startActivity(intent);


    }

    public void onClickOpenCall(View v) {
        Intent i = new Intent(Intent.ACTION_DIAL);
        String spsgrau = "080026026";
        if (spsgrau.trim().isEmpty()) {
            i.setData(Uri.parse("tel:080026026"));
        } else {
            i.setData(Uri.parse("tel:" + spsgrau));
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

    //new with timer
    public void loadingpage(View view) {
        ringProgressDialog = ProgressDialog.show(EPS_grauActivity.this, "Please wait ...", "Loading...", true);
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
        progreso = new ProgressDialog(EPS_grauActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }

    public void warningmessage() {
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(EPS_grauActivity.this);
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
