package com.mycompany.diariodeentrenamiento;

import com.mycompany.POJOs.Entidad;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
*   Esta clase abstracta representa cualquier casilla de las lístas de registros seleccionables en la app
*/
public abstract class CasillaSeleccionable extends JPanel{
        
    PanelEdicion interfaz;//La interfaz a la que pertenece
    //Subcomponentes de la casilla
    JButton btnEliminar;
    JLabel lblTexto;
    
    boolean seleccionado;//Indicador de si ha sido seleccionado o no.
    
    //El registro que contiene
    protected Entidad registro;
    
    //Constructor de la casilla, se crean las dimensiones y caracteristicas de este componente
    public CasillaSeleccionable(MouseListener mouseListener,PanelEdicion interfaz,Entidad registro){
        
        this.interfaz = interfaz;
        this.registro = registro;
        seleccionado = false;//Una casilla no está seleccionada por defecto
        
        //Características estéticas de la casilla
        this.setBackground(new Color(214,214,224));
        this.setMaximumSize(new Dimension (1000,25));
        setBorder(BorderFactory.createEtchedBorder());
        
        //Esta casilla tendrá eventos de mouse
        this.addMouseListener(mouseListener);
        
        //Características del botón de eliminar
        btnEliminar = new JButton(new ImageIcon(getClass().getResource("/iconos/delete.png")));
        btnEliminar.setBorder(BorderFactory.createEtchedBorder());
        btnEliminar.setToolTipText("Eliminar");
        
        //La etiquetá tendrá letras negras
        lblTexto = new JLabel();
        lblTexto.setForeground(Color.BLACK);
        
        //Evento del botón, eliminar este registro
        btnEliminar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "¿Está segur@ de que desea eliminar este elemento para siempre?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ){
                    eliminar();
                }
            }
        });
        
        //Diseño de este componente
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.EAST,btnEliminar);
        this.add(BorderLayout.WEST,lblTexto);
    }
    

    //Cambia el aspecto de la casilla a activado
    public void activar(){
        if(!seleccionado){
            this.setBackground(Color.LIGHT_GRAY);
            lblTexto.setForeground(Color.BLUE);
            setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY,Color.GRAY));
        }
    }
    
    //devuelve el aspecto de la casilla al aspecto por defecto solo si no estaba seleccioanda
    public void desactivar(){
        if(!seleccionado){
            this.setBackground(new Color(214,214,224));
            lblTexto.setForeground(Color.BLACK);
            setBorder(BorderFactory.createEtchedBorder());
        }
    }
    
    //Selecciona esta casilla y cambia su aspecto
    public void seleccionar(){
        seleccionado = true;
        this.setBackground(new Color(160,160,170));
        lblTexto.setForeground(Color.BLUE);
    }
    
    //Deselecciona esta casilla y devuelve su aspecto original
    public void deseleccionar(){
        seleccionado = false;
        this.setBackground(new Color(214,214,224));
        lblTexto.setForeground(Color.BLACK);
        setBorder(BorderFactory.createEtchedBorder());
    }
    
    //Genera el texto que contiene la etiqueta, depende del tipo de registro
    protected void generarTexto(){
        String txt = registro.toString();
        int max = 50;
        //Si es demasiado largo cortá el texto
        if(txt.length() > max){
            txt = txt.substring(0, max);
            txt += "..";
        }
        lblTexto.setText(txt);
    }

    //true si este componente esta seleccionado
    public boolean isSeleccionado() {
        return seleccionado;
    }
    
    //Métodos abstractos a implementar en quienes heredan esta clase abstracta
    public abstract void eliminar();
    public abstract void generarLista();
    public abstract void clicar();
    
    
}
