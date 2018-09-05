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

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoReclamosEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesenosa.InfoTramitesEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoReclamosEpsActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.claro.InfoTramitesClaroActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.InfoReclamosMovistarActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntidadTramiteFragment extends Fragment {


    RadioGroup grupo;
    RadioButton rbmovistar, rbepsgrau, rbenosa;
    Button buttonnext;

    public EntidadTramiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_entidad_tramite, container, false);
        rbmovistar = (RadioButton) view.findViewById(R.id.rb_empresa_telefonia_trammite);
        rbepsgrau = (RadioButton) view.findViewById(R.id.rb_epsgrau_entidad_tramite);
        rbenosa = (RadioButton) view.findViewById(R.id.rb_enosa_entidad_tramite);
        buttonnext=(Button) view.findViewById(R.id.btn_entidad_siguiente_tramite);

        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rbenosa.isChecked()){
                    Intent intent = new Intent(getContext(),InfoTramitesEnosaActivity.class);
                    startActivity(intent);
                }
                if (rbepsgrau.isChecked()){
                    Intent intent = new Intent(getContext(),InfoTramitesEnosaActivity.class);
                    startActivity(intent);
                }
                if (rbmovistar.isChecked()){
                    Intent intent = new Intent(getContext(),InfoTramitesEnosaActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
