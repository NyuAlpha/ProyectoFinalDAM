package com.mycompany.diariodeentrenamiento;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author victo
 */
public class VentanaPrincipal extends JFrame {
    
    private JTabbedPane tabbedPane; 
    private PanelBusqueda panelBusqueda;
    private PanelEdicion panelEdicion;
    
    
    public static void main(String[] args){
        new VentanaPrincipal();
    }
    
    
    public VentanaPrincipal(){    
        
        //Inicia los paneles y los ubica en su correspondiente lugar
        panelBusqueda = new PanelBusqueda();
        panelEdicion = new PanelEdicion();
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        
        tabbedPane.addTab("Editor ",new ImageIcon(".\\src\\main\\java\\iconos\\edit.png"), panelEdicion);
        tabbedPane.addTab("Buscador ",new ImageIcon(".\\src\\main\\java\\iconos\\search.png"), panelBusqueda);
        add(tabbedPane);
        
        //Añade un evento al cambiar al panel de búsqueda para que los campos de sus comboBox se actualicen
        tabbedPane.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tp = (JTabbedPane)e.getSource();
                if(tp.getSelectedComponent() == panelBusqueda)
                    panelBusqueda.actualizar();        
            }
        });
        
        //Establece las características de esta ventana
        setSize(1230,800);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
   
}
