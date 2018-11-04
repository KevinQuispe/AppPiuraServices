package com.piuraservices.piuraservices.views.activitiesenosa;

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
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoTramitesEnosaAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;
import com.piuraservices.piuraservices.services.enosa.ListaTramitesEnosaclient;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesepsgrau.DetalleTramitesEpsgrauActivity;

import org.apache.http.Header;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTramitesEnosaActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener  {

    ListView listaelementos;
    ArrayAdapter<InfoTramitesEnosamodel> adapter;
    //lista reclamos
    ProgressDialog progreso;
    // ListView listatramites;
    ListView listatramites;
    List<InfoTramitesEnosamodel> lista_tramites;;
    //array for to http
    ArrayList<InfoTramitesEnosamodel> lista=new ArrayList<>();

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
                //Toast.makeText(InfoTramitesEnosaActivity.this, "Click tramite "+i, Toast.LENGTH_SHORT).show();
                final int pos = i;
                Intent intent=new Intent(InfoTramitesEnosaActivity.this, DetalleTramitesEpsgrauActivity.class);
                startActivity(intent);
                editarDetalle(lista_tramites.get(pos));

            }
        });
        listarTramitesEnosa();
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
    //listar ramites con http
    public void listarTramitesEnosa(){
        dialog();
        String url="informacion/listainfotramites/2";
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
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoTramitesEnosamodel>>(){}.getType());
                    listatramites.setAdapter(new ListaInfoTramitesEnosaAdapter(getApplicationContext(),lista));
                    listatramites.setOnItemClickListener(InfoTramitesEnosaActivity.this);

                    listatramites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //Toast.makeText(getApplicationContext(), "item " +i, Toast.LENGTH_SHORT).show();
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
    //mostrardetalle lista
    public void editarDetalle(final InfoTramitesEnosamodel post){
        Bundle bundle=new Bundle();
        bundle.putSerializable("Post",post);
        bundle.putString("nombreKey",post.getNombre().toString());
        bundle.putString("descripcionKey",post.getDescripcion().toString());
        //capturar datos
        Intent intent=new Intent(InfoTramitesEnosaActivity.this, DetalleTramitesEpsgrauActivity.class);
        Bundle parametros = new Bundle();
        String nombrereclamo = post.getNombre().toString();
        String descripcionreclamo = post.getDescripcion().toString();
        parametros.putString("descripcionKey",descripcionreclamo);
        intent.putExtras(parametros);
        startActivity(intent);
    }
    //metodo para listar informacion de reclamos enosa
    public void listaTramitesEnosa(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaTramitesEnosaclient client = retrofit.create(ListaTramitesEnosaclient.class); //here get la interface
        Call<List<InfoTramitesEnosamodel>> call = client.getInfoTramitesenosa(2);//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoTramitesEnosamodel>>() {
            @Override
            public void onResponse(Call<List<InfoTramitesEnosamodel>> call, Response<List<InfoTramitesEnosamodel>> response) {
               try {
                   List<InfoTramitesEnosamodel> model=response.body();
                   //listatramites.setAdapter(new ListaInfoTramitesEnosaAdapter(InfoTramitesEnosaActivity.this, model));
                   progreso.dismiss();
               }
               catch(Exception e){
                   e.printStackTrace();
               }

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

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

