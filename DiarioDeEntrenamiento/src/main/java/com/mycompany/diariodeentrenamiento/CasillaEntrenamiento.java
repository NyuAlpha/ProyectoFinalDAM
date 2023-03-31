package com.mycompany.diariodeentrenamiento;

import POJOs.Entrenamiento;
import POJOs.Temporada;
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
    
    
    public boolean equals (Entrenamiento e){
        return ((Entrenamiento)registro).getId() == e.getId();
    }
    
    public void clicar(){
        interfaz.seleccionarEntrenamiento(this);
    }
}
