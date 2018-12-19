package com.piuraservices.piuraservices.models.enosa;

import java.io.Serializable;

public class InfoContactosEnosamodel  implements Serializable {
    private long id;
    private long entidadempresa_id;
    private String nombreempresa;
    private String direccion;
    private String telefono;
    private String horario;
    private String tipoatencion;

    public InfoContactosEnosamodel() {
    }

    public InfoContactosEnosamodel(long id, long entidadempresa_id, String nombreempresa, String direccion, String telefono, String horario, String tipoatencion) {
        this.id = id;
        this.entidadempresa_id = entidadempresa_id;
        this.nombreempresa = nombreempresa;
        this.direccion = direccion;
        this.telefono = telefono;
        this.horario = horario;
        this.tipoatencion = tipoatencion;
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
        return "InfoContactosEnosamodel{" +
                "id=" + id +
                ", entidadempresa_id=" + entidadempresa_id +
                ", nombreempresa='" + nombreempresa + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", horario='" + horario + '\'' +
                ", tipoatencion='" + tipoatencion + '\'' +
                '}';
    }
}