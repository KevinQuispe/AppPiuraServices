package com.piuraservices.piuraservices.adapters.telefonia.entel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.telefonia.entel.InfoContactosEntelmodel;

import java.util.ArrayList;

public class ListaInfoContactosEntelAdapter extends BaseAdapter{
    private Context context;
    protected ArrayList<InfoContactosEntelmodel> lista;

    public ListaInfoContactosEntelAdapter(Context context, ArrayList<InfoContactosEntelmodel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public InfoContactosEntelmodel getItem(int position) {
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
            row = inflater.inflate(R.layout.lista_info_contactos_entel, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.lista_contactos_entel_text);
        InfoContactosEntelmodel item = lista.get(position);
        String nombreempresa = item.getNombreempresa();
        String direccion = item.getDireccion();
        String horario = item.getHorario();
        String telefono = item.getTelefono();
        String tipoatencion = item.getTipoatencion();
        textView.setText(nombreempresa);
        return row;
    }
}
