package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import POJOs.Entrenamiento;
import POJOs.Serie;
import POJOs.Temporada;
import java.sql.Date;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author victo
 */
public class GestorBaseDatos {
    
    public GestorBaseDatos(){
        
    }
    
    private static Session iniciarSession(){
        SessionFactory sessionFactory  = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        //sessionFactory.close();
        return sessionFactory.openSession();
    }
    
    public static void nuevaTemporada(Temporada temporada){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.save(temporada);
        tran.commit();
        session.close();
    }
    
    public static void nuevoEntrenamiento(Entrenamiento entrenamiento){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.save(entrenamiento);
        tran.commit();
        session.close();
    }
    
    public static void nuevoEjercicio(Ejercicio ejercicio){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.save(ejercicio);
        tran.commit();
        session.close();
    }
        
    public static void nuevaSerie(Serie serie){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        int numSerie = (int)(long)session.createQuery("select count(*) from Serie where idEjercicio = " + serie.getIdEjercicio()).uniqueResult() + 1;
        serie.setNumSerie(numSerie);
        session.save(serie);
        tran.commit();
        session.close();
    }
    
    public static void actualizarTemporada(Temporada temporada){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.update(temporada);
        tran.commit();
        session.close();
    }
    
    public static void actualizarEntrenamiento(Entrenamiento entrenamiento){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.update(entrenamiento);
        tran.commit();
        session.close();
    }
    
    public static void actualizarEjercicio(Ejercicio ejercicio){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.update(ejercicio);
        tran.commit();
        session.close();
    }
    
    public static void actualizarSerie(Serie serie){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.update(serie);
        tran.commit();
        session.close();
    }
    
    public static void eliminarTemporada(Temporada temporada){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.delete(temporada);
        tran.commit();
        session.close();
    }
    
    public static void eliminarEntrenamiento(Entrenamiento entrenamiento){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.delete(entrenamiento);
        tran.commit();
        session.close();
    }
    
    public static void eliminarEjercicio(Ejercicio ejercicio){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.delete(ejercicio);
        tran.commit();
        session.close();
    }
    
    public static void eliminarSerie(Serie serie){
        
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        //Eliminar series es un caso especial ya que poseen un orden como primary key y hay que reestructurarlas
        //ejemplo: si se elimina la serie 5, la serie 6 pasa a ser la 5, la 7 la 6 ,etc.
        int i = serie.getNumSerie();
        session.delete(serie);
        session.createQuery("update Serie S set numSerie = S.numSerie - 1  where S.numSerie > " + i 
                + " and S.idEjercicio = " + serie.getIdEjercicio()).executeUpdate();
        tran.commit();
        session.close();
    }
    
    public static ArrayList<Temporada> getTemporadas(){
        
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Temporada> temporadas = (ArrayList<Temporada>)session.createQuery("from Temporada order by fechaInicio desc").list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return temporadas;
    }
    
    public static ArrayList<Entrenamiento> getEntrenamientos(int idTemporada){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Entrenamiento> entrenamientos = (ArrayList<Entrenamiento>)session.createQuery("from Entrenamiento where idTemporada = " 
                + idTemporada + "order by fecha desc").list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return entrenamientos;
    }
    
    public static ArrayList<Ejercicio> getEjercicios(int idEntreno){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Ejercicio> ejercicios = (ArrayList<Ejercicio>)session.createQuery("from Ejercicio where idEntrenamiento = " 
                + idEntreno + "order by orden").list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return ejercicios;
    }
    
    public static ArrayList<Ejercicio> getEjercicios(String ejercicio){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        String s = (ejercicio.equals("Todos"))? "":" where nombre like '" + ejercicio + "'";
        ArrayList<Ejercicio> listaEjercicios = null;
        listaEjercicios= (ArrayList<Ejercicio>)session.createQuery(" from Ejercicio" + s +" order by idEntrenamiento").list();
        tran.commit();
        session.close();
        return listaEjercicios;
    }
    
    public static ArrayList<Serie> getSeries(int idEjercicio){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Serie> series = (ArrayList<Serie>)session.createQuery("from Serie where idEjercicio = " 
                + idEjercicio + "order by numSerie").list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return series;
    }   
    
    public static ArrayList<String> getNombreEjercicios(){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<String> listaEjercicios = (ArrayList<String>)session.createQuery("select distinct nombre from Ejercicio" ).list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return listaEjercicios;
    }
    
    public static ArrayList<String> getNombreTemporadas(){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<String> listaTemporadas = (ArrayList<String>)session.createQuery("select distinct descripcion from Temporada" ).list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return listaTemporadas;
    }
    
    public static ArrayList<Ejercicio> getConsulta(String query ){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Ejercicio> ejercicios = null;
        ejercicios= (ArrayList<Ejercicio>)session.createQuery(query).list();
        for(Ejercicio e : ejercicios){
            e.setSeries((ArrayList<Serie>)session.createQuery(" from Serie where idEjercicio = " + e.getId()).list());
        }
        tran.commit();
        session.close();
        return ejercicios;
    }
    
    public static Temporada getTemporada(int idTemporada){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        Temporada temporada = (Temporada)session.createQuery("from Temporada where id = " + idTemporada).list().get(0);
        tran.commit();
        session.close();
        return temporada;
    }
    
    public static Entrenamiento getEntrenamiento(int idEntrenamiento){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        Entrenamiento entrenamiento = (Entrenamiento)session.createQuery("from Entrenamiento where id = " + idEntrenamiento).list().get(0);
        tran.commit();
        session.close();
        return entrenamiento;
    }
    
    public static Date getFechaMasReciente(int idTemporada){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        Date fecha = (Date)session.createQuery("select max(fecha) from Entrenamiento where idTemporada = " + idTemporada).uniqueResult();
        tran.commit();
        session.close();
        return fecha;
    }

}
