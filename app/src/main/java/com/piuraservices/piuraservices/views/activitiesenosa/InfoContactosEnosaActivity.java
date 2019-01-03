package com.piuraservices.piuraservices.views.activitiesenosa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoContactosEnosaAdapter;
import com.piuraservices.piuraservices.adapters.epsgrau.ListaInfoContactosEpsgrauAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoContactosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoReferencialEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoContactosEpsgraumodel;
import com.piuraservices.piuraservices.services.enosa.ListaReferencialEnosaclient;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.utils.Config;
import com.piuraservices.piuraservices.views.activitiesepsgrau.DetalleContactoEpsActivity;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoContactosEnosaActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    //declare variables
    //prograso loading
    ProgressDialog progreso;
    //list view de reclamos
    ListView listViewContactos;
    //lista data del modelo de reclamos
    List<InfoContactosEnosamodel> list_contactos;
    //Array list for to http and to converter to gson
    ArrayList<InfoContactosEnosamodel> lista = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contactos_enosa);
        getSupportActionBar().setTitle("Información de Contactos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listViewContactos = (ListView) findViewById(R.id.id_lista_contactos_enosa);
        listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                Intent intent = new Intent(InfoContactosEnosaActivity.this, DetalleContactoEnosaActivity.class);
                startActivity(intent);
                mostrarDetalle(list_contactos.get(pos));
            }
        });
        listarContactosEnosa();
    }

    //lista contactos enosa con http
    public void listarContactosEnosa() {
        dialog();
        String url = "informacion/listacontactos/2";
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
                    lista = new Gson().fromJson(responseString, new TypeToken<ArrayList<InfoContactosEnosamodel>>() {
                    }.getType());
                    listViewContactos.setAdapter(new ListaInfoContactosEnosaAdapter(getApplicationContext(), lista));
                    listViewContactos.setOnItemClickListener(InfoContactosEnosaActivity.this);
                    listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mostrarDetalle(lista.get(i));
                        }
                    });
                    progreso.hide();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    //mostrardetalle lista
    public void mostrarDetalle(final InfoContactosEnosamodel contactoEnosa) {
        //capturar datos
        Bundle bundle = new Bundle();
        bundle.putSerializable("contactoEnosa", contactoEnosa);
        bundle.putString("nombreKey", contactoEnosa.getNombreempresa().toString());
        bundle.putString("direccionKey", contactoEnosa.getDireccion().toString());
        bundle.putString("telefonoKey", contactoEnosa.getTelefono().toString());
        bundle.putString("horarioKey", contactoEnosa.getHorario().toString());
        bundle.putString("tiposervicioKey", contactoEnosa.getTipoatencion().toString());
        Intent intent = new Intent(InfoContactosEnosaActivity.this, DetalleContactoEnosaActivity.class);
        Bundle parametros = new Bundle();
        String center = contactoEnosa.getNombreempresa().toString();
        String diretion = contactoEnosa.getDireccion().toString();
        String phone = contactoEnosa.getTelefono().toString();
        String horarioatencion = contactoEnosa.getHorario().toString();
        String type = contactoEnosa.getTipoatencion().toString();
        parametros.putString("nombreKey", center);
        parametros.putString("direccionKey", diretion);
        parametros.putString("telefonoKey", phone);
        parametros.putString("horarioKey", horarioatencion);
        parametros.putString("tiposervicioKey", type);
        intent.putExtras(parametros);
        startActivity(intent);
    }

    //progres dialog
    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoContactosEnosaActivity.this, ProgressDialog.BUTTON_POSITIVE);
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
