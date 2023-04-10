package com.mycompany.POJOs;

import java.sql.Date;

public class Entrenamiento implements Entidad {
    
    private int id;
    private int idTemporada;
    private Date fecha;
    float pesoCorporal;
    String descripcion;

    public Entrenamiento(int idTemporada, Date fecha, float pesoCorporal, String descripcion) {
        this.idTemporada = idTemporada;
        this.fecha = fecha;
        this.pesoCorporal = pesoCorporal;
        this.descripcion = descripcion;
    }

    public Entrenamiento() {
    }

    public int getId() {
        return id;
    }

    public int getIdTemporada() {
        return idTemporada;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getPesoCorporal() {
        return pesoCorporal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTemporada(int id_temporada) {
        this.idTemporada = id_temporada;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPesoCorporal(float pesoCorporal) {
        this.pesoCorporal = pesoCorporal;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString(){
        String f = (fecha == null)? "Fecha desconocida":fecha.toString();
        String p = (pesoCorporal == 0)? "  (?kg)": ( "  (" + pesoCorporal + "kg) ");
        String d = (descripcion == null || descripcion.isEmpty())? "": ( ": " + descripcion);
        return f + p + d;
    }
    
}
