package com.egiladmin.egiladmin;

public class Reserva extends Departamento {

    private int id;
    private String fecha;
    private String hora;
    private String turno;
    private int departamento;

    public Reserva(int id, String fecha, String hora, String turno, int departamento) {
        this.setId(id);
        this.setFecha(fecha);
        this.setHora(hora);
        this.setTurno(turno);
        this.setDepartamento(departamento);
    }

    public Reserva(int id, String fecha, String hora, String turno, int departamento, String torre, String nombre, String apellido) {
        super(torre, nombre, apellido);
        this.setId(id);
        this.setFecha(fecha);
        this.setHora(hora);
        this.setTurno(turno);
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
}
