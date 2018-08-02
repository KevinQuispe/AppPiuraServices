package com.piuraservices.piuraservices.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.views.activities.LoginActivity;
import com.piuraservices.piuraservices.views.activities.TelefoniaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntidadesFragment extends Fragment{

    CardView cardView;
    ImageView imgtelefonia;
    ImageView imgenosa;
    ImageView imgepsgrau;
    public EntidadesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_entidades, container, false);
        cardView=(CardView) v.findViewById(R.id.card_telefonia);
        imgtelefonia=(ImageView) v.findViewById(R.id.img_telefonia);
        imgepsgrau=(ImageView) v.findViewById(R.id.img_epsgrau);
        imgenosa=(ImageView) v.findViewById(R.id.img_enosa);

        onClicked(v);
        onClickedEps(v);
        onClickedenosa(v);
        return  v;
    }

    public void onClicked( View v){
        imgtelefonia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activities.TelefoniaActivity");
                startActivity(intent);
            }
        });
    }
    public void onClickedEps( View v){
        imgepsgrau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activities.EPS_grauActivity");
                startActivity(intent);
            }
        });
    }
    public void onClickedenosa( View v){
        imgenosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("views.activities.EnosaActivity");
                startActivity(intent);
            }
        });
    }
    }

