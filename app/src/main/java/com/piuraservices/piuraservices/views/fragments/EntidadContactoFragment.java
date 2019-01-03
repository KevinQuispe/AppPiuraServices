package com.piuraservices.piuraservices.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoContactosEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoTramitesEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoContactosEpsgrauActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.claro.InfoContactosClaroActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.claro.InfoTramitesClaroActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.entel.InfoContactosEntelActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.entel.InfoTramitesEntelActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.InfoContactosMovistarActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.InfoTramitesMovistarActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntidadContactoFragment extends Fragment {

    //declare variaviables
    RadioGroup grupo;
    RadioButton rbmovistar, rbepsgrau, rbenosa, rbclaro, rbentel;
    Button buttonnext;


    public EntidadContactoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_entidad_contacto, container, false);
        //vindear las vistas
        rbepsgrau = (RadioButton) view.findViewById(R.id.rb_entidad_eps_contacto);
        rbenosa = (RadioButton) view.findViewById(R.id.rb_entidad_enosa_contacto);
        rbmovistar = (RadioButton) view.findViewById(R.id.rb_empresa_movistar_contacto);
        rbclaro = (RadioButton) view.findViewById(R.id.rb_empresa_claro_contacto);
        rbentel = (RadioButton) view.findViewById(R.id.rb_empresa_entel_contacto);
        buttonnext = (Button) view.findViewById(R.id.btn_entidad_siguiente_contacto);
        seleccioneEntidad();
        return view;
    }

    public void seleccioneEntidad() {
        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rbepsgrau.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoContactosEpsgrauActivity.class);
                    startActivity(intent);
                }
                if (rbenosa.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoContactosEnosaActivity.class);
                    startActivity(intent);
                }
                if (rbmovistar.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoContactosMovistarActivity.class);
                    startActivity(intent);
                }
                if (rbclaro.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoContactosClaroActivity.class);
                    startActivity(intent);
                }
                if (rbentel.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoContactosEntelActivity.class);
                    startActivity(intent);
                }
                else if (!rbentel.isChecked() && !rbclaro.isChecked() && !rbmovistar.isChecked() && !rbenosa.isChecked() && !rbepsgrau.isChecked()) {
                    Toast.makeText(getActivity(), "Seleccione entidad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}