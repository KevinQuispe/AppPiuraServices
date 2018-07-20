package com.piuraservices.piuraservices.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.piuraservices.piuraservices.MainActivity;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.SplashScreenActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnlogin,btnregister;
    TextView forgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnlogin=(Button) findViewById(R.id.btn_login);
        btnregister=(Button) findViewById(R.id.btn_register);
        btnlogin.setOnClickListener(this);
        btnregister.setOnClickListener(this);
        forgot=(TextView) findViewById(R.id.idforgot);
        forgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_register:
                Intent register = new Intent(LoginActivity.this, RegisterUserActivity.class);
                startActivity(register);
                break;
            case R.id.idforgot:
                Intent forgot = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(forgot);
                break;
                default:
                    Toast.makeText(LoginActivity.this,"defaul",Toast.LENGTH_SHORT).show();
        }
    }
}
