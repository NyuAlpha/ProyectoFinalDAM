package com.mycompany.diariodeentrenamiento;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victo
 */
public class DiarioDeEntrenamiento {


    GUI interfazUsuario;
    
    
    public DiarioDeEntrenamiento(){
        try {
            interfazUsuario = new GUI();
        } catch (IOException ex) {
            Logger.getLogger(DiarioDeEntrenamiento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        new DiarioDeEntrenamiento();
    }
}
