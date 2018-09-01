package com.piuraservices.piuraservices.adapters.enosa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;

import java.util.List;

public class ListaInfoReclamosEnosaAdapter  extends ArrayAdapter<InfoReclamosEnosamodel> {
    private Context context;
    private List<InfoReclamosEnosamodel> values;
    public ListaInfoReclamosEnosaAdapter(Context context, List<InfoReclamosEnosamodel> values) {
        super(context, R.layout.lista_info_reclamos_enosa, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_reclamos_enosa, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_reclamos_enosa_text);
        InfoReclamosEnosamodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}