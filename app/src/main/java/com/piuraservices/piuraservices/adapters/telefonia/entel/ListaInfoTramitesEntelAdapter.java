package com.piuraservices.piuraservices.adapters.telefonia.entel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoReclamosEntelmodel;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoTramitesEntelmodel;

import java.util.ArrayList;
import java.util.List;

public class ListaInfoTramitesEntelAdapter extends BaseAdapter{
    private Context context;
    protected ArrayList<InfoTramitesEntelmodel> lista;

    public ListaInfoTramitesEntelAdapter(Context context, ArrayList<InfoTramitesEntelmodel> lista) {
        this.context = context;
        this.lista = lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public InfoTramitesEntelmodel getItem(int position) {
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
            row = inflater.inflate(R.layout.lista_info_tramites_entel, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_tramites_entel_text);
        InfoTramitesEntelmodel item = lista.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}
