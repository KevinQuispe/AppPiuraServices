package com.piuraservices.piuraservices.services.geolocation;

public class EpsGrau {
    private double latitud;
    private double longitud;
    private String nombre;
    private String direccion;

    public EpsGrau() {
        nombre="Epsgrau";
        direccion="Piura";
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
