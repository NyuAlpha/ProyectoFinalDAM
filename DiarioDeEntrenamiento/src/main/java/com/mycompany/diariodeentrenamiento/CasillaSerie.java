package com.mycompany.diariodeentrenamiento;

import POJOs.Serie;
import POJOs.Temporada;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
        interfaz.addSeries(GestorBaseDatos.getSeries(((Serie)registro).getIdEjercicio()));
    }

    @Override
    public void generarLista() {
    }

    public Serie getSerie() {
        return (Serie)registro;
    }  
}
