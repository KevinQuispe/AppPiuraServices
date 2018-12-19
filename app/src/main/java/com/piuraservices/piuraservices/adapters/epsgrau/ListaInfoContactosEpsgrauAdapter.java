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
    private Context context;
    protected ArrayList<InfoContactosEpsgraumodel> lista;

    public ListaInfoContactosEpsgrauAdapter(Context context, ArrayList<InfoContactosEpsgraumodel> lista) {
        this.context = context;
        this.lista = lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public InfoContactosEpsgraumodel getItem(int position) {
        return lista.get(position);
    }
    @Override
    public long getItemId(int position) {
        return lista.get(position).getId();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_contactos_eps, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.lista_contactos_eps_text);
        InfoContactosEpsgraumodel item = lista.get(position);
        String nombre = item.getNombreempresa();
        String direccion = item.getDireccion();
        textView.setText(nombre);
        return row;
    }
}