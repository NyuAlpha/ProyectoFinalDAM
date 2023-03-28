package POJOs;

import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<Serie> series;

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

    public ArrayList<Serie> getSeries() {
        return series;
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

    public void setSeries(ArrayList<Serie> series) {
        this.series = series;
    }
    
    public String resumenSeries(){
        
        StringBuilder sb = new StringBuilder("");
        int peso = -1;
        int reps = 0;
        int n = 1;
        String r = "";
        for(Serie s : series){
            //Si es la primera serie
            if(peso == -1){
                peso = (int)s.getPeso();
                reps = s.getRepeticiones();
                sb.append(" " + peso + "kg(");
            }
            //a partir de la segunda serie
            else{         
                //Si el peso cambia se escribe la/s serie/s para ese peso
                if(peso != (int)s.getPeso()){
                    peso = (int)s.getPeso();
                    r = (n==1)? "": (n+"x");
                    sb.append(r + reps + ")," + peso + "kg(");
                    reps = s.getRepeticiones();
                    n = 1;
                }
                //Si el peso no varia entonces se comprueban las repeticiones
                else{
                    //Si las repeticiones han variado se escriben y se resetean
                    if(reps != s.getRepeticiones()){
                        r = (n==1)? "": (n+"x");
                        sb.append(r + reps + ",");
                        reps = s.getRepeticiones();
                        n = 1;
                    }
                    else{
                        n++;
                    }
                }
            }
        }
        r = (n==1)? "": (n+"x");
        sb.append(r + reps + ")");
        
        for(Serie s : series){
            String ex = (s.getExtra() == null || s.getExtra().isEmpty())?  "" : ("(" +s.getNumSerie() +": " +  s.getExtra() + ")");
            sb.append(ex);
        }
        return sb.toString();
    }
    
    public String toString(){
        String n = (nombre == null || nombre.isEmpty())? "Sin nombrar ": (nombre + " ");
        String v = (variante == null)? "":variante;
        String d = (descripcion == null || descripcion.isEmpty())? "": (": " + descripcion);
        return n + v + d;
    }
}
