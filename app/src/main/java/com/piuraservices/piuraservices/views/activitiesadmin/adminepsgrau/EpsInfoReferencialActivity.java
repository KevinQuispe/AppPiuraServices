

package com.piuraservices.piuraservices.views.activitiesadmin.adminepsgrau;

import android.app.Service;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgrau;
import com.piuraservices.piuraservices.services.http;

import org.apache.http.Header;

public class EpsInfoReferencialActivity extends AppCompatActivity {
    //declaracion de varibales
    EditText nombre,direccion,telefono,correo,horario,web;
    Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_informacion_referencial);
        getSupportActionBar().setTitle("Información Referencial");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nombre=(EditText) findViewById(R.id.et_nombreentidad);
        direccion=(EditText) findViewById(R.id.et_direccionentidad);
        telefono=(EditText) findViewById(R.id.et_telefonoentidad);
        correo=(EditText) findViewById(R.id.et_correoentidad);
        horario=(EditText) findViewById(R.id.et_horarioentidad);
        web=(EditText) findViewById(R.id.et_webentidad);
        btnguardar=(Button) findViewById(R.id.btn_guardarentidad);
        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.btn_guardarentidad){
                    registrarInfoReferencial();
                    //Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void registrarInfoReferencial(){
        String nom,dir,email,tel,hor,webentidad;
        nom=nombre.getText().toString();
        dir=direccion.getText().toString();
        email=correo.getText().toString();
        tel=telefono.getText().toString();
        hor=horario.getText().toString();
        webentidad=web.getText().toString();
        String url="/informacion/createInfoEntidad";
        InfoReferencialEpsgrau info=new InfoReferencialEpsgrau(1,nom,dir,tel,email,hor,webentidad);
        http.post(getApplicationContext(), "informacion/createInfoEntidad", info, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "error en conexion", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                System.out.println(responseString);
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
