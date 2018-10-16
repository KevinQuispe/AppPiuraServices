package com.piuraservices.piuraservices.adapters.telefonia.movistar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoTramitesMovistarmodel;
import java.util.List;

public class ListaInfoTramitesMovistarAdapter  extends ArrayAdapter<InfoTramitesMovistarmodel> {
    private Context context;
    private List<InfoTramitesMovistarmodel> values;

    public ListaInfoTramitesMovistarAdapter(Context context, List<InfoTramitesMovistarmodel> values) {
        super(context, R.layout.lista_info_tramites_movistar, values);
        this.context = context;
        this.values = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_tramites_movistar, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_tramites_movistar_text);
        InfoTramitesMovistarmodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}
