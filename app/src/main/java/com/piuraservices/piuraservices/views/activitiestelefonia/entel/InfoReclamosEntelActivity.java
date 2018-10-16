package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.telefonia.entel.ListaInfoReclamosEntelAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.movistar.ListaInfoReclamosMovistarAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoReclamosEntelmodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoReclamosMovistarmodel;
import com.piuraservices.piuraservices.services.enosa.ListaReclamosEnosaclient;
import com.piuraservices.piuraservices.services.entel.ListaReclamosEntelclient;
import com.piuraservices.piuraservices.services.telefonia.ListaReclamosMovistarclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.InfoReclamosMovistarActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoReclamosEntelActivity extends AppCompatActivity {

    //DECLARACION DE VARIABLES
    ListView listareclamos;
    //variable para loading
    ProgressDialog progreso;
    List<InfoReclamosEpsgraumodel> list_reclamos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reclamos_entel);
        getSupportActionBar().setTitle("Infomación Reclamos Entel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listareclamos=(ListView) findViewById(R.id.list_reclamos_entel);
        listarReclamosEntel();
    }
    public void listarReclamosEntel(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //variable
        ListaReclamosEntelclient client = retrofit.create(ListaReclamosEntelclient.class); //here get la interface

        Call<List<InfoReclamosEntelmodel>> call = client.getInfoReclamosentel(5);//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoReclamosEntelmodel>>() {
            @Override
            public void onResponse(Call<List<InfoReclamosEntelmodel>> call, Response<List<InfoReclamosEntelmodel>> response) {
                if(response.isSuccessful()) {
                    List<InfoReclamosEntelmodel> model=response.body();
                    listareclamos.setAdapter(new ListaInfoReclamosEntelAdapter(InfoReclamosEntelActivity.this,model));
                    Log.i("post submitted to API.",response.body().toString());
                    progreso.dismiss();
                }
                else{
                    warningmessage();
                }
            }

            @Override
            public void onFailure(Call<List<InfoReclamosEntelmodel>> call, Throwable t) {
                progreso.dismiss();
                warningmessage();
                Toast.makeText(InfoReclamosEntelActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();

            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter1.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoReclamosEntelActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }
    public void warningmessage(){
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoReclamosEntelActivity.this);
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
