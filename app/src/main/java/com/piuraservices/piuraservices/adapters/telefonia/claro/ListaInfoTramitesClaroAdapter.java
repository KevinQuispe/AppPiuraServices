package com.piuraservices.piuraservices.adapters.telefonia.claro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoReclamosClaromodel;
import com.piuraservices.piuraservices.models.telefonia.claro.InfoTramitesClaromodel;

import java.util.List;

public class ListaInfoTramitesClaroAdapter  extends ArrayAdapter<InfoTramitesClaromodel> {
    private Context context;
    private List<InfoTramitesClaromodel> values;

    public ListaInfoTramitesClaroAdapter(Context context, List<InfoTramitesClaromodel> values) {
        super(context, R.layout.lista_info_tramites_claro, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_tramites_claro, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_tramites_claro_text);
        InfoTramitesClaromodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}
