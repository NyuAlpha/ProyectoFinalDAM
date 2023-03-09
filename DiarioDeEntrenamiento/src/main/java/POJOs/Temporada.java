
package POJOs;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author victo
 */
public class Temporada implements Serializable{
    private int id;
    private Date fechaInicio;
    private Date fechaFin;
    private String descripcion;

    public Temporada(Date fechaInicio, Date fechaFin, String descripcion) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.descripcion = descripcion;
    }

    public Temporada() {
    }

    public int getId() {
        return id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String toString(){
        String fInicio = (fechaInicio == null)? "fecha desconocida":fechaInicio.toString();
        String fFin = (fechaFin == null)? "fecha desconocida":fechaFin.toString();
        return fInicio +"  ->  "+ fFin +":  "+ descripcion;
    }
}
