package com.piuraservices.piuraservices.models.telefonia.claro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoReclamosClaromodel {

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("entidadempresa_id")
    @Expose
    private long entidadempresa_id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("decripcion")
    @Expose
    private String descripcion;

    public InfoReclamosClaromodel() {

    }

    public InfoReclamosClaromodel(long id, long entidadempresa_id, String nombre, String descripcion) {
        this.id = id;
        this.entidadempresa_id = entidadempresa_id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEntidadempresa_id() {
        return entidadempresa_id;
    }

    public void setEntidadempresa_id(long entidadempresa_id) {
        this.entidadempresa_id = entidadempresa_id;
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

    @Override
    public String toString() {
        return "InfoReclamosClaromodel{" +
                "id=" + id +
                ", entidadempresa_id=" + entidadempresa_id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
