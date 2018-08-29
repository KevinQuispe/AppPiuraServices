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
public class RegisterUserActivity extends AppCompatActivity {

    //declare variables
     EditText nombres,apellidos,telefono,edad,correo,password;
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
        nombres=(EditText) findViewById(R.id.et_nombresuser);
        apellidos=(EditText) findViewById(R.id.et_apellidosuser);
        telefono=(EditText) findViewById(R.id.et_telefonouser);
        edad=(EditText) findViewById(R.id.et_edaduser);
        correo=(EditText) findViewById(R.id.et_emailuser);
        password=(EditText) findViewById(R.id.et_passworduser);
        direccion=(Spinner) findViewById(R.id.spinnerdireccion);
        btnregister = (Button) findViewById(R.id.btn_register_user);
        radioGroupsex = (RadioGroup) findViewById(R.id.radiogroupsexo);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    RegitrarUsuario();
                    //Toast.makeText(getApplicationContext(), "Succes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RegitrarUsuario() {
        String nom, app, dire, telf, sex, age, email, pass;
        nom = nombres.getText().toString();
        app = apellidos.getText().toString();
        //dire = direccion.getSelectedItem().toString();
        telf = telefono.getText().toString();
        age = edad.getText().toString();
        email = correo.getText().toString();
        pass = password.getText().toString();
        sex ="m";
        dire="lima";

            Usuario user = new Usuario(1,nom, app, dire, telf, age, sex, email, pass);
            http.post(getApplicationContext(), "usuario/createUsuario", user, new TextHttpResponseHandler() {
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

