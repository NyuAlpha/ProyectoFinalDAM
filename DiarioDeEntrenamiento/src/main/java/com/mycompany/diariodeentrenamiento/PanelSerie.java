package com.mycompany.diariodeentrenamiento;

import POJOs.Serie;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class PanelSerie extends PanelDatos{
   
    public PanelSerie(Serie serie,MouseListener listener,GUI interfaz){
        
        super(listener,interfaz,serie);      
        generarTexto();
    }
    
    public void eliminar(){

        GestorDatos.eliminarSerie((Serie)entidad);
        interfaz.annadirSeries(GestorDatos.getSeries(((Serie)entidad).getIdEjercicio()));
    }

    @Override
    public void generarLista() {
    }

    public Serie getSerie() {
        return (Serie)entidad;
    }  
}
