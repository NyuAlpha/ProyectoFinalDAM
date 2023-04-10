package com.mycompany.POJOs;

import java.sql.Date;

public class Temporada implements Entidad{
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcion;

    public Temporada(Date fechaInicio, Date fechaFin, String descripcion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }

    public Temporada() {
    }

    public int getId() {
        return id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        String fInicio = (fechaInicio == null)? "fecha desconocida":fechaInicio.toString();
        String fFin = (fechaFin == null)? "Sin fecha final":fechaFin.toString();
        String d = (descripcion.isEmpty())? " ": ( ": " + descripcion.toString());
        return fInicio +"  ->  "+ fFin + d;
    }
}
