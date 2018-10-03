package com.piuraservices.piuraservices.models.telefonia.claro;

public class InfoReferencialClaromodel {

    private long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String correo;
    private String horario;
    private String webentidad;

    public InfoReferencialClaromodel(long id, String nombre, String direccion, String telefono, String correo, String horario, String webentidad) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.horario = horario;
        this.webentidad = webentidad;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getWebentidad() {
        return webentidad;
    }

    public void setWebentidad(String webentidad) {
        this.webentidad = webentidad;
    }

    @Override
    public String toString() {
        return "InfoReferencialClaromodel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", horario='" + horario + '\'' +
                ", webentidad='" + webentidad + '\'' +
                '}';
    }
}

