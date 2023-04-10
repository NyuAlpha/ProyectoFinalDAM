package com.mycompany.diariodeentrenamiento;

import com.mycompany.POJOs.Ejercicio;
import com.mycompany.POJOs.Entrenamiento;
import com.mycompany.POJOs.Serie;
import com.mycompany.POJOs.Temporada;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.EmptyBorder;

/**
 * Esta clase es el módulo editor de la interfaz gráfica de usuario
 */
public class PanelEdicion extends JSplitPane implements MouseListener{
    
    //Divisiones principales de este módulo (editor)
    private JSplitPane panelTemporadaEntrenamiento;
    private JSplitPane panelEjercicioSerie;
    
    //Paneles principales del editor
    private JPanel panelTemporada;
    private JPanel panelEntrenamiento;
    private JPanel panelEjercicio;
    private JPanel panelSerie;
    
    //ScrollPane de los paneles de registros
    private JScrollPane scrollTemporadas;
    private JScrollPane scrollEntrenamientos;
    private JScrollPane scrollEjercicios;
    private JScrollPane scrollSeries;    
    
    //Paneles de registros
    private JPanel panelListaTemporadas;
    private JPanel panelListaEntrenamientos;
    private JPanel panelListaEjercicios;
    private JPanel panelListaSeries;
    
    //Las 4 posibles casillas seleccionables al mismo tiempo de la aplicación
    private CasillaSeleccionableTemporada temporadaSeleccionada;
    private CasillaSeleccionableEntrenamiento entrenamientoSeleccionado;
    private CasillaSeleccionableEjercicio ejercicioSeleccionado;
    private CasillaSeleccionableSerie serieSeleccionada;

    //Etiquetas del editor
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

    //Label especial para representar listas vacias
    private JLabel lblVacio;
    
    //Campos de texto de los formularios del editor
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

    //Botones del editor
    private JButton btnGuardarTemporada;
    private JButton btnAnnadirTemporada;
    private JButton btnGuardarEntrenamiento;
    private JButton btnAnnadirEntrenamiento;
    private JButton btnGuardarEjercicio;
    private JButton btnAnnadirEjercicio;
    private JButton btnGuardarSerie;
    private JButton btnAnnadirSerie;
    private JButton btnclonarSerie;
    
    //Constructor
    public PanelEdicion(){
      
        //Inicio de todos los campos y componentes
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
        
        
        //Etiquetas
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
        
        //Este label es para avisar de que un lista de un jpanel está vacia.
        lblVacio = new JLabel("Sin elementos");
        lblVacio.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        lblVacio.setBorder(new EmptyBorder(50,0,50,0));
        lblVacio.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        lblVacio.setForeground(Color.LIGHT_GRAY);
        
        
        //Texto
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

        //Diseño de componentes
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
        
        
        //Inicialización,icono, diseño y eventos de los botones
        btnGuardarTemporada = new JButton(new ImageIcon(getClass().getResource("/iconos/save.png")));
        btnGuardarTemporada.setToolTipText("Guardar cambios de temporada");
        btnAnnadirTemporada = new JButton(new ImageIcon(getClass().getResource("/iconos/add.png")));
        btnAnnadirTemporada.setToolTipText("Nueva temporada");
        btnGuardarEntrenamiento = new JButton(new ImageIcon(getClass().getResource("/iconos/save.png")));
        btnGuardarEntrenamiento.setToolTipText("Guardar cambios de entrenamiento");
        btnAnnadirEntrenamiento = new JButton(new ImageIcon(getClass().getResource("/iconos/add.png")));
        btnAnnadirEntrenamiento.setToolTipText("Nuevo entrenamiento");
        btnGuardarEjercicio = new JButton(new ImageIcon(getClass().getResource("/iconos/save.png")));
        btnGuardarEjercicio.setToolTipText("Guardar cambios del ejercicio");
        btnAnnadirEjercicio = new JButton(new ImageIcon(getClass().getResource("/iconos/add.png")));
        btnAnnadirEjercicio.setToolTipText("Nuevo ejercicio");
        btnGuardarSerie = new JButton(new ImageIcon(getClass().getResource("/iconos/save.png")));
        btnGuardarSerie.setToolTipText("Guardar cambios de la serie");
        btnAnnadirSerie = new JButton(new ImageIcon(getClass().getResource("/iconos/add.png")));
        btnAnnadirSerie.setToolTipText("Nueva serie");
        btnclonarSerie = new JButton(new ImageIcon(getClass().getResource("/iconos/clone.png")));
        btnclonarSerie.setToolTipText("Clonar serie");
        
        
        btnGuardarTemporada.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirTemporada.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardarEntrenamiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirEntrenamiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardarEjercicio.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirEjercicio.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardarSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnclonarSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        //Acciones de los botones, hacen lo que su nombre dice que hacen
        
        btnGuardarTemporada.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(null, "¿Está segur@ de que desea guardar los cambios?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
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
                if(JOptionPane.showConfirmDialog(null, "¿Está segur@ de que desea guardar los cambios?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
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
                if(JOptionPane.showConfirmDialog(null, "¿Está segur@ de que desea guardar los cambios?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
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
                if(JOptionPane.showConfirmDialog(null, "¿Está segur@ de que desea guardar los cambios?", "", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION )
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

        
        //Diseño del panel de temporadas con GroupLayout, es el panel con menos subcomponentes
        
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
        
        
        //Diseño del panel de entrenamiento con GroupLayout
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

        
        //Diseño del panel de ejercicio con GroupLayout
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

        //Diseño del panel de serie con GroupLayout, es el panel con más subcomponentes
        GroupLayout layoutPanelSerie = new GroupLayout(panelSerie);
        panelSerie.setLayout(layoutPanelSerie);
        layoutPanelSerie.setAutoCreateGaps(true);
        layoutPanelSerie.setAutoCreateContainerGaps(true);
        
        layoutPanelSerie.setHorizontalGroup(layoutPanelSerie.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(lblEjercicioInfo)
            .addGroup(layoutPanelSerie.createSequentialGroup()//Grupo del formulario y botón
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
        
        //Estableciendo el splitPane principal, dividido en 2 partes horizontalmente
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);//Horientación 
        setRightComponent(panelEjercicioSerie);
        setLeftComponent(panelTemporadaEntrenamiento);
        setDividerLocation(400);
        
        //Este split pane va en la zona izquierda y se divide verticalmente
        panelTemporadaEntrenamiento.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panelTemporadaEntrenamiento.setTopComponent(panelTemporada);
        panelTemporadaEntrenamiento.setBottomComponent(panelEntrenamiento);
        panelTemporadaEntrenamiento.setDividerLocation(250);
        
        //Este split pane va en la zona derecha y se vuelve a dividir horizontalmente
        panelEjercicioSerie.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        panelEjercicioSerie.setRightComponent(panelSerie);
        panelEjercicioSerie.setLeftComponent(panelEjercicio);
        panelEjercicioSerie.setDividerLocation(390);
        
        //Bloqueo de todos los subcomponentes al iniciar la aplicación
        setEnabledPanelTemporada(false);
        
        //Se añaden los únicos registros que deben estar siempre cargados si los hay
        addTemporadas(GestorBaseDatos.getTemporadas());
    }
    
    
    /****************************************************
            Métodos sobreescritos de MouseListener
    *****************************************************/
    
    @Override
    //Si se pulsa el componente llama a clicar y este será seleccionado o deseleccionado
    public void mouseClicked(MouseEvent e) {
        ((CasillaSeleccionable)e.getComponent()).clicar();
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    //Si pasas el mouse por encima del componente este cambiará su color
    public void mouseEntered(MouseEvent e) {
        ((CasillaSeleccionable)e.getComponent()).activar();
    }
    @Override
    //Si el mouse sale del componente este vuelve a su estado original
    public void mouseExited(MouseEvent e) {
        ((CasillaSeleccionable)e.getComponent()).desactivar();
    }
    
    /******************************************************
                Métodos de la propia clase
    *******************************************************/
    
    //Selecciona la temporada enviada por parámetro, pero puede deseleccionarla si ya estaba seleccionada.
    //También puede bloquear y borrar subcontenedores si estos ya no están seleccionados.
    public void seleccionarTemporada(CasillaSeleccionableTemporada casillaTemporada){
        
        //Si ya estaba seleccionada se deselecciona y se anula la temporada seleccionada , se desactivan los subcontenedores
        if(temporadaSeleccionada != null && temporadaSeleccionada.equals(casillaTemporada.getTemporada())){
            if(casillaTemporada.isSeleccionado()){
                temporadaSeleccionada.deseleccionar();
                temporadaSeleccionada = null;
                setEnabledPanelTemporada(false);
                clearTemporada();
            }
            else{
                //reselección fantasma, es automática e imperceptible, solo se activará en caso de actualización de la lista
                temporadaSeleccionada = casillaTemporada;
                temporadaSeleccionada.seleccionar();
            }
        }
        //Si no había nada seleccionado o no eran la misma le asigna la nueva casilla y activa el contenedor de entrenamientos
        else{
            clearTemporada();
            if(temporadaSeleccionada != null)
                temporadaSeleccionada.deseleccionar();//deseleciona la temporada seleccionada antes de asignarle otra
            
            temporadaSeleccionada = casillaTemporada;
            temporadaSeleccionada.seleccionar();
        
            Temporada temporada = temporadaSeleccionada.getTemporada();
            if(temporada.getFechaInicio() != null)
                txtFechaInicio.setText(temporada.getFechaInicio().toString());  
            if(temporada.getDescripcion() != null)
                txtDescripcionTemporada.setText(temporada.getDescripcion());
            
            setEnabledPanelTemporada(true);
            temporadaSeleccionada.generarLista();
        }
    }
    
    //Selecciona el entrenamiento enviado por parámetro, pero puede deseleccionarlo si ya estaba seleccionado.
    //También puede bloquear y borrar subcontenedores si estos ya no están seleccionados.
    public void seleccionarEntrenamiento(CasillaSeleccionableEntrenamiento casillaEntrenamiento){
        
        if(entrenamientoSeleccionado != null && entrenamientoSeleccionado.equals(casillaEntrenamiento.getEntrenamiento())){
            
            if(casillaEntrenamiento.isSeleccionado()){
                entrenamientoSeleccionado.deseleccionar();
                entrenamientoSeleccionado = null;
                setEnabledPanelEntrenamiento(false);
                clearEntrenamiento();
            }
            else{
                //reselección fantasma, es automática e imperceptible, solo se activará en caso de actualización de la lista
                entrenamientoSeleccionado = casillaEntrenamiento;
                entrenamientoSeleccionado.seleccionar();
            }
        }
        //Si no había nada seleccionado o no eran la misma le asigna la nueva casilla y activa el contenedor de ejercicios
        else{
            clearEntrenamiento();
            if(entrenamientoSeleccionado != null)
                entrenamientoSeleccionado.deseleccionar();//deseleciona el entrenamiento seleccionado previo
                
            entrenamientoSeleccionado = casillaEntrenamiento;
            entrenamientoSeleccionado.seleccionar();
        
            Entrenamiento entrenamiento = entrenamientoSeleccionado.getEntrenamiento();
            if(entrenamiento.getFecha() != null)
                txtFecha.setText(entrenamiento.getFecha().toString());

            txtPesoCorporal.setText(Float.toString(entrenamiento.getPesoCorporal()));   
            if(entrenamiento.getDescripcion() != null)
                txtDescripcionEntrenamiento.setText(entrenamiento.getDescripcion());
            
            setEnabledPanelEntrenamiento(true);
            entrenamientoSeleccionado.generarLista();
        }
    }
        
    //Selecciona el ejercicio enviado por parámetro, pero puede deseleccionarlo si ya estaba seleccionado.
    //También puede bloquear y borrar subcontenedores si estos ya no están seleccionados.
    public void seleccionarEjercicio(CasillaSeleccionableEjercicio casillaEjercicio){
        
        if(ejercicioSeleccionado != null && ejercicioSeleccionado.equals(casillaEjercicio.getEjercicio())){
            if(casillaEjercicio.isSeleccionado()){
                ejercicioSeleccionado.deseleccionar();
                ejercicioSeleccionado = null;
                setEnabledPanelEjercicio(false);
                clearEjercicio();
            }
            else{
                //reselección fantasma, es automática e imperceptible, solo se activará en caso de actualización de la lista
                ejercicioSeleccionado = casillaEjercicio;
                ejercicioSeleccionado.seleccionar();
            }
        }
        //Si no había nada seleccionado o no eran la misma le asigna la nueva casilla y activa el contenedor de series
        else{
            clearEjercicio();
            if(ejercicioSeleccionado != null)
                ejercicioSeleccionado.deseleccionar();//deseleciona el ejercicio seleccionado previo
                
            ejercicioSeleccionado = casillaEjercicio;
            ejercicioSeleccionado.seleccionar();
        
            Ejercicio ejercicio = ejercicioSeleccionado.getEjercicio();
                if(ejercicio.getNombre() != null)
                    txtNombre.setText(ejercicio.getNombre());

                if(ejercicio.getVariante() != null)
                    txtVariante.setText(ejercicio.getVariante()); 

                if(ejercicio.getDescripcion() != null)
                    txtDescripcionEjercicio.setText(ejercicio.getDescripcion());

            setEnabledPanelEjercicio(true);
            ejercicioSeleccionado.generarLista();
        }
    }

    //Selecciona la serie enviada por parámetro, pero puede deseleccionarla si ya estaba seleccionada.
    //También puede bloquear y borrar subcontenedores si estos ya no están seleccionados.
    public void seleccionarSerie(CasillaSeleccionableSerie casillaSerie){
        
        //Si ya estaba seleccionada se deselecciona y se anula la serie seleccionada , se desactivan los subcontenedores
        if(serieSeleccionada != null && serieSeleccionada.equals(casillaSerie.getSerie())){
            if(casillaSerie.isSeleccionado()){
                serieSeleccionada.deseleccionar();
                serieSeleccionada = null;
                setEnabledPanelSerie(false);
                clearSerie();
            }
            else{
                //reselección fantasma, es automática e imperceptible, solo se activará en caso de actualización de la lista
                serieSeleccionada = casillaSerie;
                serieSeleccionada.seleccionar();
            }
        }
        //Si no había nada seleccionado o no eran la misma le asigna la nueva casilla y activa el componente de series
        else{
            clearSerie();
            if(serieSeleccionada != null)
                serieSeleccionada.deseleccionar();//deseleciona la serie seleccionado previo
                
            serieSeleccionada = casillaSerie;
            serieSeleccionada.seleccionar();
        
            Serie serie = serieSeleccionada.getSerie();
            txtPeso.setText(Float.toString(serie.getPeso()));
            txtRepeticiones.setText(Integer.toString(serie.getRepeticiones()));   
            if(serie.getExtra() != null)
                txtExtra.setText(serie.getExtra());

            setEnabledPanelSerie(true);
            serieSeleccionada.generarLista();
        }
    }
    
    //Elimina la casilla de la temporada enviada por parámetro del listado de temporadas
    public void eliminarCasillaTemporada(CasillaSeleccionableTemporada casillaTemporada){
        //Si no había ningúna temporada seleccionada y se elimina hace una recarga normal, borrandola sin más
        if(temporadaSeleccionada == null)
            recargarTemporadas(casillaTemporada.getTemporada());
        else{
            //Si la temporada eliminada estaba seleccionada limpia y bloquea sus campos
            if((casillaTemporada).equals(temporadaSeleccionada)){
                setEnabledPanelTemporada(false);
                clearTemporada();
                temporadaSeleccionada = null;
                recargarTemporadas(casillaTemporada.getTemporada());
            }
            //si la temporada seleccionada y la que se desea elimniar no son la misma se reselecciona la que estaba seleccionada
            else
                recargarTemporadas(temporadaSeleccionada.getTemporada());
        }
    }
    
    //Elimina la casilla del entrenamiento enviado por parámetro del listado de entrenamientos
    public void eliminarPanelEntrenamiento(CasillaSeleccionableEntrenamiento panelEntrenamiento){
    
        //Si no había ningún entrenamiento seleccionado y se elimina hace una recarga normal, borrandolo sin más
        if(entrenamientoSeleccionado == null)
            recargarEntrenamientos(panelEntrenamiento.getEntrenamiento());
        else{
            //Si el entrenamiento eliminado estaba seleccionado limpia y bloquea sus campos
            if((panelEntrenamiento).equals(entrenamientoSeleccionado)){
                setEnabledPanelEntrenamiento(false);
                clearEntrenamiento();
                entrenamientoSeleccionado = null;
                recargarEntrenamientos(panelEntrenamiento.getEntrenamiento());
            }
            //si el entrenamiento seleccionado y el que se desea elimniar no son el mismo se reselecciona el que estaba seleccionado
            else
                recargarEntrenamientos(entrenamientoSeleccionado.getEntrenamiento());
        }
        //Actualiza la temporada a la que pertenece por si las fechas han cambiado y son relevantes
        actualizarTemporada();
    }
    
    //Elimina la casilla del ejercicio enviado por parámetro del listado de ejercicios
    public void eliminarPanelEjercicio(CasillaSeleccionableEjercicio panelEjercicio){
        //Si no había ningún ejercicio seleccionado y se elimina hace una recarga normal, borrandolo sin más
        if(ejercicioSeleccionado == null)
            recargarEjercicios(panelEjercicio.getEjercicio());
        else{
            //Si el ejercicio eliminado estaba seleccionado limpia y bloquea sus campos
            if((panelEjercicio).equals(ejercicioSeleccionado)){
                setEnabledPanelEjercicio(false);
                clearEjercicio();
                ejercicioSeleccionado = null;
                recargarEjercicios(panelEjercicio.getEjercicio());
            }
            //si el ejercicio seleccionado y el que se desea elimniar no son el mismo se reselecciona el que estaba seleccionado
            else
                recargarEjercicios(ejercicioSeleccionado.getEjercicio());
        }
    }
    
    //Elimina la casilla de la serie enviada por parámetro del listado de series
    public void eliminarPanelSerie(CasillaSeleccionableSerie casillaEliminada){
        
        //Eliminar serie implica una reubicación de las series, por lo que hay que recargar todas las series desde la base de datos
        panelListaSeries.removeAll();//Se borra la lista previa.
        clearSerie();
        //Como las series pueden ser modificadas cuando se elimina alguna, no es posible identificar cual estaba seleccionada previamente
        //Así que las recarga todas sin más, no llama al metodo recargar series
        addSeries(GestorBaseDatos.getSeries(casillaEliminada.getSerie().getIdEjercicio()));
        setEnabledPanelSerie(false);//desactiva el panel de formulario de serie
        //Además si la serie seleccionada es la serie eliminada, la serie seleccionada se anula.
        if(serieSeleccionada.equals(panelSerie))
            serieSeleccionada = null;
    }
    
    //Añade temporadas en forma de casillas seleccionables, enviandole como parámetro la lista de temporadas
    public void addTemporadas(ArrayList <Temporada> temporadas){
        panelListaTemporadas.removeAll();
        for(Temporada t : temporadas){
            panelListaTemporadas.add(new CasillaSeleccionableTemporada(t,this,this));
        }
        if(temporadas.isEmpty()){
            panelListaTemporadas.add(lblVacio);
        }
        panelListaTemporadas.updateUI();
    }
    
    //Añade entrenamientos en forma de casillas seleccionables, enviandole como parámetro una lista de entrenamientos
    public void addEntrenamientos(ArrayList <Entrenamiento> entrenamientos){   
        panelListaEntrenamientos.removeAll();
        for(Entrenamiento ent : entrenamientos){
            panelListaEntrenamientos.add(new CasillaSeleccionableEntrenamiento(ent,this,this));
        }
        if(entrenamientos.isEmpty()){
            panelListaEntrenamientos.add(lblVacio);
        }
        panelListaEntrenamientos.updateUI();
    }
    
    //Añade ejercicios en forma de casillas seleccionables, enviandole como parámetro una lista de ejercicios
    public void addEjercicios(ArrayList <Ejercicio> ejercicios){
        panelListaEjercicios.removeAll();
        for(Ejercicio ej: ejercicios){
            panelListaEjercicios.add(new CasillaSeleccionableEjercicio(ej,this,this));
        }
        if(ejercicios.isEmpty()){
            panelListaEjercicios.add(lblVacio);
        }
        panelListaEjercicios.updateUI();
    }
    
    //Añade series en forma de casillas seleccionables, enviandole como parámetro una lista de series
    public void addSeries(ArrayList <Serie> series){
        panelListaSeries.removeAll();
        for(Serie s : series){
            panelListaSeries.add(new CasillaSeleccionableSerie(s,this,this));
        }
        if(series.isEmpty()){
            panelListaSeries.add(lblVacio);
        }
        panelListaSeries.updateUI();
    }

    //Limpia en cascada los componentes y subcomponentes de una temporada
    private void clearTemporada(){
        txtFechaInicio.setText("");
        txtDescripcionTemporada.setText("");
        panelListaEntrenamientos.removeAll();
        panelListaEntrenamientos.updateUI();
        entrenamientoSeleccionado = null;
        clearEntrenamiento();
    }
    //Limpia en cascada los componentes y subcomponentes de un entrenamiento
    private void clearEntrenamiento(){
        txtFecha.setText("");
        txtPesoCorporal.setText("");
        txtDescripcionEntrenamiento.setText("");
        panelListaEjercicios.removeAll();
        panelListaEjercicios.updateUI();
        ejercicioSeleccionado = null;
        clearEjercicio();
    }
    //Limpia en cascada los componentes y subcomponentes de un ejercicio
    private void clearEjercicio(){
        txtNombre.setText("");
        txtVariante.setText("");
        txtDescripcionEjercicio.setText("");
        panelListaSeries.removeAll();
        panelListaSeries.updateUI();
        serieSeleccionada = null;
        clearSerie();
    }
    //Limpia el componente de una serie
    private void clearSerie(){
        txtPeso.setText("");
        txtRepeticiones.setText("");
        txtExtra.setText("");
    }
    
    
    //Crea una nueva temporada
    private void nuevaTemporada(){
        Temporada temporada = new Temporada();
        temporada.setFechaInicio((Date)Date.valueOf(LocalDate.now()));
        temporada.setDescripcion("Sin nombrar");
        //la añade a la base de datos y recarga todas las temporadas para reorganizarlas por fecha.
        GestorBaseDatos.nuevaTemporada(temporada);
        recargarTemporadas(temporada);
        
    }
    
    //Crea un nuevo entrenamiento
    private void nuevoEntrenamiento(){
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
        //Lo añade en la base de datos
        GestorBaseDatos.nuevoEntrenamiento(entrenamiento);
        //La temporada debe actualizarse para comprobar si la fecha más reciente ha cambiado
        actualizarTemporada();
        //Recarga los entrenamientos para reorganizarlos por fecha
        recargarEntrenamientos(entrenamiento);
    }
    
    //Crea un nuevo ejercicio
    private void nuevoEjercicio(){
        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setIdEntrenamiento(entrenamientoSeleccionado.getEntrenamiento().getId());
        //Lo añade a la base de datos 
        GestorBaseDatos.nuevoEjercicio(ejercicio);
        
        //seleccionarEjercicio(new CasillaSeleccionableEjercicio(ejercicio,this,this));
        //recarga todos los ejercicios nuevamente
        recargarEjercicios(ejercicio);
    }    

    //Crea una nueva serie
    private void nuevaSerie(){
        Serie serie = new Serie();
        serie.setIdEjercicio(ejercicioSeleccionado.getEjercicio().getId());
        //Añade la serie a la base de datos
        GestorBaseDatos.nuevaSerie(serie);
        //seleccionarSerie(new CasillaSeleccionableSerie(serie,this,this));
        //Recarga todas las series nuevamente
        recargarSeries(serie);
    }
    
    //Clona una serie previamente seleccionada
    private void nuevaSerieClonada(){
        if(serieSeleccionada != null){
            Serie serieAClonar = serieSeleccionada.getSerie();
            Serie serie = new Serie();
            
            serie.setPeso(serieAClonar.getPeso());
            serie.setRepeticiones(serieAClonar.getRepeticiones());
            serie.setExtra(serieAClonar.getExtra());
            serie.setIdEjercicio(ejercicioSeleccionado.getEjercicio().getId());

            //añade la serie a la base de datos
            GestorBaseDatos.nuevaSerie(serie);
            //seleccionarSerie(new CasillaSeleccionableSerie(serie,this,this));
            //panelListaSeries.add(serieSeleccionada);
            //panelListaSeries.updateUI();
            //Recarga todas las series nuevamente
            recargarSeries(serie);
        //Else temporal, hasta asegurarnos de que solo se peude clonar si obligatoriamente hay algo selecionado
        }else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ninguna serie seleccionada para clonar", "", JOptionPane.INFORMATION_MESSAGE);
        }        
    }
    
    //Realiza cambios en la temporada seleccionada y los guarda.
    private void actualizarTemporada(){
        Temporada temporada = temporadaSeleccionada.getTemporada();
        //Try para prevenir formato inválido, en caso de error avisará al usuario de la app
        try{
            Date fechaInicio = Date.valueOf(txtFechaInicio.getText());
            temporada.setFechaInicio(fechaInicio);
            //Usa la fecha del último entrenamiento
            temporada.setFechaFin(GestorBaseDatos.getFechaMasReciente(temporada.getId()));
            temporada.setDescripcion(txtDescripcionTemporada.getText());
            //Actualiza en la base de datos
            GestorBaseDatos.actualizarTemporada(temporada);
            //Recarga todas las temporadas y reselecciona la temporada que ha sido actualizada en este método
            recargarTemporadas(temporada);
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "La fecha introducida es erronea", "Fecha erronea",JOptionPane.WARNING_MESSAGE );
        }
    }  
    
    //Realiza cambios en el entrenamiento seleccionado y los guarda.
    private void actualizarEntrenamiento(){
        Entrenamiento entrenamiento = entrenamientoSeleccionado.getEntrenamiento();
        //Try para prevenir formato inválido, en caso de error avisará al usuario de la app
        try{
            Date fecha = Date.valueOf(txtFecha.getText());
            float pesoCorporal = Float.parseFloat(txtPesoCorporal.getText());
            entrenamiento.setFecha(fecha);
            entrenamiento.setPesoCorporal(pesoCorporal);  
            entrenamiento.setDescripcion(txtDescripcionEntrenamiento.getText());
            //Actualiza en la base de datos
            GestorBaseDatos.actualizarEntrenamiento(entrenamiento);
            //La temporada debe actualizarse para comprobar si la fecha más reciente ha cambiado
            actualizarTemporada();
            //Recarga la lista de entrenamientos y reselecciona el entrenamiento actualizado
            recargarEntrenamientos(entrenamiento);
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "La fecha o el peso introducido es erroneo", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
        }
    }
    
    //Realiza cambios en el ejercicio seleccionado y los guarda.
    private void actualizarEjercicio(){
        Ejercicio ejercicio = ejercicioSeleccionado.getEjercicio();
        ejercicio.setNombre(txtNombre.getText());
        ejercicio.setVariante(txtVariante.getText());
        ejercicio.setDescripcion(txtDescripcionEjercicio.getText());
        //Actualiza en la base de datos
        GestorBaseDatos.actualizarEjercicio(ejercicio);
        //Recarga la lista de ejercicios y reselecciona el entrenamiento actualizado
        recargarEjercicios(ejercicio);
    }
    
    //Realiza cambios en la serie seleccionada y los guarda
    private void actualizarSerie(){
        Serie serie = serieSeleccionada.getSerie();
        //Try para prevenir formato inválido, en caso de error avisará al usuario de la app
        try{
            float peso = Float.parseFloat(txtPeso.getText());
            int repeticiones = Integer.parseInt(txtRepeticiones.getText());
            serie.setPeso(peso);
            serie.setRepeticiones(repeticiones);
            serie.setExtra(txtExtra.getText());
            //Actualiza en la base de datos
            GestorBaseDatos.actualizarSerie(serie);
            //Recarga la lista de series y reselecciona la serie que ha sido actualizada
            recargarSeries(serie);
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "Peso o repeticiones incorrectos", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
        }
    }
    
    
    //Recarga la lista de temporadas y reselecciona la enviada como parámetro si aún existe
    private void recargarTemporadas(Temporada temporada){
        addTemporadas(GestorBaseDatos.getTemporadas());//Renueva la lista de temporadas
        
        //Recorre la lista de temporadas en busca de la temporada indicada
        for(Component c : panelListaTemporadas.getComponents()){
            if(c instanceof CasillaSeleccionableTemporada){
                //Si esta disponible en la lista de casillas entonces será seleccionada
                if(((CasillaSeleccionableTemporada)c).getTemporada().getId() == temporada.getId())
                    seleccionarTemporada((CasillaSeleccionableTemporada)c);
            }
        }
    }
    
    //Recarga la lista de entrenamientos y reselecciona el enviado como parámetro si aún existe
    private void recargarEntrenamientos(Entrenamiento entrenamiento){
        //Renueva la lista de entrenamientos
        addEntrenamientos(GestorBaseDatos.getEntrenamientos(entrenamiento.getIdTemporada()));
        
        //Recorre la lista de entrenamientos en busca del entrenamiento indicado
        for(Component c : panelListaEntrenamientos.getComponents()){
            if(c instanceof CasillaSeleccionableEntrenamiento){
                //Si esta disponible en la lista de casillas entonces será seleccionado
                if(((CasillaSeleccionableEntrenamiento)c).getEntrenamiento().getId() == entrenamiento.getId())
                seleccionarEntrenamiento((CasillaSeleccionableEntrenamiento)c);
            }
        }
    }
    
    //Recarga la lista de ejercicios y reselecciona el el enviado como parámetro si aún existe
    private void recargarEjercicios(Ejercicio ejercicio){
        //Renueva la lista de ejercicios
        addEjercicios(GestorBaseDatos.getEjercicios(ejercicio.getIdEntrenamiento()));
        
        //Recorre la lista de ejercicios en busca del ejercicio indicado
        for(Component c : panelListaEjercicios.getComponents()){
            if(c instanceof CasillaSeleccionableEjercicio){
                //Si esta disponible en la lista de casillas entonces será seleccionado
                if(((CasillaSeleccionableEjercicio)c).getEjercicio().getId() == ejercicio.getId())
                seleccionarEjercicio((CasillaSeleccionableEjercicio)c);
            }
        }
    }
    
    //Recarga la lista de series y reselecciona la enviada como parámetro si aún existe
    private void recargarSeries(Serie serie){
        //Renueva la lista de series
        addSeries(GestorBaseDatos.getSeries(serie.getIdEjercicio()));
        
        //Recorre la lista de series en busca de la serie indicada
        for(Component c : panelListaSeries.getComponents()){
            if(c instanceof CasillaSeleccionableSerie){
                //Si esta disponible en la lista de casillas entonces será seleccionada
                if(((CasillaSeleccionableSerie)c).getSerie().equals(serie))
                seleccionarSerie((CasillaSeleccionableSerie)c);
            }
        }
    }
    
    //Bloquea o desbloquea los campos y subpaneles del panel temporada
    private void setEnabledPanelTemporada(boolean b ){
        
        //True desbloquea los campos del panel y false los bloquea
        btnAnnadirEntrenamiento.setEnabled(b);
        btnGuardarTemporada.setEnabled(b);
        txtFechaInicio.setEnabled(b);
        txtDescripcionTemporada.setEnabled(b);
        //Si es true usa los colores por defecto
        if(b){
            panelEntrenamiento.setBackground(new Color(180, 160, 255));
            scrollEntrenamientos.setBackground(new Color(100, 80, 180));
            panelListaEntrenamientos.setBackground(Color.WHITE);
            txtFechaInicio.setBackground(Color.WHITE);
            txtDescripcionTemporada.setBackground(Color.WHITE);
        //Si es false usa colores que indican bloqueo
        }else{
            panelEntrenamiento.setBackground(new Color(100, 80, 180));
            scrollEntrenamientos.setBackground(new Color(20, 0, 105));
            panelListaEntrenamientos.setBackground(Color.GRAY);
            txtFechaInicio.setBackground(Color.GRAY);
            txtDescripcionTemporada.setBackground(Color.GRAY);
        }
        //Siempre se bloquearan los subpaneles 
        setEnabledPanelEntrenamiento(false);
    }
    
    //Bloquea o desbloquea los campos y subpaneles del panel entrenamiento
    private void setEnabledPanelEntrenamiento(boolean b ){
        
        //True desbloquea los campos del panel y false los bloquea
        btnAnnadirEjercicio.setEnabled(b);
        btnGuardarEntrenamiento.setEnabled(b);
        txtFecha.setEnabled(b);
        txtPesoCorporal.setEnabled(b);
        txtDescripcionEntrenamiento.setEnabled(b);
        //Si es true usa los colores por defecto
        if(b){
            panelEjercicio.setBackground(new Color(180, 160, 255));
            scrollEjercicios.setBackground(new Color(100, 80, 180));
            panelListaEjercicios.setBackground(Color.WHITE);
            txtFecha.setBackground(Color.WHITE);
            txtPesoCorporal.setBackground(Color.WHITE);
            txtDescripcionEntrenamiento.setBackground(Color.WHITE);
        //Si es false usa colores que indican bloqueo
        }else{
            panelEjercicio.setBackground(new Color(100, 80, 180));
            scrollEjercicios.setBackground(new Color(20, 0, 105));
            panelListaEjercicios.setBackground(Color.GRAY);
            txtFecha.setBackground(Color.GRAY);
            txtPesoCorporal.setBackground(Color.GRAY);
            txtDescripcionEntrenamiento.setBackground(Color.GRAY);
        }
        //Siempre se bloquearan los subpaneles 
        setEnabledPanelEjercicio(false);
    }
    
    //Bloquea o desbloquea los campos y subpaneles del panel ejercicio
    private void setEnabledPanelEjercicio(boolean b ){
        
        //True desbloquea los campos del panel y false los bloquea
        btnAnnadirSerie.setEnabled(b);
        btnGuardarEjercicio.setEnabled(b);
        txtNombre.setEnabled(b);
        txtVariante.setEnabled(b);
        txtDescripcionEjercicio.setEnabled(b);
        //Si es true usa los colores por defecto
        if(b){
            panelSerie.setBackground(new Color(180, 160, 255));
            scrollSeries.setBackground(new Color(100, 80, 180));
            panelListaSeries.setBackground(Color.WHITE);
            txtNombre.setBackground(Color.WHITE);
            txtVariante.setBackground(Color.WHITE);
            txtDescripcionEjercicio.setBackground(Color.WHITE);
        //Si es false usa colores que indican bloqueo
        }else{
            panelSerie.setBackground(new Color(100, 80, 180));
            scrollSeries.setBackground(new Color(20, 0, 105));
            panelListaSeries.setBackground(Color.GRAY);
            txtNombre.setBackground(Color.GRAY);
            txtVariante.setBackground(Color.GRAY);
            txtDescripcionEjercicio.setBackground(Color.GRAY);
        }
        //Siempre se bloquearan los subpaneles 
        setEnabledPanelSerie(false);
    }
    
    //Bloquea o desbloquea los campos y subpaneles del panel de series
    private void setEnabledPanelSerie(boolean b ){
        
        //True desbloquea los campos del panel y false los bloquea
        btnclonarSerie.setEnabled(b);
        btnGuardarSerie.setEnabled(b);
        txtPeso.setEnabled(b);
        txtRepeticiones.setEnabled(b);
        txtExtra.setEnabled(b);
        //Si es true usa los colores por defecto
        if(b){
            txtPeso.setBackground(Color.WHITE);
            txtRepeticiones.setBackground(Color.WHITE);
            txtExtra.setBackground(Color.WHITE);
        //Si es false usa colores que indican bloqueo
        }else{
            txtPeso.setBackground(Color.GRAY);
            txtRepeticiones.setBackground(Color.GRAY);
            txtExtra.setBackground(Color.GRAY);
        }
    }
}
