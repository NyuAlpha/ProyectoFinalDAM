/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diariodeentrenamiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author victo
 */
public abstract class PanelDatos extends JPanel{
        
    GUI interfaz;
    JButton btnEliminar;
    JLabel texto;
    boolean seleccionado;
    protected Object entidad;
    
    public PanelDatos(MouseListener listener,GUI interfaz,Object entidad){
        
        this.interfaz = interfaz;
        this.entidad = entidad;
        seleccionado = false;
        
        this.setBackground(new Color(214,214,224));
        this.setMaximumSize(new Dimension (1000,25));
        
        this.addMouseListener(listener);
        btnEliminar = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\delete.png"));
        btnEliminar.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
        
        texto = new JLabel();
        
        btnEliminar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
               eliminar();
            }
        });
        
        this.setLayout(new BorderLayout());
        
        this.add(BorderLayout.EAST,btnEliminar);
        this.add(BorderLayout.WEST,texto);
        

    }
    
    public abstract void eliminar();
    public abstract void generarLista();
    
    public void activar(){
        if(!seleccionado){
            this.setBackground(Color.LIGHT_GRAY);
            texto.setForeground(Color.BLUE);           
        }
    }
    
    public void desactivar(){
        if(!seleccionado){
            this.setBackground(new Color(214,214,224));
            texto.setForeground(Color.BLACK);
        }
    }
    
    public void seleccionar(){
        seleccionado = true;
        this.setBackground(new Color(160,160,170));
        texto.setForeground(Color.BLUE);
    }
    
    public void setSeleccionado(boolean seleccionado){
        this.seleccionado = seleccionado;
    }
    
    protected void generarTexto(){
        String txt = entidad.toString();
        int max = 50;
        if(txt.length() > max){
            txt = txt.substring(0, max);
            txt += "..";
        }
        texto.setText(txt);
    }
}
