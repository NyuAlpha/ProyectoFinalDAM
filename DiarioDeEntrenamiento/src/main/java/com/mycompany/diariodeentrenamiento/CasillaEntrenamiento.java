package com.mycompany.diariodeentrenamiento;

import POJOs.Entrenamiento;
import java.awt.event.MouseListener;

/**
 *
 * @author victo
 */
public class CasillaEntrenamiento extends Casilla {
    
    public CasillaEntrenamiento(Entrenamiento entrenamiento,MouseListener listener,PanelEdicion interfaz){
        
        super(listener,interfaz,entrenamiento);    
        generarTexto();
    }
    
    public void eliminar(){
         GestorBaseDatos.eliminarEntrenamiento((Entrenamiento)registro);
         interfaz.eliminarPanelEntrenamiento(this);
    }
    
    public void generarLista(){
        interfaz.addEjercicios(GestorBaseDatos.getEjercicios(((Entrenamiento)registro).getId()));
    }

    public Entrenamiento getEntrenamiento() {
        return (Entrenamiento)registro;
    }
}
