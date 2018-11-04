package com.piuraservices.piuraservices.models.telefonia.movistar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InfoTramitesMovistarmodel  implements Serializable {
    private long id;
    private String nombre;
    private String descripcion;

    public InfoTramitesMovistarmodel() {
    }

    public InfoTramitesMovistarmodel(long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

