package com.piuraservices.piuraservices.adapters.enosa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;

import java.util.ArrayList;
import java.util.List;

public class ListaInfoReclamosEnosaAdapter  extends BaseAdapter {
    private Context context;
    protected ArrayList<InfoReclamosEnosamodel> lista;

    public ListaInfoReclamosEnosaAdapter(Context context, ArrayList<InfoReclamosEnosamodel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public InfoReclamosEnosamodel getItem(int position) {
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
            row = inflater.inflate(R.layout.lista_info_reclamos_enosa, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_reclamos_enosa_text);
        InfoReclamosEnosamodel item = lista.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}