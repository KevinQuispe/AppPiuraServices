package com.piuraservices.piuraservices.models.epsgrau;

import java.util.ArrayList;

public class InfoContactosEpsgraumodel {
    private  long id;
    private  String nombre_empresa;
    private  long numero;
    private  String descripcion;
    ArrayList<InfoContactosEpsgraumodel> contactosepsgrau=new ArrayList<>();

    public InfoContactosEpsgraumodel(){

    }
    public InfoContactosEpsgraumodel(long id, String nombre_empresa, long numero, String descripcion, ArrayList<InfoContactosEpsgraumodel> contactosepsgrau) {
        this.id = id;
        this.nombre_empresa = nombre_empresa;
        this.numero = numero;
        this.descripcion = descripcion;
        this.contactosepsgrau = contactosepsgrau;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<InfoContactosEpsgraumodel> getContactosepsgrau() {
        return contactosepsgrau;
    }

    public void setContactosepsgrau(ArrayList<InfoContactosEpsgraumodel> contactosepsgrau) {
        this.contactosepsgrau = contactosepsgrau;
    }
}

