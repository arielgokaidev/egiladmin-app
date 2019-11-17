package com.egiladmin.egiladmin;

public class Departamento extends Residente {

    private int numero;
    private String torre;
    private String estado;
    private String rut;

    public Departamento() {
        super();
    }

    public Departamento (int numero, String torre, String estado, String rut, String nombre, String apellido, String tipo) {
        super(rut, nombre, apellido, tipo);
        this.setNumero(numero);
        this.setTorre(torre);
        this.setEstado(estado);
        this.setRut(rut);
    }

    public Departamento (int numero, String torre, String estado, String rut) {
        this.setNumero(numero);
        this.setTorre(torre);
        this.setEstado(estado);
        this.setRut(rut);
    }

    public Departamento(int numero) {
        this.setNumero(numero);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTorre() {
        return torre;
    }

    public void setTorre(String torre) {
        this.torre = torre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }
}
