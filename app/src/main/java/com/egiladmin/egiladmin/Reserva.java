package com.egiladmin.egiladmin;

public class Reserva {

    private int id;
    private String fecha;
    private String hora;
    private int valor;
    private int departamento;

    public Reserva(int id, String fecha, String hora, int valor, int departamento) {
        this.setId(id);
        this.setFecha(fecha);
        this.setHora(hora);
        this.setValor(valor);
        this.setDepartamento(departamento);
    }

    public Reserva(int id) {
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
}
