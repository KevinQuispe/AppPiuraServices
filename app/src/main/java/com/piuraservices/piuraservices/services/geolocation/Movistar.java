package com.piuraservices.piuraservices.services.geolocation;

public class Movistar {
    private double latitud;
    private double longitud;
    private String nombre;
    private String direccion;

    public Movistar() {
    }

    public Movistar(double latitud, double longitud, String nombre, String direccion) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
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
}
