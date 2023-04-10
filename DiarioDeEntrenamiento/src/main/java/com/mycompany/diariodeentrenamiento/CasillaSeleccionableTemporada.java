package com.mycompany.diariodeentrenamiento;

import com.mycompany.POJOs.Temporada;
import java.awt.event.MouseListener;
 
/**
 *  Clase para representar los registros de temporadas de la app
 */
public class CasillaSeleccionableTemporada extends CasillaSeleccionable {
    
    //Constructor. Inicia la casilla con su texto correspondiente
    public CasillaSeleccionableTemporada(Temporada temporada,MouseListener listener,PanelEdicion interfaz){
        
        super(listener,interfaz,temporada);
        generarTexto();
    }
    
    public Temporada getTemporada() {
        return (Temporada)registro;
    }
    
    //Devuelve true si el parametro temporada es el mismo que la temporada almacenada en la casilla
    public boolean equals (Temporada t){
        return ((Temporada)registro).getId() == t.getId();
    }

    /****************************
    *   MËTODOS DE SUPERCLASE
    *****************************/
    
    //Elimina el registro y este panel de la interfaz    
    public void eliminar(){
         GestorBaseDatos.eliminarTemporada((Temporada)registro);
         interfaz.eliminarCasillaTemporada(this);
    }
    
    //Genera la lista de entrenamientos de esta temporada
    public void generarLista(){
        interfaz.addEntrenamientos(GestorBaseDatos.getEntrenamientos(((Temporada)registro).getId()));
    }
    
    //Este método es llamado solo cuando se selecciona la casilla con el mouse (evento de mouse)
    public void clicar(){
        interfaz.seleccionarTemporada(this);
    }
}
