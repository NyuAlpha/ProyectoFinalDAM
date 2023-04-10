package com.mycompany.POJOs;

import java.util.ArrayList;

public class Ejercicio implements Entidad{
    private int id;
    private int idEntrenamiento;
    private String nombre;
    private String variante; 
    private int orden;
    private String descripcion;
    
    //La lista de series al ser puramente opcional no se incluirá en el archivo ejercicio.hbm
    //Esta se cargará manualmente solo cuando seá necesario.
    private ArrayList<Serie> series;

    public Ejercicio(int idEntrenamiento, String nombre, String variante, int orden, String descripcion) {
        this.idEntrenamiento = idEntrenamiento;
        this.nombre = nombre;
        this.variante = variante;
        this.orden = orden;
        this.descripcion = descripcion;
    }

    public Ejercicio() {
    }

    public int getId() {
        return id;
    }

    public int getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVariante() {
        return variante;
    }

    public int getOrden() {
        return orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEntrenamiento(int idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVariante(String variante) {
        this.variante = variante;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
    
    
    //Genera las series de este ejercicio con el formato clásico "peso(serie s xrepeticiones)"
    public String resumenSeries(){
        StringBuilder stringBuilder = new StringBuilder("");
        int peso = -1;
        int reps = 0;
        int numSeries = 1;
        String numSeriesString = "";
        for(Serie s : series){
            //Si es la primera serie
            if(peso == -1){
                peso = (int)s.getPeso();
                reps = s.getRepeticiones();
                stringBuilder.append(" " + peso + "kg(");
            }
            //a partir de la segunda serie
            else{         
                //Si el peso cambia se escribe la/s serie/s para ese peso
                if(peso != (int)s.getPeso()){
                    peso = (int)s.getPeso();
                    numSeriesString = (numSeries==1)? "": (numSeries+"x");
                    stringBuilder.append(numSeriesString + reps + ")," + peso + "kg(");
                    reps = s.getRepeticiones();
                    numSeries = 1;
                }
                //Si el peso no varia entonces se comprueban las repeticiones
                else{
                    //Si las repeticiones han variado se escriben y se resetean
                    if(reps != s.getRepeticiones()){
                        numSeriesString = (numSeries==1)? "": (numSeries+"x");
                        stringBuilder.append(numSeriesString + reps + ",");
                        reps = s.getRepeticiones();
                        numSeries = 1;
                    }
                    //Si no sigue subiendo el contador de series para unas mismas repeticiones
                    else{
                        numSeries++;
                    }
                }
            }
        }
        //Para acabar añade el último set de series, ya que el for no abarca el final del ejercicio
        numSeriesString = (numSeries==1)? "": (numSeries+"x");
        stringBuilder.append(numSeriesString + reps + ")");
        
        //Añade los extras de cada serie al final del ejercicio
        for(Serie s : series){
            String ex = (s.getExtra() == null || s.getExtra().isEmpty())?  "" : ("(" +s.getNumSerie() +": " +  s.getExtra() + ")");
            stringBuilder.append(ex);
        }
        //devuelve la cadena con la información de las series
        return stringBuilder.toString();
    }
    
    @Override
    public String toString(){
        String n = (nombre == null || nombre.isEmpty())? "Sin nombrar ": (nombre + " ");
        String v = (variante == null)? "":variante;
        String d = (descripcion == null || descripcion.isEmpty())? "": (": " + descripcion);
        return n + v + d;
    }
}
