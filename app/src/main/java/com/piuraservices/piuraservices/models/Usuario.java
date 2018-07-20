package com.piuraservices.piuraservices.models;

import com.google.gson.TypeAdapterFactory;

import java.io.Serializable;

public class Usuario implements Serializable{
    private long id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private int edad;
    private String sexo;
    private String correo;
    private String passeword;

    public Usuario(){

    }
    /**
     * @param id
     * @param nombres
     * @param apellidos
     * @param direccion
     * @param telefono
     * @param edad
     * @param sexo
     * @param correo
     *  @param passeword
     */
    public Usuario(long id, String nombres, String apellidos, String direccion, String telefono, int edad, String sexo, String correo, String passeword) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
        this.passeword = passeword;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasseword() {
        return passeword;
    }

    public void setPasseword(String passeword) {
        this.passeword = passeword;
    }
}
