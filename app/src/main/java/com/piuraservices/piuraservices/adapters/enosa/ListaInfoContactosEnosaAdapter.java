package com.piuraservices.piuraservices.adapters.enosa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.enosa.InfoContactosEnosamodel;
import com.piuraservices.piuraservices.models.enosa.InfoReclamosEnosamodel;

import java.util.ArrayList;

public class ListaInfoContactosEnosaAdapter extends BaseAdapter {
    private Context context;
    protected ArrayList<InfoContactosEnosamodel> lista;

    public ListaInfoContactosEnosaAdapter(Context context, ArrayList<InfoContactosEnosamodel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }
    @Override
    public InfoContactosEnosamodel getItem(int position) {
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
            row = inflater.inflate(R.layout.lista_info_contactos_enosa, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.lista_contactos_enosa_text);
        InfoContactosEnosamodel item = lista.get(position);
        String nombreempresa = item.getNombreempresa();
        String oficinalugar = item.getOficinalugar();
        String direccion = item.getDireccion();
        String horario = item.getHorario();
        String telefono = item.getTelefono();
        String tipoatencion = item.getTipoatencion();

        textView.setText(oficinalugar);
        return row;
    }
}
