package com.piuraservices.piuraservices.adapters.epsgrau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;

import java.util.ArrayList;

public class ListaInfoTramitesepsAdapter extends BaseAdapter {

    private Context context;
    protected ArrayList<InfoTramitesEpsgraumodel> lista;

    public ListaInfoTramitesepsAdapter(Context context, ArrayList<InfoTramitesEpsgraumodel> lista) {
        this.context = context;
        this.lista = lista;
    }
    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public InfoTramitesEpsgraumodel getItem(int position) {
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
            row = inflater.inflate(R.layout.lista_info_tramites_eps, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_tramites_pagination_text);
        InfoTramitesEpsgraumodel item = lista.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}

