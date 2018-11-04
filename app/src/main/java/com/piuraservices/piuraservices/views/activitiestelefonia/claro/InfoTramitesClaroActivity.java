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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoReclamosepsAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.claro.ListaInfoReclamosClaroAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.claro.ListaInfoTramitesClaroAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.entel.ListaInfoTramitesEntelAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.movistar.ListaInfoTramitesMovistarAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReclamosClaromodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoTramitesClaromodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoTramitesEntelmodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoTramitesMovistarmodel;
import com.piuraservices.piuraservices.services.claro.ListaReclamosClaroclient;
import com.piuraservices.piuraservices.services.claro.ListaTramitesClaroclient;
import com.piuraservices.piuraservices.services.entel.ListaTramitesEntelclient;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoTramitesEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.DetallereclamosEpsActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoReclamosEpsActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoTramitesEpsActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.entel.DetalleTramitesEntelActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.entel.InfoTramitesEntelActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.DetalleTramitesMovistarActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.InfoTramitesMovistarActivity;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTramitesClaroActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener  {

    ListView listView;
    ProgressDialog progreso;
    ListView listatramites;
    ArrayList<InfoTramitesClaromodel> lista=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tramites_claro);
        getSupportActionBar().setTitle("Infomación Trámites Claro");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listatramites=(ListView) findViewById(R.id.list_tramites_claro);
        listaTramitesClaro();
    }
    //listar tramites de claro
    public void listaTramitesClaro(){
        dialog();
        String url="informacion/listainfotramites/4";
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
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoTramitesClaromodel>>(){}.getType());
                    listatramites.setAdapter(new ListaInfoTramitesClaroAdapter(getApplicationContext(),lista));
                    listatramites.setOnItemClickListener(InfoTramitesClaroActivity.this);

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
    public void editarDetalle(final InfoTramitesClaromodel post){
        Bundle bundle=new Bundle();
        bundle.putSerializable("Post",post);
        bundle.putString("nombreKey",post.getNombre().toString());
        bundle.putString("descripcionKey",post.getDescripcion().toString());
        //capturar datos
        Intent intent=new Intent(InfoTramitesClaroActivity.this, DetalleTramitesClaroActivity.class);
        Bundle parametros = new Bundle();
        String nombrereclamo = post.getNombre().toString();
        String descripcionreclamo = post.getDescripcion().toString();
        parametros.putString("descripcionKey",descripcionreclamo);
        intent.putExtras(parametros);
        startActivity(intent);
    }
    //listar tramites con retrofit 2
    public  void listaTramites(){
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
                    //listatramites.setAdapter(new ListaInfoTramitesClaroAdapter(InfoTramitesClaroActivity.this,model));
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

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
