package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import POJOs.Entrenamiento;
import POJOs.Temporada;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author victo
 */
public class LogicaBusqueda {
    private String fechaMinima;
    private String fechaMaxima;
    private String ejercicio;
    private String temporada;
    private String pesoCorporalMinimo;
    private String pesoCorporalMaximo;
    private String pesoMinimo;
    private String pesoMaximo;
    
    public LogicaBusqueda(){
        fechaMinima = null;
        fechaMaxima = null;
        ejercicio = null;
        temporada = null;
        pesoCorporalMinimo = null;
        pesoCorporalMaximo = null;
        pesoMinimo = null;
        pesoMaximo = null;
    }

    public void setFechaMinima(String fechaMinima) {
        if(!fechaMinima.equals("")){
            try{
                this.fechaMinima = Date.valueOf(fechaMinima).toString();
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Fecha mínima incorrecta", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            } 
        }
    }

    public void setFechaMaxima(String fechaMaxima) {
        if(!fechaMaxima.equals("")){
            try{
                this.fechaMaxima = Date.valueOf(fechaMaxima).toString();
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Fecha máxima incorrecta", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    public void setEjercicio(String ejercicio) {
        if(!ejercicio.equals("")){       
            this.ejercicio = ejercicio;
        }
    }
    
    public void setTemporada(String temporada) {
        if(!temporada.equals("")){       
            this.temporada = temporada;
        }
    }

    public void setPesoCorporalMinimo(String pesoCorporalMinimo) {
        if(!pesoCorporalMinimo.equals("")){       
            try{
                this.pesoCorporalMinimo = String.valueOf(Float.parseFloat(pesoCorporalMinimo));
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso corporal mínimo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    public void setPesoCorporalMaximo(String pesoCorporalMaximo) {
        if(!pesoCorporalMaximo.equals("")){
            try{
                this.pesoCorporalMaximo = String.valueOf(Float.parseFloat(pesoCorporalMaximo));
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso corporal máximo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    public void setPesoMinimo(String pesoMinimo) {
        if(!pesoMinimo.equals("")){
            try{
                this.pesoMinimo = String.valueOf(Float.parseFloat(pesoMinimo));
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso mínimo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }    
    }

    public void setPesoMaximo(String pesoMaximo) {
        if(!pesoMaximo.equals("")){
            try{
                this.pesoMaximo = String.valueOf(Float.parseFloat(pesoMaximo));
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso máximo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }
    }
    
    private String crearQuery(){
          
        String tablas = " Temporada t , Entrenamiento ent , Ejercicio ej , Serie s";
        String columnas = " select ej from ";
        String condicion = " where t.id = ent.idTemporada and ent.id = ej.idEntrenamiento and s.idEjercicio = ej.id ";
        
        if(fechaMinima != null && !fechaMinima.isEmpty()){
            condicion += " and ent.fecha >= '" + fechaMinima + "'";
        }
        if(fechaMaxima != null && !fechaMaxima.isEmpty()){
            condicion += " and ent.fecha <= '" + fechaMaxima + "'";
        }
        if(pesoCorporalMinimo != null && !pesoCorporalMinimo.isEmpty()){
            condicion += " and ent.pesoCorporal >= " + pesoCorporalMinimo;
        }
        if(pesoCorporalMaximo != null && !pesoCorporalMaximo.isEmpty()){
            condicion += " and ent.pesoCorporal <= " + pesoCorporalMaximo;
        }
        if(pesoMinimo != null && !pesoMinimo.isEmpty()){
            condicion += " and s.peso >= " + pesoMinimo;
        }
        if(pesoMaximo != null && !pesoMaximo.isEmpty()){
            condicion += " and s.peso <= " + pesoMaximo;
        }
        if(ejercicio != null && !ejercicio.isEmpty()){
            if(!ejercicio.equals("Todos"))
                condicion += " and ej.nombre like '" + ejercicio +"'";
        }
        if(temporada != null && !temporada.isEmpty()){
            if(!temporada.equals("Todas"))
                condicion += " and t.descripcion like '" + temporada +"'";
        }
        //Si solo hay un where sin más se elimina de la query
        if(condicion.equals("where "))
            condicion = "";
        
        //Returna la query agrupandola por ejercicio y ordenando por fecha
        return columnas + tablas + condicion + "group by ej.id order by ent.fecha";
    }
    
    public String buscar(){
        StringBuilder text = new StringBuilder("");
        ArrayList<Ejercicio> ejercicios = GestorBaseDatos.getConsulta(crearQuery());
        Entrenamiento entrenamientoImprimir = null;
        Temporada temporadaImprimir = null;
        boolean primerVez = true;

        for(Ejercicio e : ejercicios){
            if(primerVez){
                entrenamientoImprimir = GestorBaseDatos.getEntrenamiento(e.getIdEntrenamiento());
                temporadaImprimir = GestorBaseDatos.getTemporada(entrenamientoImprimir.getIdTemporada());
                text.append( "  " + temporadaImprimir + "\n");
                text.append("\n    *" + entrenamientoImprimir + "\n");
                primerVez = false;
            }
            else{

                if(entrenamientoImprimir.getId() != e.getIdEntrenamiento()){
                    entrenamientoImprimir = GestorBaseDatos.getEntrenamiento(e.getIdEntrenamiento());
                    if(temporadaImprimir.getId() != entrenamientoImprimir.getIdTemporada()){
                        temporadaImprimir = GestorBaseDatos.getTemporada(entrenamientoImprimir.getIdTemporada());
                        text.append( "\n  " + temporadaImprimir + "\n");   
                    }
                    text.append("\n    *" + entrenamientoImprimir + "\n");
                }
            }
            text.append("       -" + e.toString() + e.resumenSeries() +"\n");
        }
        return text.toString();
    }

}
