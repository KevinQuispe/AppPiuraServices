package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoReclamosepsAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.services.epsgrau.ListaReclamosEpsclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activities.ContactoDetalleActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoReclamosEpsActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    //variables retrofit
    ListView listView;
    //variable para loading
    ProgressDialog progreso, progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reclamos_eps);
        getSupportActionBar().setTitle("Información de Reclamos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //String[] informacion = {"Información Referencial", "Información de Reclamos", "Información de Trámites"};
        //Inflater inflater;
        //listaelementos=(ListView) findViewById(R.id.list_reclamoseps);
        //adapter = new ArrayAdapter<String>(InfoReclamosEpsActivity.this, android.R.layout.simple_list_item_1, informacion);
        //listaelementos.setAdapter(adapter);

        listView = (ListView) findViewById(R.id.list_reclamoseps);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(InfoReclamosEpsActivity.this, ContactoDetalleActivity.class);
                startActivity(intent);
            }
        });
        listaReclamosEPS();

    }
    public  void listaReclamosEPS(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaReclamosEpsclient client = retrofit.create(ListaReclamosEpsclient.class); //here get la interface
        Call<List<InfoReclamosEpsgraumodel>> call = client.getInfoReclamoseps();//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoReclamosEpsgraumodel>>() {
            @Override
            public void onResponse(Call<List<InfoReclamosEpsgraumodel>> call, Response<List<InfoReclamosEpsgraumodel>> response) {
                List<InfoReclamosEpsgraumodel> model=response.body();
                listView.setAdapter(new ListaInfoReclamosepsAdapter(InfoReclamosEpsActivity.this, model));
                progreso.dismiss();
            }

            @Override
            public void onFailure(Call<List<InfoReclamosEpsgraumodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoReclamosEpsActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
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
        progreso = new ProgressDialog(InfoReclamosEpsActivity.this, ProgressDialog.BUTTON_POSITIVE);
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
