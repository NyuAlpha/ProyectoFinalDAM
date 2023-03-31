package com.mycompany.diariodeentrenamiento;

import POJOs.Serie;
import java.awt.event.MouseListener;

/**
 *
 * @author victo
 */
public class CasillaSerie extends Casilla{
   
    public CasillaSerie(Serie serie,MouseListener listener,PanelEdicion interfaz){
        
        super(listener,interfaz,serie);      
        generarTexto();
    }
    
    public void eliminar(){

        GestorBaseDatos.eliminarSerie((Serie)registro);
        interfaz.eliminarPanelSerie(this);

    }

    @Override
    public void generarLista() {
    }

    public Serie getSerie() {
        return (Serie)registro;
    } 

    
    public boolean equals (Serie s){
        return ((Serie)registro).equals(s);
    }
    
    public void clicar(){
        interfaz.seleccionarSerie(this);
    }
}
