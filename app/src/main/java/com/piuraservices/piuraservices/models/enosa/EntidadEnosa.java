package com.piuraservices.piuraservices.models.enosa;

public class EntidadEnosa {
    private String nombre;
    private String direccion;
    private String telefono;
    private String coordenadas;

    public EntidadEnosa() {
    }

    public EntidadEnosa(String nombre, String direccion, String telefono, String coordenadas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.coordenadas = coordenadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
