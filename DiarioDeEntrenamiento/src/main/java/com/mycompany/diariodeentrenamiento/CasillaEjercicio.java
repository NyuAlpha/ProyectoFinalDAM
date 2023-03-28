package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import java.awt.event.MouseListener;

/**
 *
 * @author victo
 */
public class CasillaEjercicio extends Casilla {
    
    
    public CasillaEjercicio(Ejercicio ejercicio,MouseListener listener,PanelEdicion interfaz){
        
        super(listener,interfaz,ejercicio);     
        generarTexto();
    }
    
    public void eliminar(){
         GestorBaseDatos.eliminarEjercicio((Ejercicio)registro);
         interfaz.eliminarPanelEjercicio(this);
    }
    
    public void generarLista(){
        interfaz.addSeries(GestorBaseDatos.getSeries(((Ejercicio)registro).getId()));
    }

    public Ejercicio getEjercicio() {
        return (Ejercicio)registro;
    }
    
}
