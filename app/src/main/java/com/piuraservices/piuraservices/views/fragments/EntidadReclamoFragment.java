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
import com.piuraservices.piuraservices.views.activitiesenosa.InfoReclamosEnosaActivity;
import com.piuraservices.piuraservices.views.activitiesepsgrau.InfoReclamosEpsActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.claro.InfoReclamosClaroActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.entel.InfoReclamosEntelActivity;
import com.piuraservices.piuraservices.views.activitiestelefonia.movistar.InfoReclamosMovistarActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntidadReclamoFragment extends Fragment {

    RadioGroup grupo;
    RadioButton rbmovistar, rbepsgrau, rbenosa, rbclaro, rbentel;
    Button buttonnext;

    public EntidadReclamoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_entidad_reclamo, container, false);
        rbenosa = (RadioButton) view.findViewById(R.id.rb_enosa_entidad);
        rbepsgrau = (RadioButton) view.findViewById(R.id.rb_epsgrau_entidad);
        rbmovistar = (RadioButton) view.findViewById(R.id.rb_telefonia_empresa);
        rbentel = (RadioButton) view.findViewById(R.id.rb_empresa_entel_reclamo);
        rbclaro = (RadioButton) view.findViewById(R.id.rb_empresa_Claro_reclamo);
        buttonnext = (Button) view.findViewById(R.id.btn_entidad_siguiente);

        buttonnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rbenosa.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosEnosaActivity.class);
                    startActivity(intent);
                }
                if (rbepsgrau.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosEpsActivity.class);
                    startActivity(intent);
                }
                if (rbmovistar.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosMovistarActivity.class);
                    startActivity(intent);
                }
                if (rbclaro.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosClaroActivity.class);
                    startActivity(intent);
                }
                if (rbentel.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosEntelActivity.class);
                    startActivity(intent);
                } else if (!rbentel.isChecked() && !rbclaro.isChecked() && !rbmovistar.isChecked() && !rbenosa.isChecked() && !rbepsgrau.isChecked()) {
                    Toast.makeText(getActivity(), "Seleccione entidad", Toast.LENGTH_SHORT).show();
                }
            }
        });
        onRadioButtonClicked(view);
        return view;
    }

    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        //       boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_enosa_entidad:
                if (rbenosa.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosEnosaActivity.class);
                    startActivity(intent);
                    break;
                }
            case R.id.rb_epsgrau_entidad:
                if (rbepsgrau.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosEpsActivity.class);
                    startActivity(intent);
                    break;
                }
            case R.id.rb_telefonia_empresa:
                if (rbmovistar.isChecked()) {
                    Intent intent = new Intent(getContext(), InfoReclamosMovistarActivity.class);
                    startActivity(intent);
                    break;
                }
        }
    }
}

