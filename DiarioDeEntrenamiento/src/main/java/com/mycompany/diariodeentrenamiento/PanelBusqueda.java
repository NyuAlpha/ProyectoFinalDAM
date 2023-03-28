
package com.mycompany.diariodeentrenamiento;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author victo
 * Esta clase es la interfaz gráfica de usuario del panel de búsqueda de la aplicación
 */
public class PanelBusqueda extends JPanel {
    

    private JTextArea textA;
    private JTextArea textB;
    private JLabel lblFechaMinima;
    private JLabel lblFechaMaxima;
    private JLabel lblEjercicio;
    private JLabel lblTemporada;
    private JLabel lblPesoCorporalMinimo;
    private JLabel lblPesoCorporalMaximo;
    private JLabel lblPesoMinimo;
    private JLabel lblPesoMaximo;

    private JComboBox cbEjercicio;
    private JComboBox cbTemporada;
    private JTextField txtFechaMinima;
    private JTextField txtFechaMaxima;
    private JTextField txtPesoCorporalMinimo;
    private JTextField txtPesoCorporalMaximo;
    private JTextField txtPesoMinimo;
    private JTextField txtPesoMaximo;
    private JButton btnFiltrar;
    private JButton btnTransferir;
    
    private JScrollPane scrollPaneA;
    private JScrollPane scrollPaneB;

    
    public PanelBusqueda (){
        
        setBackground(new Color(250, 253, 138));
        textA = new JTextArea();
        textB = new JTextArea();
        lblFechaMinima = new JLabel("Después de  ");
        lblFechaMaxima = new JLabel("Antes de  ");
        lblEjercicio = new JLabel("Ejercicio ");
        lblTemporada = new JLabel("Temporada ");
        lblPesoCorporalMinimo = new JLabel("Peso corporal mínimo ");
        lblPesoCorporalMaximo = new JLabel("Peso corporal máximo ");
        lblPesoMinimo = new JLabel("Peso levantado mínimo ");
        lblPesoMaximo = new JLabel("Peso levantado máximo ");

        cbEjercicio = new JComboBox();
        cbTemporada = new JComboBox();
        txtFechaMinima = new JTextField();
        txtFechaMaxima = new JTextField();
        txtPesoCorporalMinimo = new JTextField();
        txtPesoCorporalMaximo = new JTextField();
        txtPesoMinimo = new JTextField();
        txtPesoMaximo = new JTextField();
        btnFiltrar = new JButton("Filtrar");
        btnTransferir = new JButton("-----Transferir---->");
        
        scrollPaneA = new JScrollPane();
        scrollPaneB = new JScrollPane();
        
        scrollPaneA.setViewportView(textA);
        scrollPaneB.setViewportView(textB);
        scrollPaneA.getVerticalScrollBar().setUnitIncrement(10);
        scrollPaneB.getVerticalScrollBar().setUnitIncrement(10);
        scrollPaneA.setBackground(new Color(250, 162, 57));
        scrollPaneB.setBackground(new Color(250, 162, 57));
        scrollPaneA.setBorder(new EmptyBorder(5,5,5,5));
        scrollPaneB.setBorder(new EmptyBorder(5,5,5,5));
        
        textA.setFont(new Font(Font.DIALOG,Font.BOLD,12));
        textB.setFont(new Font(Font.DIALOG,Font.BOLD,12));
                
        actualizarComboBoxEjercicio();
        actualizarComboBoxEjercicio();
        


        btnFiltrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        
        btnTransferir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                textB.setText(textA.getText());
                textA.setText("");
            }
        });
        
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        
        GroupLayout layoutBusqueda = new GroupLayout(this);
        setLayout(layoutBusqueda);
        
        layoutBusqueda.setAutoCreateContainerGaps(true);
        layoutBusqueda.setAutoCreateGaps(true);
        
        layoutBusqueda.setHorizontalGroup(layoutBusqueda.createSequentialGroup()
            .addGroup(layoutBusqueda.createSequentialGroup()
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                     .addComponent(lblFechaMinima)
                     .addComponent(lblFechaMaxima)
                     .addComponent(lblEjercicio)
                     .addComponent(lblTemporada)
                     .addComponent(lblPesoCorporalMinimo)
                     .addComponent(lblPesoCorporalMaximo)
                     .addComponent(lblPesoMinimo)
                     .addComponent(lblPesoMaximo)
                )
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaMinima,GroupLayout.PREFERRED_SIZE,70,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaMaxima,GroupLayout.PREFERRED_SIZE,70,GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEjercicio,GroupLayout.PREFERRED_SIZE,140,GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTemporada,GroupLayout.PREFERRED_SIZE,140,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoCorporalMinimo,GroupLayout.PREFERRED_SIZE,50,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoCorporalMaximo,GroupLayout.PREFERRED_SIZE,50,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoMinimo,GroupLayout.PREFERRED_SIZE,50,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoMaximo,GroupLayout.PREFERRED_SIZE,50,GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar)
                )
            )
            .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(scrollPaneA,GroupLayout.PREFERRED_SIZE,430,GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollPaneB,GroupLayout.PREFERRED_SIZE,430,GroupLayout.PREFERRED_SIZE)
                )
                .addComponent(btnTransferir)
            )
        );
        layoutBusqueda.setVerticalGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(lblFechaMinima,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaMaxima,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEjercicio,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTemporada,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesoCorporalMinimo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesoCorporalMaximo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesoMinimo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesoMaximo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutBusqueda.createSequentialGroup()
                    .addComponent(txtFechaMinima,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaMaxima,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEjercicio,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTemporada,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoCorporalMinimo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoCorporalMaximo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoMinimo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoMaximo,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar)
                )
            )
            .addGroup(layoutBusqueda.createSequentialGroup()
                .addGroup(layoutBusqueda.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneA)
                    .addComponent(scrollPaneB)
                )
                .addComponent(btnTransferir)
            )
        );
    }
    
    public void actualizar(){
        actualizarComboBoxEjercicio();
        actualizarComboBoxTemporada();
    }
    
    private void actualizarComboBoxTemporada(){
        cbTemporada.removeAllItems();
        cbTemporada.addItem("Todas");
        for(String s : GestorBaseDatos.getNombreTemporadas()){
            cbTemporada.addItem(s);
        }
    }
    
    private void actualizarComboBoxEjercicio(){
        cbEjercicio.removeAllItems();
        cbEjercicio.addItem("Todos");
        for(String s : GestorBaseDatos.getNombreEjercicios()){
            cbEjercicio.addItem(s);
        }
    }  
    
    private void filtrar(){
        LogicaBusqueda busqueda = new LogicaBusqueda();
        busqueda.setFechaMinima(txtFechaMinima.getText());
        busqueda.setFechaMaxima(txtFechaMaxima.getText());
        busqueda.setEjercicio(cbEjercicio.getSelectedItem().toString());
        busqueda.setTemporada(cbTemporada.getSelectedItem().toString());
        busqueda.setPesoCorporalMinimo(txtPesoCorporalMinimo.getText());
        busqueda.setPesoCorporalMaximo(txtPesoCorporalMaximo.getText());
        busqueda.setPesoMinimo(txtPesoMinimo.getText());
        busqueda.setPesoMaximo(txtPesoMaximo.getText());
        textA.setText("");
        String text = busqueda.buscar();
        textA.setText(text);
    }   
    

}
