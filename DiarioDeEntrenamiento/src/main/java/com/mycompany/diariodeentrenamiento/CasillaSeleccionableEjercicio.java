package com.mycompany.diariodeentrenamiento;

import com.mycompany.POJOs.Ejercicio;
import java.awt.event.MouseListener;

/**
 *  Clase para representar los registros de ejercicios de la app
 */
public class CasillaSeleccionableEjercicio extends CasillaSeleccionable {
    
    //Constructor. Inicia la casilla con su texto correspondiente
    public CasillaSeleccionableEjercicio(Ejercicio ejercicio,MouseListener listener,PanelEdicion interfaz){        
        super(listener,interfaz,ejercicio);     
        generarTexto();
    }
    
    public Ejercicio getEjercicio() {
        return (Ejercicio)registro;
    }
    
    //Devuelve true si el parametro ejercicio es el mismo que el ejercicio almacenado en la casilla
    public boolean equals (Ejercicio e){
        return ((Ejercicio)registro).getId() == e.getId();
    }
    
    
    /****************************
    *   MÉTODOS DE SUPERCLASE
    *****************************/
    
    //Elimina el registro y este panel de la interfaz
    public void eliminar(){
         GestorBaseDatos.eliminarEjercicio((Ejercicio)registro);
         interfaz.eliminarPanelEjercicio(this);
    }
    
    //Genera la lista de series de este ejercicio
    public void generarLista(){
        interfaz.addSeries(GestorBaseDatos.getSeries(((Ejercicio)registro).getId()));
    }
 
    //Este método es llamado solo cuando se selecciona la casilla con el mouse (evento de mouse)
    public void clicar(){
        interfaz.seleccionarEjercicio(this);
    }
    
}
