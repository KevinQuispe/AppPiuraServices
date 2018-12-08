package com.piuraservices.piuraservices.views.activitiestelefonia.entel;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.telefonia.entel.ListaInfoReclamosEntelAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.entel.ListaInfoTramitesEntelAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoReclamosEntelmodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoTramitesEntelmodel;
import com.piuraservices.piuraservices.services.entel.ListaReclamosEntelclient;
import com.piuraservices.piuraservices.services.entel.ListaTramitesEntelclient;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiestelefonia.claro.InfoTramitesClaroActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.DetalleReclamosMovistarActivity;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTramitesEntelActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    //DECLARACION DE VARIABLES
    ListView listartramites;
    //variable para loading
    ProgressDialog progreso;
    ArrayList<InfoTramitesEntelmodel> lista=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tramites_entel);
        getSupportActionBar().setTitle("Infomación trámites Entel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listartramites=(ListView) findViewById(R.id.list_tramites_entel);
        listarTramitesEntel();
    }

    public void listarTramitesEntel(){

        dialog();
        String url="informacion/listainfotramites/5";
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
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoTramitesEntelmodel>>(){}.getType());
                    listartramites.setAdapter(new ListaInfoTramitesEntelAdapter(getApplicationContext(),lista));
                    listartramites.setOnItemClickListener(InfoTramitesEntelActivity.this);

                    listartramites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public void editarDetalle(final InfoTramitesEntelmodel post){
        Bundle bundle=new Bundle();
        bundle.putSerializable("Post",post);
        bundle.putString("nombreKey",post.getNombre().toString());
        bundle.putString("descripcionKey",post.getDescripcion().toString());
        //capturar datos
        Intent intent=new Intent(InfoTramitesEntelActivity.this, DetalleTramitesEntelActivity.class);
        Bundle parametros = new Bundle();
        String nombrereclamo = post.getNombre().toString();
        String descripcionreclamo = post.getDescripcion().toString();
        parametros.putString("descripcionKey",descripcionreclamo);
        intent.putExtras(parametros);
        startActivity(intent);
    }
    public void listarTramites(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //variable
        ListaTramitesEntelclient client = retrofit.create(ListaTramitesEntelclient.class); //here get la interface

        Call<List<InfoTramitesEntelmodel>> call = client.getInfoTramitesentel(5);//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoTramitesEntelmodel>>() {
            @Override
            public void onResponse(Call<List<InfoTramitesEntelmodel>> call, Response<List<InfoTramitesEntelmodel>> response) {
                if(response.isSuccessful()) {
                    List<InfoTramitesEntelmodel> model=response.body();
                    //listartramites.setAdapter(new ListaInfoTramitesEntelAdapter(InfoTramitesEntelActivity.this,model));
                    Log.i("post submitted to API.",response.body().toString());
                    progreso.dismiss();
                }
                else{
                    warningmessage();
                }
            }
            @Override
            public void onFailure(Call<List<InfoTramitesEntelmodel>> call, Throwable t) {
                progreso.dismiss();
                warningmessage();
                Toast.makeText(InfoTramitesEntelActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
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
        progreso = new ProgressDialog(InfoTramitesEntelActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        //progreso.setCancelable(false);
    }
    public void warningmessage(){
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoTramitesEntelActivity.this);
        alertaDeError2.setTitle("Advertencia");
        alertaDeError2.setMessage("Recargar informacion");
        alertaDeError2.setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertaDeError2.create();
        alertaDeError2.show();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}

