package com.piuraservices.piuraservices.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.Usuario;
import com.piuraservices.piuraservices.services.http;

import org.apache.http.Header;
import org.apache.http.HttpClientConnection;

public class RegisterUserActivity extends AppCompatActivity {
    //declare variables
    EditText nombres, apellidos, telefono, edad, correo, password;
    RadioGroup radioGroupsex;
    RadioButton sexom;
    Spinner direccion;
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        getSupportActionBar().setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nombres = (EditText) findViewById(R.id.et_nombresuser);
        apellidos = (EditText) findViewById(R.id.et_apellidosuser);
        telefono = (EditText) findViewById(R.id.et_telefonouser);
        edad = (EditText) findViewById(R.id.et_edaduser);
        correo = (EditText) findViewById(R.id.et_emailuser);
        password = (EditText) findViewById(R.id.et_passworduser);
        btnregister = (Button) findViewById(R.id.btn_register_user);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
    }

    public void registrarUsuario() {
        String nom, app, tel, email, clave, dire,age,sex;
        nom = nombres.getText().toString();
        app = nombres.getText().toString();
        tel = nombres.getText().toString();
        email = nombres.getText().toString();
        clave = password.getText().toString();
        age=edad.getText().toString();
        dire = "Lima";
        sex="M";
        Usuario usuario = new Usuario(1, nom, app, dire, tel,age,sex,email, clave);
        http.post(getApplicationContext(), "usuario/createUsuario", usuario, new TextHttpResponseHandler() {
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

