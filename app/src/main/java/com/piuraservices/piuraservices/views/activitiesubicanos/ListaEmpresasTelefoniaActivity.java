package com.piuraservices.piuraservices.views.activitiesubicanos;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.UbicanosActivity;

public class ListaEmpresasTelefoniaActivity extends AppCompatActivity {

    RadioGroup radioentidad;
    RadioButton rbmovistar;
    RadioButton rbclaro;
    RadioButton rbentel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_empresas_telefonia);
        getSupportActionBar().setTitle("Empresas de telefonía");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        radioentidad = (RadioGroup) findViewById(R.id.radio_group_entidades_maps);
        rbmovistar = (RadioButton) findViewById(R.id.rb_empresa_movistar_mapa);
        rbclaro = (RadioButton) findViewById(R.id.rb_empresa_claro_mapa);
        rbentel = (RadioButton) findViewById(R.id.rb_empresa_entel_mapa);
        elijaentidad();

    }

    public void elijaentidad() {
        radioentidad.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_empresa_movistar_mapa:
                        Intent intent = new Intent(ListaEmpresasTelefoniaActivity.this, UbicanosActivity.class);
                        Bundle parametros = new Bundle();
                        // String entidad = rbepsgrau.getText().toString();
                        String entidad = "movistar";
                        parametros.putString("entidadKey", entidad);
                        intent.putExtras(parametros);
                        startActivity(intent);
                        break;

                    case R.id.rb_empresa_claro_mapa:
                        Intent intentenosa = new Intent(ListaEmpresasTelefoniaActivity.this, UbicanosActivity.class);
                        Bundle parametrosenosa = new Bundle();
                        String entidadenosa = "claro";
                        parametrosenosa.putString("entidadKey", entidadenosa);
                        intentenosa.putExtras(parametrosenosa);
                        startActivity(intentenosa);
                        break;

                    case R.id.rb_empresa_entel_mapa:
                        Intent intententel = new Intent(ListaEmpresasTelefoniaActivity.this, UbicanosActivity.class);
                        Bundle parametrosentel = new Bundle();
                        String entidadentel = "entel";
                        parametrosentel.putString("entidadKey", entidadentel);
                        intententel.putExtras(parametrosentel);
                        startActivity(intententel);
                        break;
                    default:

                }

            }
        });
    }
}
