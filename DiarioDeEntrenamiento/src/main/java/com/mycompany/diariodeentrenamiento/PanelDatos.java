/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diariodeentrenamiento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author victo
 */
public abstract class PanelDatos extends JPanel{
        
    InterfazUsuario interfaz;
    JButton btnEliminar;
    JLabel texto;
    boolean seleccionado;
    protected Object entidad;
    
    public PanelDatos(MouseListener listener,InterfazUsuario interfaz,Object entidad){
        
        this.interfaz = interfaz;
        this.entidad = entidad;
        seleccionado = false;
        
        this.setBackground(new Color(214,214,224));
        this.setMaximumSize(new Dimension (1000,25));
        setBorder(BorderFactory.createEtchedBorder());
        
        this.addMouseListener(listener);
        btnEliminar = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\delete.png"));
        //Image im = ((ImageIcon)btnEliminar.getIcon()).getImage();
        //((ImageIcon)btnEliminar.getIcon()).setImage(im.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
        btnEliminar.setBorder(BorderFactory.createEtchedBorder());
        btnEliminar.setToolTipText("Eliminar");
        
        texto = new JLabel();
        
        btnEliminar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "¿Está segur@ de que desea eliminar este elemento para siempre?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ){
                    eliminar();
                    JOptionPane.showMessageDialog(null,"¡Eliminado con exito!");
                }
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
            setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY,Color.GRAY));
        }
    }
    
    public void desactivar(){
        if(!seleccionado){
            this.setBackground(new Color(214,214,224));
            texto.setForeground(Color.BLACK);
            setBorder(BorderFactory.createEtchedBorder());
        }
    }
    
    public void seleccionar(){
        
        if(!seleccionado){
            seleccionado = true;
            this.setBackground(new Color(160,160,170));
            texto.setForeground(Color.BLUE);
        }
        else{
            seleccionado = false;
            this.setBackground(new Color(214,214,224));
            texto.setForeground(Color.BLACK);
            setBorder(BorderFactory.createEtchedBorder());
        }
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

    public boolean isSeleccionado() {
        return seleccionado;
    }
    
    
}
