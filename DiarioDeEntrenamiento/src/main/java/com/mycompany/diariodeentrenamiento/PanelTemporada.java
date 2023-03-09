/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diariodeentrenamiento;

import POJOs.Temporada;
import java.awt.event.MouseListener;
 
/**
 *
 * @author victo
 */
public class PanelTemporada extends PanelDatos {
    
    public PanelTemporada(Temporada temporada,MouseListener listener,InterfazUsuario interfaz){
        
        super(listener,interfaz,temporada);
        generarTexto();
    }
    
    public void eliminar(){
         GestorBaseDatos.eliminarTemporada((Temporada)entidad);
         interfaz.eliminarPanelTemporada(this);
    }
    
    public void generarLista(){
        interfaz.annadirEntrenamientos(GestorBaseDatos.getEntrenamientos(((Temporada)entidad).getId()));
    }

    public Temporada getTemporada() {
        return (Temporada)entidad;
    }

    
}
