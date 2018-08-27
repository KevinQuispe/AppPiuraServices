package com.piuraservices.piuraservices.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loopj.android.http.TextHttpResponseHandler;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.Usuario;
import com.piuraservices.piuraservices.services.http;

import org.apache.http.Header;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterUserActivity extends AppCompatActivity {

    //declare variables
    @BindView(R.id.et_nombres) AutoCompleteTextView nombres;
    @BindView(R.id.et_apellidos) AutoCompleteTextView apellidos;
    @BindView(R.id.et_telefono) AutoCompleteTextView telefono;
    @BindView(R.id.et_edad) AutoCompleteTextView edad;
    @BindView(R.id.radiogroup) RadioGroup sexo;
    @BindView(R.id.et_email) AutoCompleteTextView correo;
    @BindView(R.id.et_password) AutoCompleteTextView password;
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        getSupportActionBar().setTitle("Registrarse");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        btnregister = (Button) findViewById(R.id.btn_register_user);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
                //startActivity(intent);
                //finish();
                RegitrarUsuario();

            }
        });
    }

    public void RegitrarUsuario() {
        String nom, app, dire, telf, sex, age, email, pass;
        nom = nombres.getText().toString();
        app = apellidos.getText().toString();
        dire = "lima";
        telf = telefono.getText().toString();
        age = edad.getText().toString();
        //int intedad = Integer.parseInt(age);
        email = correo.getText().toString();
        pass = password.getText().toString();
        sex = "M";
            String url = "usuario/createUsuario";
            Usuario user = new Usuario(1,nom, app, dire, telf, age, sex, email, pass);
            http.post(getApplication(), url, user, new TextHttpResponseHandler() {
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

