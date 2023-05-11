package com.mycompany.diariodeentrenamiento;

import com.mycompany.POJOs.Ejercicio;
import com.mycompany.POJOs.Entrenamiento;
import com.mycompany.POJOs.Temporada;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *  Esta clase implementa las búsquedas que se harán en la base de datos para el módulo de búsqueda de la app
 */
public class LogicaBusqueda {
    
    //Parámetros que se usarán para filtrar las búsquedas
    private String fechaMinima;
    private String fechaMaxima;
    private String ejercicio;
    private String temporada;
    private String pesoCorporalMinimo;
    private String pesoCorporalMaximo;
    private String pesoMinimo;
    private String pesoMaximo;
    
    //Construcor, todo se inicia como null
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
    
    //Fabrica y devuelve una query en forma de cadena de texto en función de que campos son null y cuales no
    private String crearQuery(){
        
        //Fragmentos fijos de texto para construir la query 
        String tablas = " Temporada t , Entrenamiento ent , Ejercicio ej , Serie s";
        String columnas = " select ej from ";
        String condicion = " where t.id = ent.idTemporada and ent.id = ej.idEntrenamiento and s.idEjercicio = ej.id ";
        
        //Conjunto de if para añadir cada instrucción a la query en caso de que sea necesario
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
            if(!ejercicio.equals("Todos"))//Si no se pone todos los filtra por nombre
                condicion += " and ej.nombre like '" + ejercicio +"'";
        }
        if(temporada != null && !temporada.isEmpty()){
            if(!temporada.equals("Todas"))//Si no se pone todas se filtra por descripción
                condicion += " and t.descripcion like '" + temporada +"'";
        }
        //Si solo hay un where sin ninguna comprobación se elimina de la query, para que no quede solo sin nada.
        if(condicion.equals("where "))
            condicion = "";
        
        //ensambla todos los fragmentos de texto, agrupa por ejercicio y ordena por fecha, y devuelve la cadena completa
        return columnas + tablas + condicion + "group by ej.id order by ent.fecha";
    }
    
    
    //Este método recoge el resultado de la query y devuelve una cadena de texto con el resumen de la búsqueda
    public String buscar(){
        StringBuilder text = new StringBuilder("");
        ArrayList<Ejercicio> ejercicios = GestorBaseDatos.getConsulta(crearQuery());
        //Entrenamiento y temporada que se imprimirá junto a los entrenamientos que esten contenidos en ellos para
        //que la información este más completa, pero nunca se repetirán para evitar información reiterada
        Entrenamiento entrenamientoImprimir = null;
        Temporada temporadaImprimir = null;
        
        //Variable de control para que haga algo solo la primera vez
        boolean primerVez = true;

        for(Ejercicio e : ejercicios){
            //La primera vez imprimirá la temporada y el entrenamiento al que pertenece el ejercicio. 
            if(primerVez){
                entrenamientoImprimir = GestorBaseDatos.getEntrenamiento(e.getIdEntrenamiento());
                temporadaImprimir = GestorBaseDatos.getTemporada(entrenamientoImprimir.getIdTemporada());
                text.append( "  TEMPORADA: " + temporadaImprimir + "\n");
                text.append("\n    *" + entrenamientoImprimir + "\n");
                primerVez = false;//Asegura que no se repetirá este if
            }
            //A partir del segundo registro solo se imprime la temporada o entrenamiento cuando este cambie para no repetir datos
            else{
                
                //if anidados para verificar si se ha cambiado de entrenamiento o temporada y así imprimirlo
                if(entrenamientoImprimir.getId() != e.getIdEntrenamiento()){
                    entrenamientoImprimir = GestorBaseDatos.getEntrenamiento(e.getIdEntrenamiento());
                    if(temporadaImprimir.getId() != entrenamientoImprimir.getIdTemporada()){
                        temporadaImprimir = GestorBaseDatos.getTemporada(entrenamientoImprimir.getIdTemporada());
                        text.append( "\n  TEMPORADA: " + temporadaImprimir + "\n");   
                    }
                    text.append("\n    *" + entrenamientoImprimir + "\n");
                }
            }
            text.append("       -" + e.toString() + e.resumenSeries() +"\n");
        }
        //Devuelve el resumen de a búsqueda
        return text.toString();
    }
    
    /*********************************************************************
    *               METODOS SET.
    *   Todos con sus restricciones correspondientes para evitar errores
    **********************************************************************/
    
    
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

    public void setPesoCorporalMinimo(String pesoCorporalMinimo){
        if(!pesoCorporalMinimo.equals("")){       
            try{
                Float p = Float.parseFloat(pesoCorporalMinimo);
                if(p < 0 || p > 1000){
                    throw new IllegalArgumentException();
                }
                else
                    this.pesoCorporalMinimo = String.valueOf(p);

            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso corporal mínimo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    public void setPesoCorporalMaximo(String pesoCorporalMaximo) {
        if(!pesoCorporalMaximo.equals("")){
            try{
                Float p = Float.parseFloat(pesoCorporalMaximo);
                if(p < 0 || p > 1000){
                    throw new IllegalArgumentException();
                }
                else
                    this.pesoCorporalMaximo = String.valueOf(p);
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso corporal máximo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }
    }

    public void setPesoMinimo(String pesoMinimo) {
        if(!pesoMinimo.equals("")){
            try{
                Float p = Float.parseFloat(pesoMinimo);
                if(p < 0 || p > 1000){
                    throw new IllegalArgumentException();
                }
                else
                    this.pesoMinimo = String.valueOf(p);
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso mínimo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }    
    }

    public void setPesoMaximo(String pesoMaximo) {
        if(!pesoMaximo.equals("")){
            try{
                Float p = Float.parseFloat(pesoMaximo);
                if(p < 0 || p > 1000){
                    throw new IllegalArgumentException();
                }
                else
                    this.pesoMaximo = String.valueOf(p);
            }catch(java.lang.IllegalArgumentException e){
                JOptionPane.showMessageDialog(new JPanel(), "Peso máximo incorrecto", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
        }
    }
}
