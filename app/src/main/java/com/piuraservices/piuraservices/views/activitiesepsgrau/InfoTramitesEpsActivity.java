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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoTramitesepsAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;
import com.piuraservices.piuraservices.services.epsgrau.ListaTramitesEpsclient;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.utils.Config;

import org.apache.http.Header;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTramitesEpsActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener  {

    ArrayAdapter<String> adapter;
    //list view para tramites
    ListView listViewTramites;
    //dialog
    ProgressDialog progreso;
    //lista tramites
    List<InfoTramitesEpsgraumodel> list_tramites;
    //array list for to http
    ArrayList<InfoTramitesEpsgraumodel> lista = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tramites_eps);
        getSupportActionBar().setTitle("Información de Tramites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //String[] informacion = {"Información Referencial", "Información de Reclamos", "Información de Trámites"};
        //Inflater inflater;
        //listaelementos=(ListView) findViewById(R.id.list_tramiteseps);
        //adapter = new ArrayAdapter<String>(InfoTramitesEpsActivity.this, android.R.layout.simple_list_item_1, informacion);
        //listaelementos.setAdapter(adapter);

        listViewTramites = (ListView) findViewById(R.id.list_tramiteseps);
        listViewTramites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                Intent intent=new Intent(InfoTramitesEpsActivity.this, DetallereclamosEpsActivity.class);
                startActivity(intent);
                editarDetalle(list_tramites.get(pos));
            }
        });
        listarTramitesEps();
    }
    public void listarTramitesEps()
    {
        dialog();
        String url="informacion/listainfotramites/1";
        http.get(getApplicationContext(), url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
                progreso.hide();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                try {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoTramitesEpsgraumodel>>(){}.getType());
                    listViewTramites.setAdapter(new ListaInfoTramitesepsAdapter(getApplicationContext(),lista));
                    listViewTramites.setOnItemClickListener(InfoTramitesEpsActivity.this);
                    listViewTramites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            editarDetalle(lista.get(i)); //call data detalle
                        }
                    });
                    progreso.hide();
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });
    }
    public void editarDetalle(final InfoTramitesEpsgraumodel post){
        //capturar datos
        Intent intent=new Intent(InfoTramitesEpsActivity.this, DetalleTramitesEpsgrauActivity.class);
        Bundle parametros = new Bundle();
        String nombretramite = post.getNombre().toString();
        String descripciontramite = post.getDescripcion().toString();
        parametros.putString("descripcionKey",descripciontramite);
        intent.putExtras(parametros);
        startActivity(intent);
        //call methods
    }
    //listar tramites
    public  void listatramitesEPS(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaTramitesEpsclient client = retrofit.create(ListaTramitesEpsclient.class); //here get la interface
        Call<List<InfoTramitesEpsgraumodel>> call = client.getInfoTramiteseps(1);
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoTramitesEpsgraumodel>>() {
            @Override
            public void onResponse(Call<List<InfoTramitesEpsgraumodel>> call, Response<List<InfoTramitesEpsgraumodel>> response) {
                List<InfoTramitesEpsgraumodel> model=response.body();
                //listatramites.setAdapter(new ListaInfoTramitesepsAdapter(InfoTramitesEpsActivity.this,model));
                progreso.dismiss();
            }
            @Override
            public void onFailure(Call<List<InfoTramitesEpsgraumodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoTramitesEpsActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
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
                //adapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoTramitesEpsActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }

    //cargando
    public void cargando(){
        progreso = ProgressDialog.show(this, "Procesando",
                "Loading...", true);

        new Thread(new Runnable() {
            @Override
            public void run()
            {
                // do the thing that takes a long time

                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        progreso.dismiss();
                    }
                });
            }
        }).start();
    }
    //new with timer
    public void launchRingDialog(View view) {
        final ProgressDialog ringProgressDialog =
                ProgressDialog.show(InfoTramitesEpsActivity.this, "Please wait ...", "Loading...", true);
        ringProgressDialog.setCancelable(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Here you should write your time consuming task...
                    // Let the progress ring for 10 seconds...
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ringProgressDialog.dismiss();
            }
        }).start();
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onClick(View view) {

    }
}

