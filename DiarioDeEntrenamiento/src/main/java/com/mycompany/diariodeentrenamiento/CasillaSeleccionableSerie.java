package com.mycompany.diariodeentrenamiento;

import com.mycompany.POJOs.Serie;
import java.awt.event.MouseListener;

/**
 *  Clase para representar los registros de series de la app
 */
public class CasillaSeleccionableSerie extends CasillaSeleccionable{
   
    //Constructor. Inicia la casilla con su texto correspondiente
    public CasillaSeleccionableSerie(Serie serie,MouseListener listener,PanelEdicion interfaz){
        
        super(listener,interfaz,serie);      
        generarTexto();
    }

    public Serie getSerie() {
        return (Serie)registro;
    } 

    //Devuelve true si el parametro serie es el mismo que la serie almacenada en la casilla
    public boolean equals (Serie s){
        return ((Serie)registro).equals(s);
    }
    
    /****************************
    *   MËTODOS DE SUPERCLASE
    *****************************/
    
    //Elimina el registro y este panel de la interfaz    
    public void eliminar(){
        GestorBaseDatos.eliminarSerie((Serie)registro);
        interfaz.eliminarPanelSerie(this);
    }

    //Las series no tienen listas
    public void generarLista() {}
    
    //Este método es llamado solo cuando se selecciona la casilla con el mouse (evento de mouse)
    public void clicar(){
        interfaz.seleccionarSerie(this);
    }
}
