package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import POJOs.Entrenamiento;
import POJOs.Serie;
import POJOs.Temporada;
import java.io.File;
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
    
    public static void introPrueba() {
		
        Session sesion = GestorBaseDatos.getCurrentSessionFromConfig();
        Transaction tran = sesion.beginTransaction();
        Temporada temporada = new Temporada (new java.sql.Date(2000,2,14),new java.sql.Date(2069,2,14) , "alguien ha envenenido el abrevadero");
        sesion.save(temporada);
        tran.commit();
        sesion.close();

	}
	
    public static Session getCurrentSessionFromConfig() {
        SessionFactory sessionFactory  = new Configuration().configure(new File("C:\\Users\\victo\\OneDrive\\Escritorio\\DiarioDeEntrenamiento\\src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        Session session = sessionFactory.openSession();
        sessionFactory.close();
        return session;
      }
    
    private static Session iniciarSession(){
        SessionFactory sessionFactory  = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
    
        public static void annadirSerie(Serie serie){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        session.save(serie);
        tran.commit();
        session.close();
        //sessionFactory.close();
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
        ArrayList<Temporada> temporadas = (ArrayList<Temporada>)session.createQuery("from Temporada").list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return temporadas;
    }
    
    public static ArrayList<Entrenamiento> getEntrenamientos(int idTemporada){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Entrenamiento> entrenamientos = (ArrayList<Entrenamiento>)session.createQuery("from Entrenamiento where idTemporada = " + idTemporada).list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return entrenamientos;
    }
    
    public static ArrayList<Ejercicio> getEjercicios(int idEntreno){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Ejercicio> ejercicios = (ArrayList<Ejercicio>)session.createQuery("from Ejercicio where idEntrenamiento = " + idEntreno).list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return ejercicios;
    }
    
    public static ArrayList<Serie> getSeries(int idEjercicio){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<Serie> series = (ArrayList<Serie>)session.createQuery("from Serie where idEjercicio = " + idEjercicio).list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return series;
    }   
    
    public static ArrayList<String> getListaEjercicios(){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<String> listaEjercicios = (ArrayList<String>)session.createQuery("select distinct nombre from Ejercicio" ).list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return listaEjercicios;
    }
    
    public static ArrayList<String> getListaTemporadas(){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        ArrayList<String> listaTemporadas = (ArrayList<String>)session.createQuery("select distinct descripcion from Temporada" ).list();
        tran.commit();
        session.close();
        //sessionFactory.close();
        return listaTemporadas;
    }
    
    public static  ArrayList<Ejercicio> getEjercicios(String ejercicio){
        Session session = iniciarSession();
        Transaction tran = session.beginTransaction();
        String s = (ejercicio.equals("Todos"))? "":" where nombre like '" + ejercicio + "'";
        ArrayList<Ejercicio> listaEjercicios = null;
        listaEjercicios= (ArrayList<Ejercicio>)session.createQuery(" from Ejercicio" + s +" order by idEntrenamiento").list();
        tran.commit();
        session.close();
        return listaEjercicios;
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

}
