package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.telefonia.claro.ListaInfoReclamosClaroAdapter;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReclamosClaromodel;
import com.piuraservices.piuraservices.services.claro.ListaReclamosClaroclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activities.ContactoDetalleActivity;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoReclamosClaroActivity extends AppCompatActivity {

    ListView listView;
    ProgressDialog progreso;
    ListView listareclamosclaro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reclamos_claro);
        getSupportActionBar().setTitle("Infomación Reclamos Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listareclamosclaro = (ListView) findViewById(R.id.list_reclamos_claro);
        listaReclamosClaro();
    }
    public  void listaReclamosClaro(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaReclamosClaroclient client = retrofit.create(ListaReclamosClaroclient.class); //here get la interface
        Call<List<InfoReclamosClaromodel>> call = client.getInfoReclamosclaro(4);//here el model
        dialog();
        call.enqueue(new Callback<List<InfoReclamosClaromodel>>() {
            @Override
            public void onResponse(Call<List<InfoReclamosClaromodel>> call, Response<List<InfoReclamosClaromodel>> response) {

                if(response.isSuccessful()) {
                    List<InfoReclamosClaromodel> model=response.body();
                    listareclamosclaro.setAdapter(new ListaInfoReclamosClaroAdapter(InfoReclamosClaroActivity.this,model));
                    Log.i("post submitted to API.",response.body().toString());
                    progreso.dismiss();
                }
                else{
                    warningmessage();
                }
            }
            @Override
            public void onFailure(Call<List<InfoReclamosClaromodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoReclamosClaroActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();

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
        progreso = new ProgressDialog(InfoReclamosClaroActivity.this, ProgressDialog.BUTTON_POSITIVE);
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
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoReclamosClaroActivity.this);
        alertaDeError2.setTitle("Advertencia");
        alertaDeError2.setMessage("Recargar Información.");
        alertaDeError2.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertaDeError2.create();
        alertaDeError2.show();
    }
}