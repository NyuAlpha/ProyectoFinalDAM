/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import POJOs.Entrenamiento;
import POJOs.Serie;
import POJOs.Temporada;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



/**
 *
 * @author victo
 */
public class GUI extends javax.swing.JFrame implements MouseListener {

    //Los 4 posibles objetos seleccionables al mismo tiempo de la aplicación
    private PanelTemporada temporadaSeleccionada;
    private PanelEntrenamiento entrenamientoSeleccionado;
    private PanelEjercicio ejercicioSeleccionado;
    private PanelSerie serieSeleccionada;
    
    //Formularios de cada bloque
    private PanelFormularioTemporada formularioTemporada;
    private PanelFormularioEntrenamiento formularioEntrenamiento;
    private PanelFormularioEjercicio formularioEjercicio;
    private PanelFormularioSerie formularioSerie;
    
    /**
     * Creates new form GUI
     */
    public GUI() throws IOException {
        
        initComponents();
        crearPanelBusqueda();
        annadirTemporadas(GestorDatos.getTemporadas());
        JButton btnAnnadirTemporada = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        JButton btnAnnadirEntrenamiento = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        JButton btnAnnadirEjercicio = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        JButton btnAnnadirSerie = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        
        btnAnnadirTemporada.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               nuevaTemporada();
            }
        });
        btnAnnadirEntrenamiento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               nuevoEntrenamiento();
            }
        });
        btnAnnadirEjercicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               nuevoEjercicio();
            }
        });
        btnAnnadirSerie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               nuevaSerie();
            }
        });
        panelNaranja.add(Box.createRigidArea(new Dimension(0,10)));
        panelNaranja.add(btnAnnadirTemporada);
        panelVerde.add(Box.createRigidArea(new Dimension(0,10)));
        panelVerde.add(btnAnnadirEntrenamiento);
        panelRojo.add(Box.createRigidArea(new Dimension(0,10)));
        panelRojo.add(btnAnnadirEjercicio);
        panelAzul.add(Box.createRigidArea(new Dimension(0,10)));
        panelAzul.add(btnAnnadirSerie);


        //Se crean los paneles de formulario de temporada,entrenamiento,ejercicio y serie y se añaden al principio de su panel padre
        formularioTemporada = new PanelFormularioTemporada();
        panelVerde.add(formularioTemporada, 0);

        formularioEntrenamiento = new PanelFormularioEntrenamiento();
        panelRojo.add(formularioEntrenamiento, 0);
        
        formularioEjercicio = new PanelFormularioEjercicio();
        panelAzul.add(formularioEjercicio, 0);
        
        formularioSerie = new PanelFormularioSerie();
        panelAzul.add(formularioSerie);
        
        setVisible(true);
    }

    private void nuevaTemporada(){
        Temporada temporada = GestorDatos.nuevaTemporada(); 
        seleccionar( new PanelTemporada(temporada,this,this));
        panelListaTemporadas.add(temporadaSeleccionada);
        panelListaTemporadas.updateUI();
    }
    
    private void nuevoEntrenamiento(){
        Entrenamiento entrenamiento = GestorDatos.nuevoEntrenamiento(temporadaSeleccionada.getTemporada().getId());
        seleccionar(new PanelEntrenamiento(entrenamiento,this,this));
        panelListaEntrenamientos.add(entrenamientoSeleccionado);
        panelListaEntrenamientos.updateUI();
    }
    
    private void nuevoEjercicio(){
        Ejercicio ejercicio = GestorDatos.nuevoEjercicio(entrenamientoSeleccionado.getEntrenamiento().getId());
        seleccionar(new PanelEjercicio(ejercicio,this,this));
        panelListaEjercicios.add(ejercicioSeleccionado);
        panelListaEjercicios.updateUI();
    }
        
    private void nuevaSerie(){
        int numSerie = GestorDatos.countNumSeries(ejercicioSeleccionado.getEjercicio().getId()) + 1;
        Serie serie = GestorDatos.nuevaSerie(ejercicioSeleccionado.getEjercicio().getId(),numSerie);
        seleccionar(new PanelSerie(serie,this,this));
        panelListaSeries.add(serieSeleccionada);
        panelListaSeries.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedPane = new javax.swing.JTabbedPane();
        panelEdicion = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        panelNaranja = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelListaTemporadas = new javax.swing.JPanel();
        panelVerde = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelListaEntrenamientos = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        panelRojo = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelListaEjercicios = new javax.swing.JPanel();
        panelAzul = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelListaSeries = new javax.swing.JPanel();
        panelBusqueda = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1100, 700));
        setSize(new java.awt.Dimension(1100, 700));

        panelEdicion.setBackground(new java.awt.Color(102, 102, 102));

        jSplitPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createCompoundBorder()));
        jSplitPane1.setDividerLocation(350);
        jSplitPane1.setInheritsPopupMenu(true);

        jSplitPane2.setDividerLocation(200);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        panelNaranja.setBackground(new java.awt.Color(255, 153, 51));
        panelNaranja.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelNaranja.setLayout(new javax.swing.BoxLayout(panelNaranja, javax.swing.BoxLayout.Y_AXIS));

        jLabel2.setText("Temporadas");
        panelNaranja.add(jLabel2);

        panelListaTemporadas.setLayout(new javax.swing.BoxLayout(panelListaTemporadas, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(panelListaTemporadas);

        panelNaranja.add(jScrollPane1);

        jSplitPane2.setTopComponent(panelNaranja);

        panelVerde.setBackground(new java.awt.Color(153, 255, 153));
        panelVerde.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelVerde.setLayout(new javax.swing.BoxLayout(panelVerde, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setText("Entrenamientos");
        panelVerde.add(jLabel1);

        panelListaEntrenamientos.setLayout(new javax.swing.BoxLayout(panelListaEntrenamientos, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane3.setViewportView(panelListaEntrenamientos);

        panelVerde.add(jScrollPane3);

        jSplitPane2.setRightComponent(panelVerde);

        jSplitPane1.setLeftComponent(jSplitPane2);

        jSplitPane3.setDividerLocation(350);

        panelRojo.setBackground(new java.awt.Color(255, 102, 102));
        panelRojo.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRojo.setLayout(new javax.swing.BoxLayout(panelRojo, javax.swing.BoxLayout.Y_AXIS));

        jLabel6.setText("Ejercicios");
        panelRojo.add(jLabel6);

        panelListaEjercicios.setLayout(new javax.swing.BoxLayout(panelListaEjercicios, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(panelListaEjercicios);

        panelRojo.add(jScrollPane2);

        jSplitPane3.setLeftComponent(panelRojo);

        panelAzul.setBackground(new java.awt.Color(153, 255, 204));
        panelAzul.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelAzul.setLayout(new javax.swing.BoxLayout(panelAzul, javax.swing.BoxLayout.Y_AXIS));

        jLabel7.setText("Series");
        panelAzul.add(jLabel7);

        panelListaSeries.setLayout(new javax.swing.BoxLayout(panelListaSeries, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane4.setViewportView(panelListaSeries);

        panelAzul.add(jScrollPane4);

        jSplitPane3.setRightComponent(panelAzul);

        jSplitPane1.setRightComponent(jSplitPane3);

        javax.swing.GroupLayout panelEdicionLayout = new javax.swing.GroupLayout(panelEdicion);
        panelEdicion.setLayout(panelEdicionLayout);
        panelEdicionLayout.setHorizontalGroup(
            panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        panelEdicionLayout.setVerticalGroup(
            panelEdicionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEdicionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1))
        );

        TabbedPane.addTab("Editor", panelEdicion);

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );

        TabbedPane.addTab("Búsqueda avanzada", panelBusqueda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TabbedPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JPanel panelAzul;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JPanel panelEdicion;
    private javax.swing.JPanel panelListaEjercicios;
    private javax.swing.JPanel panelListaEntrenamientos;
    private javax.swing.JPanel panelListaSeries;
    private javax.swing.JPanel panelListaTemporadas;
    private javax.swing.JPanel panelNaranja;
    private javax.swing.JPanel panelRojo;
    private javax.swing.JPanel panelVerde;
    // End of variables declaration//GEN-END:variables

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
        seleccionar((PanelDatos)e.getComponent());
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((PanelDatos)e.getComponent()).activar();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((PanelDatos)e.getComponent()).desactivar();
    }
    
    //=======================================================
        public void seleccionar(PanelDatos panelDatos){
        if(panelDatos instanceof PanelTemporada){
            if(temporadaSeleccionada != null){
                temporadaSeleccionada.setSeleccionado(false);
                temporadaSeleccionada.desactivar();
            }

            temporadaSeleccionada = (PanelTemporada) panelDatos;
            formularioTemporada.recargarCampos(temporadaSeleccionada);
            
            clearEntrenamientos();
        }
        else if(panelDatos instanceof PanelEntrenamiento){
            if(entrenamientoSeleccionado != null){
                entrenamientoSeleccionado.setSeleccionado(false);
                entrenamientoSeleccionado.desactivar();
            }
            entrenamientoSeleccionado = (PanelEntrenamiento) panelDatos;
            formularioEntrenamiento.recargarCampos(entrenamientoSeleccionado);
            
            clearEjercicios();
        }
        else if(panelDatos instanceof PanelEjercicio){
            if(ejercicioSeleccionado != null){
                ejercicioSeleccionado.setSeleccionado(false);
                ejercicioSeleccionado.desactivar();
            }
            ejercicioSeleccionado = (PanelEjercicio) panelDatos;
            formularioEjercicio.recargarCampos(ejercicioSeleccionado);
            
            clearSeries();
        }
        else if(panelDatos instanceof PanelSerie){
            if(serieSeleccionada != null){
                serieSeleccionada.setSeleccionado(false);
                serieSeleccionada.desactivar();
            }
            serieSeleccionada = (PanelSerie) panelDatos;
            formularioSerie.recargarCampos(serieSeleccionada);
        }
        panelDatos.seleccionar();
        panelDatos.generarLista();
    }
    
    
    public void eliminarPanelTemporada(JPanel panel){
        panelListaTemporadas.remove(panel);
        panelListaTemporadas.updateUI();
        clearEntrenamientos();
    }
    
    public void eliminarPanelEntrenamiento(JPanel panel){
        panelListaEntrenamientos.remove(panel);
        panelListaEntrenamientos.updateUI();
        clearEjercicios();
    }
    
    
    public void eliminarPanelEjercicio(JPanel panel){
        panelListaEjercicios.remove(panel);
        panelListaEjercicios.updateUI();
        clearSeries();
    }
    
    public void eliminarPanelSerie(JPanel panel){
        panelListaSeries.remove(panel);
        panelListaSeries.updateUI();
    }
    
    //======================================================
    
    public void annadirTemporadas(ArrayList <Temporada> temporadas){
        panelListaTemporadas.removeAll();
        for(Temporada t : temporadas){
            panelListaTemporadas.add(new PanelTemporada(t,this,this));
        }
        panelListaEntrenamientos.updateUI();
    }
    
    public void annadirEntrenamientos(ArrayList <Entrenamiento> entrenamientos){
        
        panelListaEntrenamientos.removeAll();
        for(Entrenamiento ent : entrenamientos){
            panelListaEntrenamientos.add(new PanelEntrenamiento(ent,this,this));
        }
        panelListaEntrenamientos.updateUI();
    }
    
    public void annadirEjercicios(ArrayList <Ejercicio> ejercicios){
        
        panelListaEjercicios.removeAll();
        for(Ejercicio ej: ejercicios){
            panelListaEjercicios.add(new PanelEjercicio(ej,this,this));
        }
        panelListaEjercicios.updateUI();
    }
    
    public void annadirSeries(ArrayList <Serie> series){
        
        panelListaSeries.removeAll();
        for(Serie s : series){
            panelListaSeries.add(new PanelSerie(s,this,this));
        }
        panelListaSeries.updateUI();
    }

    
    //Metodos para limpiar en cascada las listas de los subcontenedores de cada entidad
    private void clearEntrenamientos(){
        panelListaEntrenamientos.removeAll();
        panelListaEntrenamientos.updateUI();
        clearEjercicios();
    }
    private void clearEjercicios(){
        panelListaEjercicios.removeAll();
        panelListaEjercicios.updateUI();
        clearSeries();
    }
    private void clearSeries(){
        panelListaSeries.removeAll();
        panelListaSeries.updateUI();
    }
    
    //Interfaz para el panel de búsqueda
    
    private void crearPanelBusqueda(){
        

        JTextArea textA = new JTextArea();
        JTextArea textB = new JTextArea();
        
        JLabel lblMostrar = new JLabel("Mostrar solo ");
        JLabel lblEjercicio = new JLabel("Ejercicio ");
        JLabel lblPesoCorporal = new JLabel("Peso corporal ");
        JLabel lblPeso = new JLabel("Peso ");
        JLabel lblRepeticiones = new JLabel("Repeticiones ");
        
        JComboBox cbMostrar = new JComboBox();
        cbMostrar.addItem("Entrenamientos");
        cbMostrar.addItem("Ejercicios");
        cbMostrar.addItem("Series");
          
        JComboBox cbEjercicio = new JComboBox();
        cbEjercicio.setEditable(true);
        for(String s : GestorDatos.getListaEjercicios()){
            cbEjercicio.addItem(s);
        }
        
        JTextField txtPesoCorporal = new JTextField();
        JTextField txtPeso = new JTextField();
        JTextField txtRepeticiones = new JTextField();
        
        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Object> lista = GestorDatos.busqueda(cbMostrar.getSelectedItem().toString(),cbEjercicio.getSelectedItem().toString());
                for(Object o : lista){
                    textA.setText(o.toString());
                }
            }
        });
        
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda,BoxLayout.Y_AXIS));
        
        GroupLayout layoutBusqueda = new GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(layoutBusqueda);
        
        layoutBusqueda.setAutoCreateContainerGaps(true);
        layoutBusqueda.setAutoCreateGaps(true);
        
        layoutBusqueda.setHorizontalGroup(layoutBusqueda.createSequentialGroup()
            .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(lblMostrar)
                    .addComponent(cbMostrar,GroupLayout.PREFERRED_SIZE,140,GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(lblEjercicio)
                    .addComponent(cbEjercicio,GroupLayout.PREFERRED_SIZE,140,GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(lblPesoCorporal)
                    .addComponent(txtPesoCorporal,GroupLayout.PREFERRED_SIZE,140,GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(lblPeso)
                    .addComponent(txtPeso,GroupLayout.PREFERRED_SIZE,140,GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(lblRepeticiones)
                    .addComponent(txtRepeticiones,GroupLayout.PREFERRED_SIZE,140,GroupLayout.PREFERRED_SIZE)
                )
                .addComponent(btnFiltrar)
            )
            .addComponent(textA)
            .addComponent(textB)
        );
        layoutBusqueda.setVerticalGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layoutBusqueda.createSequentialGroup()
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblMostrar)
                    .addComponent(cbMostrar,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblEjercicio)
                    .addComponent(cbEjercicio,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )  
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblPesoCorporal)
                    .addComponent(txtPesoCorporal,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                ) 
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblPeso)
                    .addComponent(txtPeso,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )                     
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblRepeticiones)
                    .addComponent(txtRepeticiones,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )
                .addComponent(btnFiltrar)
            )
            .addComponent(textA)
            .addComponent(textB)
        );
    }
}
