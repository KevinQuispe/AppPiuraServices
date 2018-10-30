package com.piuraservices.piuraservices.adapters.epsgrau;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;

import java.util.List;

public class ListaInfoTramitesepsAdapter extends ArrayAdapter<InfoTramitesEpsgraumodel> {
    private Context context;
    private List<InfoTramitesEpsgraumodel> values;

    public ListaInfoTramitesepsAdapter(Context context, List<InfoTramitesEpsgraumodel> values) {
        super(context, R.layout.lista_info_tramites_eps, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_tramites_eps, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_tramites_pagination_text);
        InfoTramitesEpsgraumodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}
