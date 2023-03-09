package POJOs;

import java.io.Serializable;
import java.sql.Date;
/**
 *
 * @author victo
 */
public class Entrenamiento implements Serializable {
    
    private int id;
    private int idTemporada;
    private Date fecha;
    float pesoCorporal;
    String descripcion;

    public Entrenamiento(int idTemporada, Date fecha, float pesoCorporal, String descripcion) {
        this.idTemporada = idTemporada;
        this.fecha = fecha;
        this.pesoCorporal = pesoCorporal;
        this.descripcion = descripcion;
    }

    public Entrenamiento() {
    }

    public int getId() {
        return id;
    }

    public int getIdTemporada() {
        return idTemporada;
    }

    public Date getFecha() {
        return fecha;
    }

    public float getPesoCorporal() {
        return pesoCorporal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTemporada(int id_temporada) {
        this.idTemporada = id_temporada;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setPesoCorporal(float pesoCorporal) {
        this.pesoCorporal = pesoCorporal;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String toString(){
        String f = (fecha == null)? "Fecha desconocida":fecha.toString();
        String d = (descripcion == null)? "":descripcion;
        return f + " ->  Peso corporal " +  (int)pesoCorporal + " kg    " + descripcion;
    }
    
}