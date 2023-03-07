package POJOs;

import java.io.Serializable;

/**
 *
 * @author victo
 */
public class Ejercicio implements Serializable{
    private int id;
    private int idEntrenamiento;
    private String nombre;
    private String variante; 
    private int orden;
    private String descripcion;

    public Ejercicio(int idEntrenamiento, String nombre, String variante, int orden, String descripcion) {
        this.idEntrenamiento = idEntrenamiento;
        this.nombre = nombre;
        this.variante = variante;
        this.orden = orden;
        this.descripcion = descripcion;
    }

    public Ejercicio() {
    }

    public int getId() {
        return id;
    }

    public int getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getVariante() {
        return variante;
    }

    public int getOrden() {
        return orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdEntrenamiento(int idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVariante(String variante) {
        this.variante = variante;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String toString(){
        return orden +"."+ nombre +" "+ variante + ": " + descripcion;
    }
}
