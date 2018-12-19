package com.piuraservices.piuraservices.views.activitiesepsgrau;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoContactosEpsgrauAdapter;
import com.piuraservices.piuraservices.models.epsgrau.InfoContactosEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;
import com.piuraservices.piuraservices.services.http;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class InfoContactosEpsgrauActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener   {

    ArrayAdapter<String> adapter;
    //list view para tramites
    ListView listViewTramites;
    //dialog
    ProgressDialog progreso;
    //lista tramites
    List<InfoContactosEpsgraumodel> list_tramites;
    //array list for to http
    ArrayList<InfoContactosEpsgraumodel> lista = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contactos_epsgrau);
        getSupportActionBar().setTitle("Información de Contactos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listViewTramites = (ListView) findViewById(R.id.id_lista_contactos_eps);
        listViewTramites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                Intent intent=new Intent(InfoContactosEpsgrauActivity.this, DetalleContactoEpsActivity.class);
                startActivity(intent);
                mostrarDetalle(list_tramites.get(pos));
            }
        });
        listarCotactosEps();

    }
    public void listarCotactosEps()
    {
        dialog();
        String url="informacion/listacontactos/1";
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
                    //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoContactosEpsgraumodel>>(){}.getType());
                    listViewTramites.setAdapter(new ListaInfoContactosEpsgrauAdapter(getApplicationContext(),lista));
                    listViewTramites.setOnItemClickListener(InfoContactosEpsgrauActivity.this);
                    listViewTramites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mostrarDetalle(lista.get(i)); //call data detalle
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
    public void mostrarDetalle(final InfoContactosEpsgraumodel tramite){
        //capturar datos
        Bundle bundle=new Bundle();
        bundle.putSerializable("Tramite",tramite);
        bundle.putString("nombreKey",tramite.getNombreempresa().toString());
        bundle.putString("direccionKey",tramite.getDireccion().toString());
        bundle.putString("telefonoKey",tramite.getDireccion().toString());
        bundle.putString("horarioKey",tramite.getDireccion().toString());
        bundle.putString("tipoatencionKey",tramite.getDireccion().toString());
        Intent intent=new Intent(InfoContactosEpsgrauActivity.this, DetalleContactoEpsActivity.class);
        Bundle parametros = new Bundle();

        String nombre = tramite.getNombreempresa().toString();
        String direccion = tramite.getDireccion().toString();
        String telefono = tramite.getTelefono().toString();
        String horario = tramite.getHorario().toString();
        String tipoatencion = tramite.getTipoatencion().toString();
        parametros.putString("nombreKey",nombre);
        parametros.putString("direccionKey",direccion);
        parametros.putString("telefonoKey",telefono);
        parametros.putString("horarioKey",horario);
        parametros.putString("tipoatencionKey",tipoatencion);
        intent.putExtras(parametros);
        startActivity(intent);

    }
    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoContactosEpsgrauActivity.this, ProgressDialog.BUTTON_POSITIVE);
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
