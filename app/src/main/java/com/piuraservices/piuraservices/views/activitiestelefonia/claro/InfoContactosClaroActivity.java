package com.piuraservices.piuraservices.views.activitiestelefonia.claro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoContactosEnosaAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.claro.ListaInfoContactosClaroAdapter;
import com.piuraservices.piuraservices.adapters.telefonia.movistar.ListaInfoContactosMovistarAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoContactosEnosamodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoContactosClaromodel;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoContactosMovistarmodel;
import com.piuraservices.piuraservices.services.http;
import com.piuraservices.piuraservices.views.activitiesenosa.DetalleContactoEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoContactosEnosaActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.DetalleContactoMovistarActivity;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class InfoContactosClaroActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    //declare variables
    //prograso loading
    ProgressDialog progreso;
    //list view de reclamos
    ListView listacontactos;
    //lista data del modelo de reclamos
    List<InfoContactosClaromodel> list_contactos;
    //Array list for to http and to converter to gson
    ArrayList<InfoContactosClaromodel> lista = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contactos_claro);
        getSupportActionBar().setTitle("Información de Contactos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listacontactos=(ListView) findViewById(R.id.id_lista_contactos_claro);

        listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                Intent intent=new Intent(InfoContactosClaroActivity.this, DetalleContactoClaroActivity.class);
                startActivity(intent);
                mostrarDetalle(list_contactos.get(pos));
            }
        });
        listarContactosClaro();

    }
    //lista contactos enosa con http
    public void listarContactosClaro(){
        dialog();
        String url="informacion/listacontactos/4";
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
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoContactosClaromodel>>(){}.getType());
                    listacontactos.setAdapter(new ListaInfoContactosClaroAdapter(getApplicationContext(),lista));
                    listacontactos.setOnItemClickListener(InfoContactosClaroActivity.this);
                    listacontactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            mostrarDetalle(lista.get(i));
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
    public void mostrarDetalle(final InfoContactosClaromodel contactoClaro){
        //capturar datos
        Bundle bundle=new Bundle();
        bundle.putSerializable("Contacto",contactoClaro);
        bundle.putString("centerKey",contactoClaro.getNombreempresa().toString());
        bundle.putString("direccionKey",contactoClaro.getDireccion().toString());
        bundle.putString("telefonoKey",contactoClaro.getTelefono().toString());
        bundle.putString("horarioKey",contactoClaro.getHorario().toString());
        bundle.putString("tiposervicioKey",contactoClaro.getTipoatencion().toString());
        Intent intent=new Intent(InfoContactosClaroActivity.this, DetalleContactoClaroActivity.class);
        Bundle parametros = new Bundle();
        String center = contactoClaro.getNombreempresa().toString();
        String diretion = contactoClaro.getDireccion().toString();
        String phone = contactoClaro.getTelefono().toString();
        String horarioatencion = contactoClaro.getHorario().toString();
        String type = contactoClaro.getTipoatencion().toString();
        parametros.putString("centerKey",center);
        parametros.putString("direccionKey",diretion);
        parametros.putString("telefonoKey",phone);
        parametros.putString("horarioKey",horarioatencion);
        parametros.putString("tiposervicioKey",type);
        intent.putExtras(parametros);
        startActivity(intent);
    }
    //progres dialog
    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(InfoContactosClaroActivity.this, ProgressDialog.BUTTON_POSITIVE);
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

