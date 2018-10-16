package com.piuraservices.piuraservices.views.activitiestelefonia.movistar;

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
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoTramitesEnosaAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.movistar.ListaInfoReclamosMovistarAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.movistar.ListaInfoTramitesMovistarAdapter;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoReclamosMovistarmodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoTramitesMovistarmodel;
import com.piuraservices.piuraservices.services.telefonia.ListaReclamosMovistarclient;
import com.piuraservices.piuraservices.services.telefonia.ListaTramitesMovistarclient;
import com.piuraservices.piuraservices.utils.Config;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoTramitesMovistarActivity extends AppCompatActivity {

    //DECLARACION DE VARIABLES
    ListView listatramites;
    //variable para loading
    ProgressDialog progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tramites_movistar);
        getSupportActionBar().setTitle("Infomación Tramites Movistar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listatramites=(ListView) findViewById(R.id.list_tramites_movistar);
        listarTramitesMovistar();
    }

    public void listarTramitesMovistar() {
        final String url = Config.URL_SERVER;
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //variable
        ListaTramitesMovistarclient client = retrofit.create(ListaTramitesMovistarclient.class); //here get la interface

        Call<List<InfoTramitesMovistarmodel>> call = client.getInfoTramitesmovistar(3);//here el model
        //loading
        dialog();
        call.enqueue(new Callback<List<InfoTramitesMovistarmodel>>() {
            @Override
            public void onResponse(Call<List<InfoTramitesMovistarmodel>> call, Response<List<InfoTramitesMovistarmodel>> response) {

                if (response.isSuccessful()) {

                    List<InfoTramitesMovistarmodel> model = response.body();

                    listatramites.setAdapter(new ListaInfoTramitesMovistarAdapter(InfoTramitesMovistarActivity.this, model));

                    Log.i("post submitted to API.", response.body().toString());

                    progreso.dismiss();

                } else {

                    warningmessage();
                }
            }
            @Override
            public void onFailure(Call<List<InfoTramitesMovistarmodel>> call, Throwable t) {
                progreso.dismiss();
                Toast.makeText(InfoTramitesMovistarActivity.this, "Error de conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) item.getActionView();
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
        progreso = new ProgressDialog(InfoTramitesMovistarActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }

    public void warningmessage() {
        final AlertDialog.Builder alertaDeError2 = new AlertDialog.Builder(InfoTramitesMovistarActivity.this);
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
