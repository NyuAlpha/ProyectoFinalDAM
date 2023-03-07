package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import java.awt.event.MouseListener;

/**
 *
 * @author victo
 */
public class PanelEjercicio extends PanelDatos {
    
    
    public PanelEjercicio(Ejercicio ejercicio,MouseListener listener,GUI interfaz){
        
        super(listener,interfaz,ejercicio);     
        generarTexto();
    }
    
    public void eliminar(){
         GestorDatos.eliminarEjercicio((Ejercicio)entidad);
         interfaz.eliminarPanelEjercicio(this);
    }
    
    public void generarLista(){
        interfaz.annadirSeries(GestorDatos.getSeries(((Ejercicio)entidad).getId()));
    }

    public Ejercicio getEjercicio() {
        return (Ejercicio)entidad;
    }
    
}
