package com.piuraservices.piuraservices.adapters.enosa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;
import java.util.List;

public class ListaInfoTramitesEnosaAdapter  extends ArrayAdapter<InfoTramitesEnosamodel> {
    private Context context;
    private List<InfoTramitesEnosamodel> values;

    public ListaInfoTramitesEnosaAdapter(Context context, List<InfoTramitesEnosamodel> values) {
        super(context, R.layout.lista_info_tramites_enosa, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_tramites_enosa, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_tramites_enosa_text);
        InfoTramitesEnosamodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}