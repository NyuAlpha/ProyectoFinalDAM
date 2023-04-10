package com.mycompany.POJOs;

import java.io.Serializable;


public class Serie implements Entidad , Serializable{
    private int numSerie;
    private int idEjercicio;
    private float peso;
    private int repeticiones;
    private String extra;

    public Serie(int numSerie, int idEjercicio, float peso, int repeticiones, String extra) {
        this.numSerie = numSerie;
        this.idEjercicio = idEjercicio;
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.extra = extra;
    }

    public Serie() {
    }

    public int getNumSerie() {
        return numSerie;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public float getPeso() {
        return peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public String getExtra() {
        return extra;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    @Override
    public String toString(){
        String p = (peso == 0)? "Sin peso ": (peso +"kg x ");
        String r = (repeticiones == 0)? "? reps": Integer.toString(repeticiones);
        String e = (extra == null || extra.isEmpty())? " ": (": " + extra);
        return numSerie + ": " + p + r + e;
    }
    
    @Override
    public boolean equals(Object object){
        return ((Serie)object).getIdEjercicio() == idEjercicio && ((Serie)object).getNumSerie() == numSerie;
    }
    
    @Override
    public int hashCode(){
        return super.hashCode();
    }

    
    
}
