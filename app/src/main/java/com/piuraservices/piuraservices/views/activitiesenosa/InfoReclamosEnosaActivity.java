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
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoReclamosepsAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.services.enosa.ListaReclamosEnosaclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoReclamosEpsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoReclamosEnosaActivity extends AppCompatActivity {

    ListView listaelementos;
    ArrayAdapter<String> adapter;
    //prograso
    ProgressDialog progreso;
    ListView listareclamos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reclamos_enosa);
        getSupportActionBar().setTitle("Información de Reclamos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //String[] informacion = {"Información Referencial", "Información de Reclamos", "Información de Trámites"};
        //Inflater inflater;
        listareclamos=(ListView) findViewById(R.id.list_reclamosenosa);
        //adapter = new ArrayAdapter<String>(InfoReclamosEnosaActivity.this, android.R.layout.simple_list_item_1, informacion);
        //listaelementos.setAdapter(adapter);

        listareclamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(InfoReclamosEnosaActivity.this, "Click me", Toast.LENGTH_SHORT).show();

            }
        });
        listaReclamosEnosa();

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
                //adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    //metodo para listar informacion de reclamos enosa
    public void listaReclamosEnosa(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaReclamosEnosaclient client = retrofit.create(ListaReclamosEnosaclient.class); //here get la interface
        Call<List<InfoReclamosEnosamodel>> call = client.getInfoReclamosenosa();//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoReclamosEnosamodel>>() {
            @Override
            public void onResponse(Call<List<InfoReclamosEnosamodel>> call, Response<List<InfoReclamosEnosamodel>> response) {
                List<InfoReclamosEnosamodel> model=response.body();
                listareclamos.setAdapter(new ListaInfoReclamosEnosaAdapter(InfoReclamosEnosaActivity.this, model));
                progreso.dismiss();
            }
            @Override
            public void onFailure(Call<List<InfoReclamosEnosamodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoReclamosEnosaActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoReclamosEnosaActivity.this, ProgressDialog.BUTTON_POSITIVE);
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
