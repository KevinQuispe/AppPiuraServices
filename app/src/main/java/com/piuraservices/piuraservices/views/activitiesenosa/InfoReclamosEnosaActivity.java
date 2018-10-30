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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoReclamosEnosaAdapter;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoReclamosepsAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.services.enosa.ListaReclamosEnosaclient;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesepsgrau.DetallereclamosEpsActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoReclamosEpsActivity;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoReclamosEnosaActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener  {

    //prograso loading
    ProgressDialog progreso;
    //list view de reclamos
    ListView listareclamos;
    //lista data del modelo de reclamos
    List<InfoReclamosEnosamodel> list_reclamos;
    //Array list for to http
    ArrayList<InfoReclamosEnosamodel> lista = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_reclamos_enosa);
        getSupportActionBar().setTitle("Información de Reclamos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //String[] informacion = {"Información Referencial", "Información de Reclamos", "Información de Trámites"};
        //Inflater inflater;
        //adapter = new ArrayAdapter<String>(InfoReclamosEnosaActivity.this, android.R.layout.simple_list_item_1, informacion);
        //listaelementos.setAdapter(adapter);
        listareclamos=(ListView) findViewById(R.id.list_reclamosenosa);
        listareclamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(InfoReclamosEnosaActivity.this, "Click me reclamos"+i, Toast.LENGTH_SHORT).show();
                final int pos = i;
                Intent intent=new Intent(InfoReclamosEnosaActivity.this, DetalleReclamosEnosaActivity.class);
                startActivity(intent);
                editarDetalle(list_reclamos.get(pos));
            }
        });
        lsitarReclamos();
    }
    //lista reclamos con http
    public void lsitarReclamos(){
        dialog();
        String url="informacion/listainforeclamos/2";
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
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoReclamosEnosamodel>>(){}.getType());
                    listareclamos.setAdapter(new ListaInfoReclamosEnosaAdapter(getApplicationContext(),lista));
                    listareclamos.setOnItemClickListener(InfoReclamosEnosaActivity.this);

                    listareclamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
    public void editarDetalle(final InfoReclamosEnosamodel post){
        Bundle bundle=new Bundle();
        bundle.putSerializable("Post",post);
        bundle.putString("nombreKey",post.getNombre().toString());
        bundle.putString("descripcionKey",post.getDescripcion().toString());
        //capturar datos
        Intent intent=new Intent(InfoReclamosEnosaActivity.this, DetalleReclamosEnosaActivity.class);
        Bundle parametros = new Bundle();
        String nombrereclamo = post.getNombre().toString();
        String descripcionreclamo = post.getDescripcion().toString();
        parametros.putString("descripcionKey",descripcionreclamo);
        intent.putExtras(parametros);
        startActivity(intent);
    }
    //metodo para listar informacion de reclamos enosa con retrofit
    public void listaReclamosEnosa(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        ListaReclamosEnosaclient client = retrofit.create(ListaReclamosEnosaclient.class); //here get la interface
        Call<List<InfoReclamosEnosamodel>> call = client.getInfoReclamosenosa(2);//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoReclamosEnosamodel>>() {
            @Override
            public void onResponse(Call<List<InfoReclamosEnosamodel>> call, Response<List<InfoReclamosEnosamodel>> response) {
                List<InfoReclamosEnosamodel> model=response.body();
                //listareclamos.setAdapter(new ListaInfoReclamosEnosaAdapter(InfoReclamosEnosaActivity.this, model));
                progreso.dismiss();
            }
            @Override
            public void onFailure(Call<List<InfoReclamosEnosamodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoReclamosEnosaActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //filtro para realizar las busquedas
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

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
