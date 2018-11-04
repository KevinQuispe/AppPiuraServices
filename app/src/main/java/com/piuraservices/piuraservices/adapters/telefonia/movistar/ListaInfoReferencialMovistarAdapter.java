package com.piuraservices.piuraservices.adapters.telefonia.movistar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoReclamosMovistarmodel;

import java.util.List;

public class ListaInfoReferencialMovistarAdapter extends ArrayAdapter<InfoReclamosMovistarmodel> {
    private Context context;
    private List<InfoReclamosMovistarmodel> values;

    public ListaInfoReferencialMovistarAdapter(Context context, List<InfoReclamosMovistarmodel> values) {
        super(context, R.layout.lista_info_reclamos_movistar, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_reclamos_movistar, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_reclamos_movistar_text);
        InfoReclamosMovistarmodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}

