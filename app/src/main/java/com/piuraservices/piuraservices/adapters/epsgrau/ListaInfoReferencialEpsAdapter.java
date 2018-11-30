package com.piuraservices.piuraservices.adapters.epsgrau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.enosa.InfoTramitesEnosamodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoReferencialEpsgraumodel;
import com.piuraservices.piuraservices.models.epsgrau.InfoTramitesEpsgraumodel;

import java.util.List;
public class ListaInfoReferencialEpsAdapter extends ArrayAdapter<InfoReferencialEpsgraumodel> {
    private Context context;
    private List<InfoReferencialEpsgraumodel> values; //lista con retrofit en un activity

    public ListaInfoReferencialEpsAdapter(Context context, List<InfoReferencialEpsgraumodel> values) {
        super(context, R.layout.lista_info_tramites_enosa, values);
        this.context = context;
        this.values = values;
    }
    //
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.lista_info_reclamos_eps, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_item_pagination_text);
        InfoReferencialEpsgraumodel item = values.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}
