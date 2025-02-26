package com.egiladmin.egiladmin;

public class Residente {

    protected String rut;
    protected String nombre;
    protected String apellido;
    private String usuario;
    private String password;
    protected String tipo;
    
    public Residente() {}

    public Residente(String rut, String nombre, String apellido, String usuario, String password, String tipo) {
        this.setRut(rut);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setUsuario(usuario);
        this.setPassword(password);
        this.setTipo(tipo);
    }

    public Residente(String usuario, String password, String nombre) {
        this.setUsuario(usuario);
        this.setPassword(password);
        this.setNombre(nombre);
    }

    public Residente(String rut) {
        this.setRut(rut);
    }

    public Residente(String rut, String nombre, String apellido, String tipo) {
        this.setRut(rut);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setTipo(tipo);
    }

    public Residente(String nombre, String apellido) {
        this.setNombre(nombre);
        this.setApellido(apellido);
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
