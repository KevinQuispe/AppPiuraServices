package com.piuraservices.piuraservices.adapters.telefonia.entel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoReclamosEntelmodel;

import java.util.List;

public class ListaInfoReclamosEntelAdapter  extends ArrayAdapter<InfoReclamosEntelmodel>{
    private Context context;
    private List<InfoReclamosEntelmodel> values;

    public ListaInfoReclamosEntelAdapter(Context context, List<InfoReclamosEntelmodel> values) {
        super(context, R.layout.lista_info_tramites_claro, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_reclamos_entel, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_reclamos_entel_text);
        InfoReclamosEntelmodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}
