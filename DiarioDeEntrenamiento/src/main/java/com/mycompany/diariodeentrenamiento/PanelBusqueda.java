
package com.mycompany.diariodeentrenamiento;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
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
 * Esta clase es la interfaz gráfica de usuario del panel de búsqueda de la aplicación
 */
public class PanelBusqueda extends JPanel {
    
    //Etiquetas
    private JLabel lblFechaMinima;
    private JLabel lblFechaMaxima;
    private JLabel lblEjercicio;
    private JLabel lblTemporada;
    private JLabel lblPesoCorporalMinimo;
    private JLabel lblPesoCorporalMaximo;
    private JLabel lblPesoMinimo;
    private JLabel lblPesoMaximo;

    //Campos de formulario
    private JComboBox cbEjercicio;
    private JComboBox cbTemporada;
    private JTextField txtFechaMinima;
    private JTextField txtFechaMaxima;
    private JTextField txtPesoCorporalMinimo;
    private JTextField txtPesoCorporalMaximo;
    private JTextField txtPesoMinimo;
    private JTextField txtPesoMaximo;
    
    //Botones
    private JButton btnFiltrar;
    private JButton btnTransferir;
    
    //Campos de texto y sus paneles de scroll de los resultados de búsqueda
    private JScrollPane scrollPaneA;
    private JScrollPane scrollPaneB;
    private JTextArea textA;
    private JTextArea textB;

    //Inicia todos los componentes
    public PanelBusqueda (){
        
        //Color del panel 
        setBackground(new Color(250, 253, 138));
        
        //Etiquetas con su texto correspondiente
        lblFechaMinima = new JLabel("Después de  ");
        lblFechaMaxima = new JLabel("Antes de  ");
        lblEjercicio = new JLabel("Ejercicio ");
        lblTemporada = new JLabel("Temporada ");
        lblPesoCorporalMinimo = new JLabel("Peso corporal mínimo ");
        lblPesoCorporalMaximo = new JLabel("Peso corporal máximo ");
        lblPesoMinimo = new JLabel("Peso levantado mínimo ");
        lblPesoMaximo = new JLabel("Peso levantado máximo ");

        //campos de formulario vacios
        cbEjercicio = new JComboBox();
        cbTemporada = new JComboBox();
        txtFechaMinima = new JTextField();
        txtFechaMaxima = new JTextField();
        txtPesoCorporalMinimo = new JTextField();
        txtPesoCorporalMaximo = new JTextField();
        txtPesoMinimo = new JTextField();
        txtPesoMaximo = new JTextField();
        
        //Botones y su texto
        btnFiltrar = new JButton("Filtrar");
        btnTransferir = new JButton("-----Transferir---->");
        
        //Campos de texto de resultado y sus scroll pane
        scrollPaneA = new JScrollPane();
        scrollPaneB = new JScrollPane();
        textA = new JTextArea();
        textB = new JTextArea();
        
        //Añadimos la fecha actual por defecto al formulario, así sirve también de guia de usuario en cuanto al formato se refiere
        txtFechaMaxima.setText(LocalDate.now().toString());
        
        //Añade los paneles de texto de resultado a su scroll pane
        scrollPaneA.setViewportView(textA);
        scrollPaneB.setViewportView(textB);
        
        //Se personaliza cada componente
        scrollPaneA.getVerticalScrollBar().setUnitIncrement(10);
        scrollPaneB.getVerticalScrollBar().setUnitIncrement(10);
        scrollPaneA.setBackground(new Color(250, 162, 57));
        scrollPaneB.setBackground(new Color(250, 162, 57));
        scrollPaneA.setBorder(new EmptyBorder(5,5,5,5));
        scrollPaneB.setBorder(new EmptyBorder(5,5,5,5));
        
        textA.setFont(new Font(Font.DIALOG,Font.BOLD,12));
        textB.setFont(new Font(Font.DIALOG,Font.BOLD,12));
        
        textA.setEditable(false);
        textB.setEditable(false);
                
        //Se autorellenan los comboBox
        actualizar();
        

        //añade la función filtrar al botón para realizar la búsqueda
        btnFiltrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
            }
        });
        
        //Añade la función transferir al botón para pasar la información del panel de resultado izquierdo al derecho
        btnTransferir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                textB.setText(textA.getText());
                textA.setText("");
            }
        });
        
        //Añade un group layout para el diseño de contenedores
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
    
    //Actualiza los comboBox
    public void actualizar(){
        actualizarComboBoxEjercicio();
        actualizarComboBoxTemporada();
    }
    
    //Actualiza el combo box temporada
    private void actualizarComboBoxTemporada(){
        cbTemporada.removeAllItems();
        cbTemporada.addItem("Todas");
        for(String s : GestorBaseDatos.getNombreTemporadas()){
            cbTemporada.addItem(s);
        }
    }
    
    //Actualiza el combo box ejercicio
    private void actualizarComboBoxEjercicio(){
        cbEjercicio.removeAllItems();
        cbEjercicio.addItem("Todos");
        for(String s : GestorBaseDatos.getNombreEjercicios()){
            cbEjercicio.addItem(s);
        }
    }  
    
    //Recoge todos los datos de los campos y realiza una búsqueda, el resultado de la búsqueda lo escribe en el panel de texto izquierdo
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
