package com.mycompany.diariodeentrenamiento;

import com.mycompany.POJOs.Entrenamiento;
import java.awt.event.MouseListener;

/**
 *  Clase para representar los registros de entrenamientos de la app
 */
public class CasillaSeleccionableEntrenamiento extends CasillaSeleccionable {
    
    //Constructor. Inicia la casilla con su texto correspondiente
    public CasillaSeleccionableEntrenamiento(Entrenamiento entrenamiento,MouseListener listener,PanelEdicion interfaz){
        
        super(listener,interfaz,entrenamiento);    
        generarTexto();
    }   

    public Entrenamiento getEntrenamiento() {
        return (Entrenamiento)registro;
    }
    
    //Devuelve true si el parametro Entrenamiento es el mismo que el entrenamiento almacenado en la casilla
    public boolean equals (Entrenamiento e){
        return ((Entrenamiento)registro).getId() == e.getId();
    }
    
    /****************************
    *   MËTODOS DE SUPERCLASE
    *****************************/
    
    //Elimina el registro y este panel de la interfaz
    public void eliminar(){
         GestorBaseDatos.eliminarEntrenamiento((Entrenamiento)registro);
         interfaz.eliminarPanelEntrenamiento(this);
    }
    
    //Genera la lista de ejercicios de este entrenamiento
    public void generarLista(){
        interfaz.addEjercicios(GestorBaseDatos.getEjercicios(((Entrenamiento)registro).getId()));
    }
    
    //Este método es llamado solo cuando se selecciona la casilla con el mouse (evento de mouse)
    public void clicar(){
        interfaz.seleccionarEntrenamiento(this);
    } 
}
