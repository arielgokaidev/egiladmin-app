package com.egiladmin.egiladmin;

public class Residente {

    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String tipo;

    public Residente(int id, String rut, String nombre, String apellido, String usuario, String password, String tipo) {
        this.setId(id);
        this.setRut(rut);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setUsuario(usuario);
        this.setPassword(password);
        this.setTipo(tipo);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
