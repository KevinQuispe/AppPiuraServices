package com.piuraservices.piuraservices.models.telefonia.entel;

import java.io.Serializable;

public class InfoContactosEntelmodel  implements Serializable{
    private long id;
    private String nombreempresa;
    private String oficinalugar;
    private String direccion;
    private String telefono;
    private String horario;
    private String tipoatencion;

    public InfoContactosEntelmodel() {
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

    public String getOficinalugar() {
        return oficinalugar;
    }

    public void setOficinalugar(String oficinalugar) {
        this.oficinalugar = oficinalugar;
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
        return "InfoContactosEntelmodel{" +
                "id=" + id +
                ", nombreempresa='" + nombreempresa + '\'' +
                ", oficinalugar='" + oficinalugar + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", horario='" + horario + '\'' +
                ", tipoatencion='" + tipoatencion + '\'' +
                '}';
    }
}
