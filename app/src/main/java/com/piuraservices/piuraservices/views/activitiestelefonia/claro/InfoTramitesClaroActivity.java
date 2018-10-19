package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

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
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoReclamosepsAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.claro.ListaInfoReclamosClaroAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.claro.ListaInfoTramitesClaroAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReclamosClaromodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoTramitesClaromodel;
import com.piuraservices.piuraservices.services.claro.ListaReclamosClaroclient;
import com.piuraservices.piuraservices.services.claro.ListaTramitesClaroclient;
import com.piuraservices.piuraservices.services.entel.ListaTramitesEntelclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoReclamosEpsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTramitesClaroActivity extends AppCompatActivity {

    ListView listView;
    ProgressDialog progreso;
    ListView listatramites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tramites_claro);
        getSupportActionBar().setTitle("Infomación Trámites Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listatramites=(ListView) findViewById(R.id.list_tramites_claro);
        listaTramitesClaro();
    }
    public  void listaTramitesClaro(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaTramitesClaroclient client = retrofit.create(ListaTramitesClaroclient.class); //here get la interface
        //Call<InfoReclamosClaromodel> call = client.getInfoReclamosclaro(4);//here el model
        Call<List<InfoTramitesClaromodel>> call = client.getInfoTramitesclaro(4);//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoTramitesClaromodel>>() {
            @Override
            public void onResponse(Call<List<InfoTramitesClaromodel>> call, Response<List<InfoTramitesClaromodel>> response) {

                if(response.isSuccessful()) {
                    List<InfoTramitesClaromodel> model=response.body();
                    listatramites.setAdapter(new ListaInfoTramitesClaroAdapter(InfoTramitesClaroActivity.this,model));
                    Log.i("post submitted to API.",response.body().toString());
                    progreso.dismiss();
                }
                else{
                    warningmessage();
                }

            }

            @Override
            public void onFailure(Call<List<InfoTramitesClaromodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoTramitesClaroActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();

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
        progreso = new ProgressDialog(InfoTramitesClaroActivity.this, ProgressDialog.BUTTON_POSITIVE);
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
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoTramitesClaroActivity.this);
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
