
package com.mycompany.diariodeentrenamiento;


import java.sql.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author victo
 */
public class Filtro {
    private String fechaMinima;
    private String fechaMaxima;
    private String ejercicio;
    private String pesoCorporalMinimo;
    private String pesoCorporalMaximo;
    private String pesoMinimo;
    private String pesoMaximo;
    
    public Filtro(){
        String fechaMinima = null;
        fechaMaxima = null;
        ejercicio = null;
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
    
    public String crearQuery(){
          
        String tablas = " Entrenamiento ent , Ejercicio ej , Serie s";
        String columnas = " select ej from ";
        String condicion = " where ent.id = ej.idEntrenamiento and s.idEjercicio = ej.id ";
        if(this.fechaMinima != null){
            condicion += " ej.fecha >" + fechaMinima;
        }
        if(this.ejercicio != null){
            if(!ejercicio.equals("Todos"))
                condicion += " and ej.nombre like '" + ejercicio +"'";
        }
        if(condicion.equals("where "))
            condicion = "";
        
        return columnas + tablas + condicion + "group by ej.id order by ent.fecha";
    }
}
