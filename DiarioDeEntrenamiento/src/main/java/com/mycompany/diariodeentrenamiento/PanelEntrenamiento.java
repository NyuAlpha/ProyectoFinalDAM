package com.mycompany.diariodeentrenamiento;

import POJOs.Entrenamiento;
import java.awt.event.MouseListener;

/**
 *
 * @author victo
 */
public class PanelEntrenamiento extends PanelDatos {
    
    public PanelEntrenamiento(Entrenamiento entrenamiento,MouseListener listener,GUI interfaz){
        
        super(listener,interfaz,entrenamiento);    
        generarTexto();
    }
    
    public void eliminar(){
         GestorDatos.eliminarEntrenamiento((Entrenamiento)entidad);
         interfaz.eliminarPanelEntrenamiento(this);
    }
    
    public void generarLista(){
        interfaz.annadirEjercicios(GestorDatos.getEjercicios(((Entrenamiento)entidad).getId()));
    }

    public Entrenamiento getEntrenamiento() {
        return (Entrenamiento)entidad;
    }
}
