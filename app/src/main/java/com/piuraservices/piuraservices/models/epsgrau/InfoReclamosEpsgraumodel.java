package com.piuraservices.piuraservices.models.epsgrau;
import java.io.Serializable;
public class InfoReclamosEpsgraumodel implements Serializable {
    private long id;
    private String nombre;
    private String descripcion;

    public InfoReclamosEpsgraumodel() {
    }

    public InfoReclamosEpsgraumodel(long id, String nombre, String descripcion) {
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

    @Override
    public String toString() {
        return "InfoReclamosEpsgraumodel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
