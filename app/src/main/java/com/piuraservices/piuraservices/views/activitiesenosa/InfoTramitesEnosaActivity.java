package com.piuraservices.piuraservices.views.activitiesenosa;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoReclamosEnosaAdapter;
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoTramitesEnosaAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;
import com.piuraservices.piuraservices.services.enosa.ListaReclamosEnosaclient;
import com.piuraservices.piuraservices.services.enosa.ListaTramitesEnosaclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoTramitesEpsActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTramitesEnosaActivity extends AppCompatActivity {

    ListView listaelementos;
    ArrayAdapter<InfoTramitesEnosamodel> adapter;
    //lista reclamos
    ProgressDialog progreso;
    ListView listatramites;
    ArrayList<InfoTramitesEnosamodel> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tramites_enosa);
        getSupportActionBar().setTitle("Información de Tramites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listatramites=(ListView) findViewById(R.id.list_tramitesenosa);
        listatramites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(InfoTramitesEnosaActivity.this, "Click me", Toast.LENGTH_SHORT).show();

            }
        });
        listaTramitesEnosa();
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
                listatramites.getAnimation();
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    //metodo para listar informacion de reclamos enosa
    public void listaTramitesEnosa(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaTramitesEnosaclient client = retrofit.create(ListaTramitesEnosaclient.class); //here get la interface
        Call<List<InfoTramitesEnosamodel>> call = client.getInfoTramitesenosa(1);//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoTramitesEnosamodel>>() {
            @Override
            public void onResponse(Call<List<InfoTramitesEnosamodel>> call, Response<List<InfoTramitesEnosamodel>> response) {
                List<InfoTramitesEnosamodel> model=response.body();
                listatramites.setAdapter(new ListaInfoTramitesEnosaAdapter(InfoTramitesEnosaActivity.this, model));
               // adapter = new ArrayList<InfoTramitesEnosamodel>(InfoTramitesEnosaActivity.this, android.R.layout.simple_list_item_1,model);
                //listatramites.setAdapter(adapter);
                progreso.dismiss();
            }

            @Override
            public void onFailure(Call<List<InfoTramitesEnosamodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoTramitesEnosaActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoTramitesEnosaActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }
}

