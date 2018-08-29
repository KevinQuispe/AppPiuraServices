package com.piuraservices.piuraservices.views.activitiesadmin.adminepsgrau;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgrau;
import com.piuraservices.piuraservices.services.http;

import org.apache.http.Header;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EpsInfoTramitesActivity extends AppCompatActivity  {

    @BindView(R.id.et_nombretramiteeps) EditText nomtramite;
    @BindView(R.id.et_desctramiteeps) EditText desctramite;
    @BindView(R.id.btn_guardartramiteeps) Button btntramites;
    ProgressDialog progreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_informacion_tramites);
        getSupportActionBar().setTitle("Información de trámites");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        }

    @OnClick(R.id.btn_guardartramiteeps)
    public void registrarTemitesEpsgrau() {
       //declare variables
        String nomt,desct;
        nomt=nomtramite.getText().toString();
        desct=desctramite.getText().toString();
        dialog();
        InfoTramitesEpsgrau info=new InfoTramitesEpsgrau(1,nomt,desct);
        http.post(getApplicationContext(), "informacion/createInfoTramite", info, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progreso.dismiss();
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "error en conexion", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                progreso.dismiss();
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "Se registró con éxito", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void dialog() {
        //progreso = new ProgressDialog(EpsInfoReclamosActivity.this, ProgressDialog.THEME_HOLO_LIGHT);
        progreso = new ProgressDialog(EpsInfoTramitesActivity.this, ProgressDialog.BUTTON_POSITIVE);
        // set indeterminate style
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // set title and message
        progreso.setTitle("Procesando");
        progreso.setMessage("Loading...");
        // and show it
        progreso.show();
        progreso.setCancelable(false);
    }
}
