package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import POJOs.Entrenamiento;
import POJOs.Serie;
import POJOs.Temporada;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author victo
 */
public class InterfazUsuario extends JFrame implements MouseListener{
    
    private JTabbedPane tabbedPane;
    
/*=======================================================================
    Variables del panel de edición
=========================================================================*/
    
    private JSplitPane panelEdicion;
    private JSplitPane panelTemporadaEntrenamiento;
    private JSplitPane panelEjercicioSerie;
    
    
    private JScrollPane scrollTemporadas;
    private JScrollPane scrollEntrenamientos;
    private JScrollPane scrollEjercicios;
    private JScrollPane scrollSeries;
    
    //Los 4 posibles objetos seleccionables al mismo tiempo de la aplicación
    private PanelTemporada temporadaSeleccionada;
    private PanelEntrenamiento entrenamientoSeleccionado;
    private PanelEjercicio ejercicioSeleccionado;
    private PanelSerie serieSeleccionada;
    
/*=======================================================================
    Paneles
=========================================================================*/
    private JPanel panelTemporada;
    private JPanel panelEntrenamiento;
    private JPanel panelEjercicio;
    private JPanel panelSerie;
    private JPanel panelListaTemporadas;
    private JPanel panelListaEntrenamientos;
    private JPanel panelListaEjercicios;
    private JPanel panelListaSeries;
  
/*=======================================================================
    Campos de texto
=========================================================================*/
    private JTextField txtFechaInicio;
    private JTextField txtDescripcionTemporada;
    private JTextField txtFecha;
    private JTextField txtPesoCorporal;
    private JTextField txtDescripcionEntrenamiento;
    private JTextField txtNombre;
    private JTextField txtVariante;
    private JTextField txtDescripcionEjercicio;
    private JTextField txtPeso;
    private JTextField txtRepeticiones;
    private JTextField txtExtra;
/*=======================================================================
    Etiquetas
=========================================================================*/    
    private JLabel lblTemporadas;
    private JLabel lblFechaInicio;
    private JLabel lblDescripcionTemporada;
    private JLabel lblEntrenamientos;
    private JLabel lblFecha;
    private JLabel lblPesoCorporal;
    private JLabel lblDescripcionEntrenamiento;
    private JLabel lblEjercicios;
    private JLabel lblNombre;
    private JLabel lblVariante;
    private JLabel lblDescripcionEjercicio;
    private JLabel lblSeries;
    private JLabel lblPeso;
    private JLabel lblRepeticiones;
    private JLabel lblExtra;
    private JLabel lblTemporadaInfo;
    private JLabel lblEntrenamientoInfo;
    private JLabel lblEjercicioInfo;
    private JLabel lblSerieInfo;
/*=======================================================================
    Botones
=========================================================================*/    
    private JButton btnGuardarTemporada;
    private JButton btnAnnadirTemporada;
    private JButton btnGuardarEntrenamiento;
    private JButton btnAnnadirEntrenamiento;
    private JButton btnGuardarEjercicio;
    private JButton btnAnnadirEjercicio;
    private JButton btnGuardarSerie;
    private JButton btnAnnadirSerie;
    private JButton btnclonarSerie;
    
    
    
/*=======================================================================
    Variables del panel de búsqueda
=========================================================================*/
    private JPanel panelBusqueda;
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

    
    public static void main(String[] args){
        new InterfazUsuario();
    }
    
/*=======================================================================
    Métodos de la clase
=========================================================================*/
    
    public InterfazUsuario(){
        
        crearPanelEdicion();
        crearPanelBusqueda();
        
        annadirTemporadas(GestorBaseDatos.getTemporadas());
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Editor ",new ImageIcon(".\\src\\main\\java\\iconos\\edit.png"), panelEdicion);
        tabbedPane.addTab("Buscador ",new ImageIcon(".\\src\\main\\java\\iconos\\search.png"), panelBusqueda);
        add(tabbedPane);
        
        tabbedPane.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tp = (JTabbedPane)e.getSource();
                if(tp.getSelectedComponent() == panelBusqueda)
                    actualizarComboBoxEjercicio();
                    actualizarComboBoxTemporada();
            }
        });
        
        setSize(1230,800);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void crearPanelBusqueda(){
        
        panelBusqueda = new JPanel();
        panelBusqueda.setBackground(new Color(250, 253, 138));
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
        
        panelBusqueda.setLayout(new BoxLayout(panelBusqueda,BoxLayout.Y_AXIS));
        
        GroupLayout layoutBusqueda = new GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(layoutBusqueda);
        
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
    
    private void actualizarComboBoxTemporada(){
        cbTemporada.removeAllItems();
        cbTemporada.addItem("Todas");
        for(String s : GestorBaseDatos.getListaTemporadas()){
            cbTemporada.addItem(s);
        }
    }
    
    private void actualizarComboBoxEjercicio(){
        cbEjercicio.removeAllItems();
        cbEjercicio.addItem("Todos");
        for(String s : GestorBaseDatos.getListaEjercicios()){
            cbEjercicio.addItem(s);
        }
    }  
    
    private void filtrar(){
        Filtro filtro = new Filtro();
        filtro.setFechaMinima(txtFechaMinima.getText());
        filtro.setFechaMaxima(txtFechaMaxima.getText());
        filtro.setEjercicio(cbEjercicio.getSelectedItem().toString());
        filtro.setTemporada(cbTemporada.getSelectedItem().toString());
        filtro.setPesoCorporalMinimo(txtPesoCorporalMinimo.getText());
        filtro.setPesoCorporalMaximo(txtPesoCorporalMaximo.getText());
        filtro.setPesoMinimo(txtPesoMinimo.getText());
        filtro.setPesoMaximo(txtPesoMaximo.getText());
        ArrayList<Ejercicio> ejercicios = GestorBaseDatos.getConsulta(filtro.crearQuery());
        textA.setText("");
        //Entrenamiento entrenamientoPrevio = null;
        Entrenamiento entrenamientoImprimir = null;
        //Temporada temporadaPrevia = null;
        Temporada temporadaImprimir = null;
        boolean primerVez = true;

        for(Ejercicio e : ejercicios){
            if(primerVez){
                entrenamientoImprimir = GestorBaseDatos.getEntrenamiento(e.getIdEntrenamiento());
                temporadaImprimir = GestorBaseDatos.getTemporada(entrenamientoImprimir.getIdTemporada());
                textA.append( "  " + temporadaImprimir + "\n");
                textA.append("\n    " + entrenamientoImprimir + "\n");
                primerVez = false;
            }
            else{

                if(entrenamientoImprimir.getId() != e.getIdEntrenamiento()){
                    entrenamientoImprimir = GestorBaseDatos.getEntrenamiento(e.getIdEntrenamiento());
                    if(temporadaImprimir.getId() != entrenamientoImprimir.getIdTemporada()){
                        temporadaImprimir = GestorBaseDatos.getTemporada(entrenamientoImprimir.getIdTemporada());
                        textA.append( "\n  " + temporadaImprimir + "\n");   
                    }
                    textA.append("\n    " + entrenamientoImprimir + "\n");
                }
            }

            textA.append("      " + e.toString() + e.resumenSeries() +"\n");
        }
    }       
    
    private void crearPanelEdicion(){
        
        panelEdicion = new JSplitPane();
        panelTemporadaEntrenamiento = new JSplitPane();
        panelEjercicioSerie = new JSplitPane();
                
        panelTemporada = new JPanel();
        panelEntrenamiento = new JPanel();
        panelEjercicio = new JPanel();       
        panelSerie = new JPanel();
        
        scrollTemporadas = new JScrollPane();
        scrollEntrenamientos = new JScrollPane();
        scrollEjercicios = new JScrollPane();   
        scrollSeries = new JScrollPane();  
        
        panelListaTemporadas = new JPanel();
        panelListaEntrenamientos = new JPanel();
        panelListaEjercicios = new JPanel();
        panelListaSeries = new JPanel();
        
        lblTemporadaInfo = new JLabel("Temporada seleccionada");
        lblTemporadaInfo.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        lblEntrenamientoInfo = new JLabel("Entrenamiento seleccionado");
        lblEntrenamientoInfo.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        lblEjercicioInfo = new JLabel("Ejercicio seleccionado");
        lblEjercicioInfo.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        lblSerieInfo = new JLabel("Serie seleccionada");
        lblSerieInfo.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        
        lblTemporadas = new JLabel("Lista de temporadas");
        lblFechaInicio = new JLabel ("Inicio de temporada");
        lblDescripcionTemporada = new JLabel ("Descripcion");
        
        lblEntrenamientos = new JLabel("Lista de entrenamientos de la temporada");
        lblFecha = new JLabel ("Día (fecha)");
        lblPesoCorporal = new JLabel ("Peso corporal(kg)");
        lblDescripcionEntrenamiento = new JLabel ("Descripcion");
        
        lblEjercicios = new JLabel("Lista de ejercicios del entrenamiento");
        lblNombre = new JLabel ("Nombre");
        lblVariante = new JLabel ("Variante");
        lblDescripcionEjercicio = new JLabel ("Descripcion");
        
        lblSeries = new JLabel("Lista de series del ejercicio");
        lblPeso = new JLabel ("Peso(kg)");
        lblRepeticiones = new JLabel ("Repeticiones");
        lblExtra = new JLabel ("Extra");
        
        
        txtFechaInicio = new JTextField();
        txtDescripcionTemporada = new JTextField();
        txtFecha = new JTextField();
        txtPesoCorporal = new JTextField();
        txtDescripcionEntrenamiento = new JTextField();
        txtNombre = new JTextField();
        txtVariante = new JTextField();
        txtDescripcionEjercicio = new JTextField();
        txtPeso = new JTextField();
        txtRepeticiones = new JTextField();
        txtExtra = new JTextField();
        
        //Botones
        btnGuardarTemporada = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnGuardarTemporada.setToolTipText("Guardar cambios de temporada");
        btnAnnadirTemporada = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        btnAnnadirTemporada.setToolTipText("Nueva temporada");
        btnGuardarEntrenamiento = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnGuardarEntrenamiento.setToolTipText("Guardar cambios de entrenamiento");
        btnAnnadirEntrenamiento = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        btnAnnadirEntrenamiento.setToolTipText("Nuevo entrenamiento");
        btnGuardarEjercicio = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnGuardarEjercicio.setToolTipText("Guardar cambios del ejercicio");
        btnAnnadirEjercicio = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        btnAnnadirEjercicio.setToolTipText("Nuevo ejercicio");
        btnGuardarSerie = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnGuardarSerie.setToolTipText("Guardar cambios de la serie");
        btnAnnadirSerie = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        btnAnnadirSerie.setToolTipText("Nueva serie");
        btnclonarSerie = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\clone.png"));
        btnclonarSerie.setToolTipText("Clonar serie");

        panelTemporada.setBackground(new Color(180, 160, 255));
        lblTemporadas.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        panelListaTemporadas.setLayout(new BoxLayout(panelListaTemporadas,BoxLayout.Y_AXIS));
        panelListaTemporadas.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        scrollTemporadas.setViewportView(panelListaTemporadas);
        scrollTemporadas.setBackground(new Color(100, 80, 180));
        scrollTemporadas.setBorder(new EmptyBorder(5,5,5,5));
        scrollTemporadas.getVerticalScrollBar().setUnitIncrement(10);

        panelEntrenamiento.setBackground(new Color(180, 160, 255));
        lblEntrenamientos.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        panelListaEntrenamientos.setLayout(new BoxLayout(panelListaEntrenamientos,BoxLayout.Y_AXIS));
        panelListaEntrenamientos.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        scrollEntrenamientos.setViewportView(panelListaEntrenamientos);
        scrollEntrenamientos.setBackground(new Color(100, 80, 180));
        scrollEntrenamientos.setBorder(new EmptyBorder(5,5,5,5));        
        scrollEntrenamientos.getVerticalScrollBar().setUnitIncrement(10);
    
        panelEjercicio.setBackground(new Color(180, 160, 255));
        lblEjercicios.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        panelListaEjercicios.setLayout(new BoxLayout(panelListaEjercicios,BoxLayout.Y_AXIS));
        panelListaEjercicios.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        scrollEjercicios.setViewportView(panelListaEjercicios);
        scrollEjercicios.setBackground(new Color(100, 80, 180));
        scrollEjercicios.setBorder(new EmptyBorder(5,5,5,5));
        scrollEjercicios.getVerticalScrollBar().setUnitIncrement(10);
        
        panelSerie.setBackground(new Color(180, 160, 255));
        lblSeries.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        panelListaSeries.setLayout(new BoxLayout(panelListaSeries,BoxLayout.Y_AXIS));
        panelListaSeries.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        scrollSeries.setViewportView(panelListaSeries);
        scrollSeries.setBackground(new Color(100, 80, 180));
        scrollSeries.setBorder(new EmptyBorder(5,5,5,5));
        scrollSeries.getVerticalScrollBar().setUnitIncrement(10);
        
        btnGuardarTemporada.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirTemporada.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardarEntrenamiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirEntrenamiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardarEjercicio.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirEjercicio.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardarSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnclonarSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        btnGuardarTemporada.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTemporada();
            }
        });
        btnAnnadirTemporada.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaTemporada();
            }
        });
        btnGuardarEntrenamiento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEntrenamiento();
            }
        });
        btnAnnadirEntrenamiento.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoEntrenamiento();
            }
        });
        btnGuardarEjercicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEjercicio();
            }
        });
        btnAnnadirEjercicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoEjercicio();
            }
        });
        btnGuardarSerie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarSerie();
            }
        });
        btnAnnadirSerie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaSerie();
            }
        });
        btnclonarSerie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaSerieClonada();
            }
        });
//------------------------------------------------------------------------------------------------------   
        GroupLayout layout = new GroupLayout(panelTemporada);
        panelTemporada.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
    
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblTemporadas)
            .addComponent(btnAnnadirTemporada)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollTemporadas)
                .addContainerGap()
            )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addComponent(lblTemporadas)
            .addGap(15)
            .addComponent(btnAnnadirTemporada)
            .addComponent(scrollTemporadas)
            .addGap(15)
        );
//------------------------------------------------------------------------------------------------------
        GroupLayout layoutPanelEntrenamiento = new GroupLayout(panelEntrenamiento);
        panelEntrenamiento.setLayout(layoutPanelEntrenamiento);
        layoutPanelEntrenamiento.setAutoCreateGaps(true);
        layoutPanelEntrenamiento.setAutoCreateContainerGaps(true);
        
        layoutPanelEntrenamiento.setHorizontalGroup(layoutPanelEntrenamiento.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblTemporadaInfo)
            .addGroup(layoutPanelEntrenamiento.createSequentialGroup()
                .addGap(20)//Margen del formulario
                .addGroup(layoutPanelEntrenamiento.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaInicio)
                    .addComponent(lblDescripcionTemporada)  
                )
                .addGroup(layoutPanelEntrenamiento.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaInicio,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionTemporada,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
                .addGroup(layoutPanelEntrenamiento.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarTemporada)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
            )
            .addComponent(lblEntrenamientos)
            .addComponent(btnAnnadirEntrenamiento)
            .addGroup(layoutPanelEntrenamiento.createSequentialGroup()
                .addComponent(scrollEntrenamientos)
                .addContainerGap()
            )
        );
        layoutPanelEntrenamiento.setVerticalGroup(layoutPanelEntrenamiento.createSequentialGroup()
            .addComponent(lblTemporadaInfo)
            .addGap(15)
            .addGroup(layoutPanelEntrenamiento.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanelEntrenamiento.createSequentialGroup()
                    .addComponent(lblFechaInicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcionTemporada,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelEntrenamiento.createSequentialGroup()
                    .addComponent(txtFechaInicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)              
                    .addComponent(txtDescripcionTemporada,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelEntrenamiento.createSequentialGroup()
                    .addComponent(btnGuardarTemporada,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )
            )
            .addGap(15)
            .addComponent(lblEntrenamientos)
            .addGap(15)
            .addComponent(btnAnnadirEntrenamiento)
            .addComponent(scrollEntrenamientos)
            .addGap(15)
        );
//------------------------------------------------------------------------------------------------------
        GroupLayout layoutPanelEjercicio = new GroupLayout(panelEjercicio);
        panelEjercicio.setLayout(layoutPanelEjercicio);
        layoutPanelEjercicio.setAutoCreateGaps(true);
        layoutPanelEjercicio.setAutoCreateContainerGaps(true);
        
        layoutPanelEjercicio.setHorizontalGroup(layoutPanelEjercicio.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblEntrenamientoInfo)
            .addGroup(layoutPanelEjercicio.createSequentialGroup()
                .addGap(20)//Margen del formulario
                .addGroup(layoutPanelEjercicio.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha)
                    .addComponent(lblPesoCorporal)
                    .addComponent(lblDescripcionEntrenamiento)  
                )
                .addGroup(layoutPanelEjercicio.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtFecha,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoCorporal,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionEntrenamiento,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
                .addGroup(layoutPanelEjercicio.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarEntrenamiento)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
            )

            .addComponent(lblEjercicios)
            .addComponent(btnAnnadirEjercicio)                
            .addGroup(layoutPanelEjercicio.createSequentialGroup()
                .addComponent(scrollEjercicios)
                .addContainerGap()
            )
        );
        layoutPanelEjercicio.setVerticalGroup(layoutPanelEjercicio.createSequentialGroup()
            .addComponent(lblEntrenamientoInfo)
            .addGap(15)
            .addGroup(layoutPanelEjercicio.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanelEjercicio.createSequentialGroup()
                    .addComponent(lblFecha,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPesoCorporal,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcionEntrenamiento,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelEjercicio.createSequentialGroup()
                    .addComponent(txtFecha,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoCorporal,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)               
                    .addComponent(txtDescripcionEntrenamiento,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelEjercicio.createSequentialGroup()
                    .addComponent(btnGuardarEntrenamiento,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )
            )

            .addGap(15)
            .addComponent(lblEjercicios)
            .addGap(15)
            .addComponent(btnAnnadirEjercicio)
            .addComponent(scrollEjercicios)
            .addGap(15)
        );
//------------------------------------------------------------------------------------------------------
        GroupLayout layoutPanelSerie = new GroupLayout(panelSerie);
        panelSerie.setLayout(layoutPanelSerie);
        layoutPanelSerie.setAutoCreateGaps(true);
        layoutPanelSerie.setAutoCreateContainerGaps(true);
        
        layoutPanelSerie.setHorizontalGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblEjercicioInfo)
            .addGroup(layoutPanelSerie.createSequentialGroup()//Grupo del formulario y boton
                .addGap(20)//Margen del formulario
                .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(lblVariante)
                    .addComponent(lblDescripcionEjercicio)  
                )
                .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVariante,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionEjercicio,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
                .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarEjercicio)
                    .addComponent(btnAnnadirEjercicio)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
            )
            .addComponent(lblSeries)
            .addComponent(btnAnnadirSerie)
            .addGroup(layoutPanelSerie.createSequentialGroup()//Grupo de la lista de series
                .addComponent(scrollSeries)
                .addContainerGap()
            )
            .addComponent(lblSerieInfo)
            .addGroup(layoutPanelSerie.createSequentialGroup()
                .addGap(20)//Margen del formulario
                .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblPeso)
                    .addComponent(lblRepeticiones)
                    .addComponent(lblExtra)  
                )
                .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtPeso,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRepeticiones,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExtra,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
                .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarSerie)
                    .addComponent(btnclonarSerie)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
            )

        );
        layoutPanelSerie.setVerticalGroup(layoutPanelSerie.createSequentialGroup()
            .addComponent(lblEjercicioInfo)
            .addGap(15)
            .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanelSerie.createSequentialGroup()
                    .addComponent(lblNombre,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblVariante,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcionEjercicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelSerie.createSequentialGroup()
                    .addComponent(txtNombre,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVariante,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)               
                    .addComponent(txtDescripcionEjercicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelSerie.createSequentialGroup()
                    .addComponent(btnGuardarEjercicio,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnnadirEjercicio,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )
            )
            .addGap(15)
            .addComponent(lblSeries)
            .addGap(15)
            .addComponent(btnAnnadirSerie,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
            .addComponent(scrollSeries)
            .addGap(15)
            .addComponent(lblSerieInfo)
            .addGap(15)
            .addGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layoutPanelSerie.createSequentialGroup()
                    .addComponent(lblPeso,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRepeticiones,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExtra,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelSerie.createSequentialGroup()
                    .addComponent(txtPeso,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRepeticiones,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)               
                    .addComponent(txtExtra,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layoutPanelSerie.createSequentialGroup()
                    .addComponent(btnGuardarSerie,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclonarSerie,GroupLayout.PREFERRED_SIZE,20,GroupLayout.PREFERRED_SIZE)
                )
            )
            .addGap(50)
        );
        
        //Estableciendo todos los SplitPane de la pantalla
        panelEdicion.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        panelEdicion.setRightComponent(panelEjercicioSerie);
        panelEdicion.setLeftComponent(panelTemporadaEntrenamiento);
        panelEdicion.setDividerLocation(400);
        
        panelTemporadaEntrenamiento.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panelTemporadaEntrenamiento.setTopComponent(panelTemporada);
        panelTemporadaEntrenamiento.setBottomComponent(panelEntrenamiento);
        panelTemporadaEntrenamiento.setDividerLocation(250);
        
        panelEjercicioSerie.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        panelEjercicioSerie.setRightComponent(panelSerie);
        panelEjercicioSerie.setLeftComponent(panelEjercicio);
        panelEjercicioSerie.setDividerLocation(390);
    }

    /*=========================================================================================
            Métodos de funcionalidad
    ===========================================================================================*/
    
    @Override
    public void mouseClicked(MouseEvent e) {
        seleccionar((PanelDatos)e.getComponent());
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {
        ((PanelDatos)e.getComponent()).activar();
    }
    @Override
    public void mouseExited(MouseEvent e) {
        ((PanelDatos)e.getComponent()).desactivar();
    }
    
    //=======================================================
    public void seleccionar(PanelDatos panelDatosSeleccionado){
        if(panelDatosSeleccionado instanceof PanelTemporada){
            if(temporadaSeleccionada != null){
                //Cuando se selecciona un registro distinto 
                if(temporadaSeleccionada != panelDatosSeleccionado){
                    temporadaSeleccionada.seleccionar();//Se deselecciona el registro anterior
                    temporadaSeleccionada = (PanelTemporada) panelDatosSeleccionado;
                }
                //Cuando los registros seleccionados son el mismo se desmarcará
                else{
                    temporadaSeleccionada = null;
                }
            }
            //Si temporada seleccionada es null
            else{
                temporadaSeleccionada = (PanelTemporada) panelDatosSeleccionado;
            }
            recargarCamposTemporada();
        }
        else if(panelDatosSeleccionado instanceof PanelEntrenamiento){
            if(entrenamientoSeleccionado != null){
                //Cuando se selecciona un registro distinto 
                if(entrenamientoSeleccionado  != panelDatosSeleccionado){
                    entrenamientoSeleccionado.seleccionar();//Se deselecciona el registro anterior
                    entrenamientoSeleccionado = (PanelEntrenamiento) panelDatosSeleccionado;
                }
                //Cuando los registros seleccionados son el mismo se desmarcará
                else{
                    entrenamientoSeleccionado = null;
                }
            }
            //Si temporada seleccionada es null
            else{
                entrenamientoSeleccionado = (PanelEntrenamiento) panelDatosSeleccionado;
            }
            recargarCamposEntrenamiento(); 
        }
        else if(panelDatosSeleccionado instanceof PanelEjercicio){
            if(ejercicioSeleccionado != null){
                //Cuando se selecciona un registro distinto 
                if(ejercicioSeleccionado != panelDatosSeleccionado){
                    ejercicioSeleccionado.seleccionar();//Se deselecciona el registro anterior
                    ejercicioSeleccionado = (PanelEjercicio) panelDatosSeleccionado;
                }
                //Cuando los registros seleccionados son el mismo se desmarcará
                else{
                    ejercicioSeleccionado = null;
                }
            }
            //Si temporada seleccionada es null
            else{
                ejercicioSeleccionado = (PanelEjercicio) panelDatosSeleccionado;
            }
            recargarCamposEjercicio();
        }
        else if(panelDatosSeleccionado instanceof PanelSerie){
            if(serieSeleccionada != null){
                //Cuando se selecciona un registro distinto 
                if(serieSeleccionada != panelDatosSeleccionado){
                    serieSeleccionada.seleccionar();//Se deselecciona el registro anterior
                    serieSeleccionada = (PanelSerie) panelDatosSeleccionado;
                }
                //Cuando los registros seleccionados son el mismo se desmarcará
                else{
                    serieSeleccionada = null;
                }
            }
            //Si temporada seleccionada es null
            else{
                serieSeleccionada = (PanelSerie) panelDatosSeleccionado;
            }
            recargarCamposSerie();
        }
        //Marca el registro seleccionado
        panelDatosSeleccionado.seleccionar();
        //Genera la lista de elementos que contenga dicho registro solo si está seleccionado
        if(panelDatosSeleccionado.isSeleccionado()){
            panelDatosSeleccionado.generarLista();
        }
    }
    
    //=======================================================
    
    public void eliminarPanelTemporada(JPanel panel){
        panelListaTemporadas.remove(panel);
        panelListaTemporadas.updateUI();
        clearTemporada();
    }
    
    public void eliminarPanelEntrenamiento(JPanel panel){
        panelListaEntrenamientos.remove(panel);
        panelListaEntrenamientos.updateUI();
        clearEntrenamiento();
    }
    
    public void eliminarPanelEjercicio(JPanel panel){
        panelListaEjercicios.remove(panel);
        panelListaEjercicios.updateUI();
        clearEjercicio();
    }
    
    public void eliminarPanelSerie(JPanel panel){
        panelListaSeries.remove(panel);
        panelListaSeries.updateUI();
        clearSerie();
    }
    
    //======================================================
    
    public void annadirTemporadas(ArrayList <Temporada> temporadas){
        panelListaTemporadas.removeAll();
        for(Temporada t : temporadas){
            panelListaTemporadas.add(new PanelTemporada(t,this,this));
        }
        panelListaTemporadas.updateUI();
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

    //Métodos para limpiar en cascada las listas de los subcontenedores de cada entidad
    private void clearTemporada(){
        txtFechaInicio.setText("");
        txtDescripcionTemporada.setText("");
        panelListaEntrenamientos.removeAll();
        panelListaEntrenamientos.updateUI();
        entrenamientoSeleccionado = null;
        clearEntrenamiento();
    }
    private void clearEntrenamiento(){
        txtFecha.setText("");
        txtPesoCorporal.setText("");
        txtDescripcionEntrenamiento.setText("");
        panelListaEjercicios.removeAll();
        panelListaEjercicios.updateUI();
        ejercicioSeleccionado = null;
        clearEjercicio();
    }
    private void clearEjercicio(){
        txtNombre.setText("");
        txtVariante.setText("");
        txtDescripcionEjercicio.setText("");
        panelListaSeries.removeAll();
        panelListaSeries.updateUI();
        serieSeleccionada = null;
        clearSerie();
    }
    
    private void clearSerie(){
        txtPeso.setText("");
        txtRepeticiones.setText("");
        txtExtra.setText("");
    }
    
    
    //Métodos de creación de registros
    private void nuevaTemporada(){
        Temporada temporada = new Temporada();
        temporada.setFechaInicio((Date)Date.valueOf(LocalDate.now()));
        temporada.setDescripcion("Sin nombrar");
        GestorBaseDatos.nuevaTemporada(temporada);
        reselecionarTemporada(temporada);
    }
    
    private void nuevoEntrenamiento(){
        if(temporadaSeleccionada != null){
            Entrenamiento entrenamiento = new Entrenamiento();
            entrenamiento.setFecha((Date)Date.valueOf(LocalDate.now()));    
            if( ! txtPesoCorporal.getText().isEmpty()){
                try{
                    entrenamiento.setPesoCorporal(Float.parseFloat(txtPesoCorporal.getText()));
                }
                catch(java.lang.IllegalArgumentException iae){
                    JOptionPane.showMessageDialog(this, "Peso corporal erroneo", "",JOptionPane.WARNING_MESSAGE );
                }
            }
            entrenamiento.setDescripcion(txtDescripcionEntrenamiento.getText());
            entrenamiento.setIdTemporada(temporadaSeleccionada.getTemporada().getId());

            GestorBaseDatos.nuevoEntrenamiento(entrenamiento);
            reseleccionarEntrenamiento(entrenamiento);
        }else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ninguna temporada seleccionada", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void nuevoEjercicio(){
        if(entrenamientoSeleccionado != null){
            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setIdEntrenamiento(entrenamientoSeleccionado.getEntrenamiento().getId());

            GestorBaseDatos.nuevoEjercicio(ejercicio);
            seleccionar(new PanelEjercicio(ejercicio,this,this));
            panelListaEjercicios.add(ejercicioSeleccionado);
            panelListaEjercicios.updateUI();
        }else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún entrenamiento seleccionado", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
    private void nuevaSerie(){
        if(ejercicioSeleccionado != null){
            Serie serie = new Serie();
            serie.setIdEjercicio(ejercicioSeleccionado.getEjercicio().getId());
            GestorBaseDatos.nuevaSerie(serie);
            seleccionar(new PanelSerie(serie,this,this));
            panelListaSeries.add(serieSeleccionada);
            panelListaSeries.updateUI();
        }else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún ejercicio seleccionado", "", JOptionPane.INFORMATION_MESSAGE);
        }        
    }
    private void nuevaSerieClonada(){
        if(ejercicioSeleccionado != null && serieSeleccionada != null){
            Serie serieAClonar = serieSeleccionada.getSerie();
            Serie serie = new Serie();
            
            serie.setPeso(serieAClonar.getPeso());
            serie.setRepeticiones(serieAClonar.getRepeticiones());
            serie.setExtra(serieAClonar.getExtra());
            serie.setIdEjercicio(ejercicioSeleccionado.getEjercicio().getId());

            GestorBaseDatos.nuevaSerie(serie);
            seleccionar(new PanelSerie(serie,this,this));
            panelListaSeries.add(serieSeleccionada);
            panelListaSeries.updateUI();
        }else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún ejercicio o serie seleccionada", "", JOptionPane.INFORMATION_MESSAGE);
        }        
    }
    
    private void actualizarTemporada(){
        Temporada temporada = temporadaSeleccionada.getTemporada();
        try{
            temporada.setFechaInicio(Date.valueOf(txtFechaInicio.getText()));
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "La fecha introducida es erronea", "Fecha erronea",JOptionPane.WARNING_MESSAGE );
        }
        temporada.setDescripcion(txtDescripcionTemporada.getText());
        GestorBaseDatos.actualizarTemporada(temporada);
        reselecionarTemporada(temporada); 
    }
    
    private void actualizarEntrenamiento(){
        try{
            Entrenamiento entrenamiento = entrenamientoSeleccionado.getEntrenamiento();
            try{
                entrenamiento.setFecha(Date.valueOf(txtFecha.getText()));
                entrenamiento.setPesoCorporal(Float.parseFloat(txtPesoCorporal.getText()));
            }
            catch(java.lang.IllegalArgumentException iae){
                JOptionPane.showMessageDialog(this, "La fecha o el peso introducido es erroneo", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
            entrenamiento.setDescripcion(txtDescripcionEntrenamiento.getText());
            GestorBaseDatos.actualizarEntrenamiento(entrenamiento);
            reseleccionarEntrenamiento(entrenamiento);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún elemento seleccionado", "", JOptionPane.INFORMATION_MESSAGE);
        }  
    }
    private void actualizarEjercicio(){
        try{
            Ejercicio ejercicio = ejercicioSeleccionado.getEjercicio();
            ejercicio.setNombre(txtNombre.getText());
            ejercicio.setVariante(txtVariante.getText());
            ejercicio.setDescripcion(txtDescripcionEjercicio.getText());
            GestorBaseDatos.actualizarEjercicio(ejercicio);
            ejercicioSeleccionado.generarTexto();
            ejercicioSeleccionado.updateUI();
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún elemento seleccionado", "", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    private void actualizarSerie(){
        try{
            Serie serie = serieSeleccionada.getSerie();
            try{
                serie.setPeso(Float.parseFloat(txtPeso.getText()));
                serie.setRepeticiones(Integer.parseInt(txtRepeticiones.getText()));
            }
            catch(java.lang.IllegalArgumentException iae){
                JOptionPane.showMessageDialog(this, "Peso o repeticiones incorrectos", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
            }
            serie.setExtra(txtExtra.getText());
            GestorBaseDatos.actualizarSerie(serie);
            serieSeleccionada.generarTexto();
            serieSeleccionada.updateUI();
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún elemento seleccionado", "", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    //Metodos de actualización para registros que contienen fecha y su orden depende de ella
    
    private void reselecionarTemporada(Temporada temporada){
        annadirTemporadas(GestorBaseDatos.getTemporadas());
        PanelTemporada panelTemporada = null;
        //Para seleccionar el nuevo panel habrá que buscarlo en la lista de paneles de temporada
        for(Component c : panelListaTemporadas.getComponents()){
            if(c instanceof PanelTemporada){
                if(((PanelTemporada)c).getTemporada().getId() == temporada.getId())
                panelTemporada = (PanelTemporada)c;
            }
        }
        temporadaSeleccionada = null;
        seleccionar(panelTemporada);
        panelListaTemporadas.updateUI();
    }
    
    private void reseleccionarEntrenamiento(Entrenamiento entrenamiento){
        annadirEntrenamientos(GestorBaseDatos.getEntrenamientos(entrenamiento.getIdTemporada()));
        PanelEntrenamiento panelEntrenamiento = null;
        //Para seleccionar el nuevo panel habrá que buscarlo en la lista de paneles de entrenamientos
        for(Component c : panelListaEntrenamientos.getComponents()){
            if(c instanceof PanelEntrenamiento){
                if(((PanelEntrenamiento)c).getEntrenamiento().getId() == entrenamiento.getId())
                panelEntrenamiento = (PanelEntrenamiento)c;
            }
        }
        entrenamientoSeleccionado = null;
        seleccionar(panelEntrenamiento);
        panelListaEntrenamientos.updateUI();
    }
    
    private void recargarCamposTemporada(){
        clearTemporada();
        if(temporadaSeleccionada != null){
            Temporada temporada = temporadaSeleccionada.getTemporada();
            if(temporada != null){
                if(temporada.getFechaInicio() != null){
                    txtFechaInicio.setText(temporada.getFechaInicio().toString());
                }    
                if(temporada.getDescripcion() != null){
                    txtDescripcionTemporada.setText(temporada.getDescripcion());
                }
            }   
        }
    }
    private void recargarCamposEntrenamiento(){
        clearEntrenamiento();
        if(entrenamientoSeleccionado != null){
            Entrenamiento entrenamiento = entrenamientoSeleccionado.getEntrenamiento();
            if(entrenamiento != null){
                if(entrenamiento.getFecha() != null){
                    txtFecha.setText(entrenamiento.getFecha().toString());
                }
                txtPesoCorporal.setText(Float.toString(entrenamiento.getPesoCorporal()));   
                if(entrenamiento.getDescripcion() != null){
                    txtDescripcionEntrenamiento.setText(entrenamiento.getDescripcion());
                }
            }
        }
    }
    private void recargarCamposEjercicio(){
        clearEjercicio();
        if(ejercicioSeleccionado != null){
            Ejercicio ejercicio = ejercicioSeleccionado.getEjercicio(); 
            if(ejercicio != null){
                if(ejercicio.getNombre() != null){
                    txtNombre.setText(ejercicio.getNombre());
                }
                if(ejercicio.getVariante() != null){
                    txtVariante.setText(ejercicio.getVariante()); 
                }
                if(ejercicio.getDescripcion() != null){
                    txtDescripcionEjercicio.setText(ejercicio.getDescripcion());
                }
            }
        }
    }
    private void recargarCamposSerie(){
        clearSerie();
        if(serieSeleccionada != null){
            Serie serie = serieSeleccionada.getSerie();      
            if(serie != null){
                txtPeso.setText(Float.toString(serie.getPeso()));
                txtRepeticiones.setText(Integer.toString(serie.getRepeticiones()));   
                if(serie.getExtra() != null){
                    txtExtra.setText(serie.getExtra());
                }
            }
        }
    }
}
