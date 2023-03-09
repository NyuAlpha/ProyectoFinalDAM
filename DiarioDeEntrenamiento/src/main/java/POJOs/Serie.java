package POJOs;

import java.io.Serializable;

/**
 *
 * @author victo
 */
public class Serie implements Serializable{
    private int numSerie;
    private int idEjercicio;
    private float peso;
    private int repeticiones;
    private String extra;

    public Serie(int numSerie, int idEjercicio, float peso, int repeticiones, String extra) {
        this.numSerie = numSerie;
        this.idEjercicio = idEjercicio;
        this.peso = peso;
        this.repeticiones = repeticiones;
        this.extra = extra;
    }

    public Serie() {
    }

    public int getNumSerie() {
        return numSerie;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public float getPeso() {
        return peso;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public String getExtra() {
        return extra;
    }

    public void setNumSerie(int numSerie) {
        this.numSerie = numSerie;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
    
    public String toString(){
        return numSerie + ") " + (int)peso + "kg x " + repeticiones + " : " + extra;
    }
}
