package com.piuraservices.piuraservices.adapters.epsgrau;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoContactosEpsgraumodel;

import java.util.ArrayList;

public class ListaInfoContactosEpsgrauAdapter  extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<InfoContactosEpsgraumodel> lista;

    public ListaInfoContactosEpsgrauAdapter(Activity activity, ArrayList<InfoContactosEpsgraumodel> lista) {
        this.activity = activity;
        this.lista = lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lista.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1= view;
        if (view1==null){
            LayoutInflater layoutInflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //view1=layoutInflater.inflate(R.layout.fragment_lista_item,null);
        }
        InfoContactosEpsgraumodel contcato=lista.get(i);
        //TextView nombre=(TextView) view1.findViewById(R.id.tex_nombre_item);
        //nombre.setText(contcato.getNombre_empresa());
        return view1;
    }
}
