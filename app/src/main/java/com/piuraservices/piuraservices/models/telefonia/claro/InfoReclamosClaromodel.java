package com.piuraservices.piuraservices.models.telefonia.claro;

public class InfoReclamosClaromodel {
    private  long id;
    private String nombre;
    private  String descripcion;

    public InfoReclamosClaromodel(){

    }
    public InfoReclamosClaromodel(long id, String nombre, String descripcion) {
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
