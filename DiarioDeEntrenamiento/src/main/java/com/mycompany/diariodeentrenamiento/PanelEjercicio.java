package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import java.awt.event.MouseListener;

/**
 *
 * @author victo
 */
public class PanelEjercicio extends PanelDatos {
    
    
    public PanelEjercicio(Ejercicio ejercicio,MouseListener listener,InterfazUsuario interfaz){
        
        super(listener,interfaz,ejercicio);     
        generarTexto();
    }
    
    public void eliminar(){
         GestorBaseDatos.eliminarEjercicio((Ejercicio)entidad);
         interfaz.eliminarPanelEjercicio(this);
    }
    
    public void generarLista(){
        interfaz.annadirSeries(GestorBaseDatos.getSeries(((Ejercicio)entidad).getId()));
    }

    public Ejercicio getEjercicio() {
        return (Ejercicio)entidad;
    }
    
}
