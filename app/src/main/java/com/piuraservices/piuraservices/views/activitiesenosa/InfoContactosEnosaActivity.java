package com.piuraservices.piuraservices.views.activitiesenosa;

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
import com.piuraservices.piuraservices.adapters.enosa.ListaInfoReclamosEnosaAdapter;
import com.piuraservices.piuraservices.models.enosa.InfoContactosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.services.http;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class InfoContactosEnosaActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {

    //declare variables
    //prograso loading
    ProgressDialog progreso;
    //list view de reclamos
    ListView listareclamos;
    //lista data del modelo de reclamos
    List<InfoReclamosEnosamodel> list_reclamos;
    //Array list for to http and to converter to gson
    ArrayList<InfoContactosEnosamodel> lista = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_contactos_enosa);
        getSupportActionBar().setTitle("Información de Contactos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    //lista contactos enosa con http
    public void listarReclamosEnosa(){
        dialog();
        String url="informacion/listainfocontactos/2";
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
                    lista=new Gson().fromJson(responseString,new TypeToken<ArrayList<InfoReclamosEnosamodel>>(){}.getType());
                    listareclamos.setAdapter(new ListaInfoContactosEnosaAdapter(getApplicationContext(),lista));
                    listareclamos.setOnItemClickListener(InfoContactosEnosaActivity.this);

                    listareclamos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            //Toast.makeText(getApplicationContext(), "item " +i, Toast.LENGTH_SHORT).show();
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
    public void mostrarDetalle(final InfoContactosEnosamodel post){
        Bundle bundle=new Bundle();
        bundle.putSerializable("Post",post);
        bundle.putString("oficinaKey",post.getOficinalugar().toString());
        bundle.putString("direccionKey",post.getDireccion().toString());
        //capturar datos
        Intent intent=new Intent(InfoContactosEnosaActivity.this, DetalleContactoEnosaActivity.class);
        Bundle parametros = new Bundle();
        String nombreoficina = post.getOficinalugar().toString();
        String direccionoficina = post.getDireccion().toString();
        parametros.putString("oficinaKey",nombreoficina);
        parametros.putString("direccionKey",direccionoficina);
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
