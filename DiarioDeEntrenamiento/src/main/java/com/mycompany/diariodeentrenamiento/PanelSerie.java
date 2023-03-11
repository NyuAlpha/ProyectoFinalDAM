package com.mycompany.diariodeentrenamiento;

import POJOs.Serie;
import POJOs.Temporada;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 *
 * @author victo
 */
public class PanelSerie extends PanelDatos{
   
    public PanelSerie(Serie serie,MouseListener listener,InterfazUsuario interfaz){
        
        super(listener,interfaz,serie);      
        generarTexto();
    }
    
    public void eliminar(){

        GestorBaseDatos.eliminarSerie((Serie)entidad);
        interfaz.annadirSeries(GestorBaseDatos.getSeries(((Serie)entidad).getIdEjercicio()));
    }

    @Override
    public void generarLista() {
    }

    public Serie getSerie() {
        return (Serie)entidad;
    }  
}
