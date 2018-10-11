package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoReclamosEnosaAdapter;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoReclamosepsAdapter;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoTramitesepsAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;
import com.piuraservices.piuraservices.services.epsgrau.ListaReclamosEpsclient;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activities.ContactoDetalleActivity;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoReclamosEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoTramitesEnosaActivity;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoReclamosEpsActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    //variables retrofit
    ListView listareclamos;
    //variable para loading
    ProgressDialog progreso, progressDialog;
    List<InfoReclamosEpsgraumodel> list_reclamos;
    //varaibles para listar listview

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

        listareclamos = (ListView) findViewById(R.id.list_reclamoseps);
        listareclamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
               // Intent intent=new Intent(InfoReclamosEpsActivity.this, DetallereclamosEpsActivity.class);
                //startActivity(intent);
                editarDetalle(list_reclamos.get(pos));
            }
        });
        listaReclamosEPS();

    }
    //mostrardetalle lista
    public void editarDetalle(final InfoReclamosEpsgraumodel post){
        Bundle bundle=new Bundle();
        bundle.putSerializable("Post",post);
        bundle.putString("nombreKey",post.getNombre().toString());
        bundle.putString("descripcionKey",post.getDescripcion().toString());
        Intent intent=new Intent(InfoReclamosEpsActivity.this, DetallereclamosEpsActivity.class);
        startActivity(intent);
          //call methods
    }
    public  void listaReclamosEPS(){
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //variable
        ListaReclamosEpsclient client = retrofit.create(ListaReclamosEpsclient.class); //here get la interface

        Call<List<InfoReclamosEpsgraumodel>> call = client.getInfoReclamoseps();//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoReclamosEpsgraumodel>>() {
            @Override
            public void onResponse(Call<List<InfoReclamosEpsgraumodel>> call, Response<List<InfoReclamosEpsgraumodel>> response) {
                 if(response.isSuccessful()) {
                    //showResponse(response.body().toString());
                     List<InfoReclamosEpsgraumodel> model=response.body();
                     listareclamos.setAdapter(new ListaInfoReclamosepsAdapter(InfoReclamosEpsActivity.this,model));
                     Log.i("post submitted to API.",response.body().toString());
                     progreso.dismiss();
                }
                else{
                     warningmessage();
                 }
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
    public void warningmessage(){
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoReclamosEpsActivity.this);
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
