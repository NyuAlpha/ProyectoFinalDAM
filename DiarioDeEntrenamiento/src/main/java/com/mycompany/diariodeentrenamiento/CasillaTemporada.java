package com.mycompany.diariodeentrenamiento;

import POJOs.Temporada;
import java.awt.event.MouseListener;
 
/**
 *
 * @author victo
 */
public class CasillaTemporada extends Casilla {
    
    public CasillaTemporada(Temporada temporada,MouseListener listener,PanelEdicion interfaz){
        
        super(listener,interfaz,temporada);
        generarTexto();
    }
    
    public void eliminar(){
         GestorBaseDatos.eliminarTemporada((Temporada)registro);
         interfaz.eliminarPanelTemporada(this);
    }
    
    public void generarLista(){
        interfaz.addEntrenamientos(GestorBaseDatos.getEntrenamientos(((Temporada)registro).getId()));
    }

    public Temporada getTemporada() {
        return (Temporada)registro;
    }
    
    public boolean equals (Temporada t){
        return ((Temporada)registro).getId() == t.getId();
    }

    public void clicar(){
        interfaz.seleccionarTemporada(this);
    }
}
