package com.piuraservices.piuraservices.adapters.telefonia.claro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReclamosClaromodel;
import java.util.List;

public class ListaInfoReclamosClaroAdapter extends ArrayAdapter<InfoReclamosClaromodel> {
    private Context context;
    private List<InfoReclamosClaromodel> values;

    public ListaInfoReclamosClaroAdapter(Context context, List<InfoReclamosClaromodel> values) {
        super(context, R.layout.lista_info_reclamos_claro, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_reclamos_claro, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_reclamos_claro_text);
        InfoReclamosClaromodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}
