package com.piuraservices.piuraservices.adapters.epsgrau;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.epsgrau.InfoReclamosEpsgraumodel;
import java.util.ArrayList;
public class ListaInfoReclamosepsAdapter extends BaseAdapter {

    private Context context;
    protected ArrayList<InfoReclamosEpsgraumodel> lista;
    public ListaInfoReclamosepsAdapter(Context context, ArrayList<InfoReclamosEpsgraumodel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public InfoReclamosEpsgraumodel getItem(int position) {
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
            row = inflater.inflate(R.layout.lista_info_reclamos_eps, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.list_reclamos_pagination_text);
        InfoReclamosEpsgraumodel item = lista.get(position);
        String message = item.getNombre();
        textView.setText(message);
        return row;
    }
}