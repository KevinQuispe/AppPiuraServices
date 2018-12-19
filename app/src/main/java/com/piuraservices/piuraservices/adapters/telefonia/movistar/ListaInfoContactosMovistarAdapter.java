package com.piuraservices.piuraservices.adapters.telefonia.movistar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.piuraservices.piuraservices.R;
import com.piuraservices.piuraservices.models.telefonia.movistar.InfoContactosMovistarmodel;

import java.util.ArrayList;

public class ListaInfoContactosMovistarAdapter  extends BaseAdapter{
    private Context context;
    protected ArrayList<InfoContactosMovistarmodel> lista;

    public ListaInfoContactosMovistarAdapter(Context context, ArrayList<InfoContactosMovistarmodel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public InfoContactosMovistarmodel getItem(int position) {
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
            row = inflater.inflate(R.layout.lista_info_contactos_movistar, parent, false);
        }
        TextView textView = (TextView) row.findViewById(R.id.lista_contactos_movistar_text);
        InfoContactosMovistarmodel item = lista.get(position);
        String nombreempresa = item.getNombreempresa();
        String direccion = item.getDireccion();
        String horario = item.getHorario();
        String telefono = item.getTelefono();
        String tipoatencion = item.getTipoatencion();
        textView.setText(nombreempresa);
        return row;
    }
}
