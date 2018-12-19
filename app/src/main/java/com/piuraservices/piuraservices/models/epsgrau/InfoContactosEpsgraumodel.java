package com.piuraservices.piuraservices.models.epsgrau;


import java.io.Serializable;

public class InfoContactosEpsgraumodel  implements Serializable{
    private long id;
    private String nombreempresa;
    private String direccion;
    private String telefono;
    private String horario;
    private String tipoatencion;

    public InfoContactosEpsgraumodel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreempresa() {
        return nombreempresa;
    }

    public void setNombreempresa(String nombreempresa) {
        this.nombreempresa = nombreempresa;
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

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTipoatencion() {
        return tipoatencion;
    }

    public void setTipoatencion(String tipoatencion) {
        this.tipoatencion = tipoatencion;
    }

    @Override
    public String toString() {
        return "InfoContactosEpsgraumodel{" +
                "id=" + id +
                ", nombreempresa='" + nombreempresa + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", horario='" + horario + '\'' +
                ", tipoatencion='" + tipoatencion + '\'' +
                '}';
    }
}

