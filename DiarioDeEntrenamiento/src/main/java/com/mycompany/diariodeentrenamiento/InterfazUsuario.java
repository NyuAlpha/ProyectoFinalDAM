package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import POJOs.Entrenamiento;
import POJOs.Serie;
import POJOs.Temporada;
import java.awt.Color;
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
    private JPanel panelTemporada;
    private JPanel panelEntrenamiento;
    private JPanel panelEjercicio;
    private JPanel panelSerie;
    

    private JPanel panelListaTemporadas;
    private JPanel panelListaEntrenamientos;
    private JPanel panelListaEjercicios;
    private JPanel panelListaSeries;
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

    
/*=======================================================================
    Variables del panel del formulario Temporada
=========================================================================*/

    private JPanel panelFormularioTemporada;
    private JLabel lblTemporadas;
    private JLabel lblFechaInicio;
    private JLabel lblFechaFin;
    private JLabel lblDescripcionTemporada;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtDescripcionTemporada;
    private JButton btnGuardarTemporada;
    private JButton btnAnnadirTemporada;
    
/*=======================================================================
    Variables del panel del formulario Entrenamiento
=========================================================================*/
    private JPanel panelFormularioEntrenamiento;
    private JLabel lblFecha;
    private JLabel lblPesoCorporal;
    private JLabel lblDescripcionEntrenamiento;
    private JTextField txtFecha;
    private JTextField txtPesoCorporal;
    private JTextField txtDescripcionEntrenamiento;
    private JButton btnGuardarEntrenamiento;
    private JButton btnAnnadirEntrenamiento;
    
/*=======================================================================
    Variables del panel del formulario Ejercicio
=========================================================================*/
    private JPanel panelFormularioEjercicio;
    private JLabel lblNombre;
    private JLabel lblVariante;
    private JLabel lblDescripcionEjercicio;
    private JTextField txtNombre;
    private JTextField txtVariante;
    private JTextField txtDescripcionEjercicio;
    private JButton btnGuardarEjercicio;
    private JButton btnAnnadirEjercicio;
    
/*=======================================================================
    Variables del panel del formulario Serie
=========================================================================*/
    private JPanel panelFormularioSerie;
    private JLabel lblPeso;
    private JLabel lblRepeticiones;
    private JLabel lblExtra;
    private JTextField txtPeso;
    private JTextField txtRepeticiones;
    private JTextField txtExtra;
    private JButton btnGuardarSerie;
    private JButton btnAnnadirSerie;
    
    
    public static void main(String[] args){
        new InterfazUsuario();
    }
    
/*=======================================================================
    Métodos de la clase
=========================================================================*/
    
    public InterfazUsuario(){
        
        crearFormularioTemporada();
        crearFormularioEntrenamiento();
        crearFormularioEjercicio();
        crearFormularioSerie();
        crearPanelEdicion();
        crearPanelBusqueda();
        
        annadirTemporadas(GestorBaseDatos.getTemporadas());
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("Editor", panelEdicion);
        tabbedPane.addTab("Buscador", panelBusqueda);
        add(tabbedPane);
        
        setBounds(100,100, 1200, 700);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void crearPanelBusqueda(){
        
        panelBusqueda = new JPanel();
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
        
        actualizarComboBoxEjercicio();
        actualizarComboBoxEjercicio();

        btnFiltrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrar();
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
            .addComponent(textA)
            .addComponent(textB)
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
            .addComponent(textA)
            .addComponent(textB)
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
        filtro.setPesoCorporalMinimo(txtPesoCorporalMinimo.getText());
        filtro.setPesoCorporalMaximo(txtPesoCorporalMaximo.getText());
        filtro.setPesoMinimo(txtPesoMinimo.getText());
        filtro.setPesoMaximo(txtPesoMaximo.getText());
        ArrayList<Ejercicio> ejercicios = GestorBaseDatos.getConsulta(filtro.crearQuery());
            textA.setText("");
        for(Ejercicio e : ejercicios){
            textA.append(e.toString() + e.resumenSeries() +"\n");
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
        
        

        panelListaEntrenamientos = new JPanel();
        panelListaEjercicios = new JPanel();
        panelListaSeries = new JPanel();
        
        scrollEntrenamientos = new JScrollPane();
        scrollEjercicios = new JScrollPane();
        scrollSeries = new JScrollPane();
        
        btnAnnadirEjercicio = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        btnAnnadirSerie = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));  
        
        //==================================================================================================
        
        //Estableciendo todos los SplitPane de la pantalla
        panelEdicion.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        panelEdicion.setRightComponent(panelEjercicioSerie);
        panelEdicion.setLeftComponent(panelTemporadaEntrenamiento);
        panelEdicion.setDividerLocation(400);
        
        panelTemporadaEntrenamiento.setOrientation(JSplitPane.VERTICAL_SPLIT);
        panelTemporadaEntrenamiento.setTopComponent(panelFormularioTemporada);
        panelTemporadaEntrenamiento.setBottomComponent(panelEntrenamiento);
        panelTemporadaEntrenamiento.setDividerLocation(300);
        
        panelEjercicioSerie.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        panelEjercicioSerie.setRightComponent(panelSerie);
        panelEjercicioSerie.setLeftComponent(panelEjercicio);
        panelEjercicioSerie.setDividerLocation(450);
        
        //Anidando componentes

        panelEntrenamiento.setLayout(new BoxLayout(panelEntrenamiento,BoxLayout.Y_AXIS));
        panelEjercicio.setLayout(new BoxLayout(panelEjercicio,BoxLayout.Y_AXIS));
        panelSerie.setLayout(new BoxLayout(panelSerie,BoxLayout.Y_AXIS));
        
        //Colocando formularios en sus paneles

        panelEntrenamiento.add(panelFormularioEntrenamiento);
        panelEjercicio.add(panelFormularioEjercicio);
        panelSerie.add(panelFormularioSerie);
        
        //Colocando etiquetas informativas 


        panelEntrenamiento.add(new JLabel("Entrenamientos"));
        panelEjercicio.add(new JLabel("Ejercicios"));
        panelSerie.add(new JLabel("Series"));
        
        //Anidando el scrollPane a sus paneles correspondientes

        panelEntrenamiento.add(scrollEntrenamientos);
        panelEjercicio.add(scrollEjercicios);
        panelSerie.add(scrollSeries);
        
        //Anidando los paneles de listas a los scrollPane

        scrollEntrenamientos.setViewportView(panelListaEntrenamientos);
        scrollEjercicios.setViewportView(panelListaEjercicios);
        scrollSeries.setViewportView(panelListaSeries);
        
        //Estableciendo un boxlayout vertical como layout de los paneles de listas
        panelListaTemporadas.setLayout(new BoxLayout(panelListaTemporadas,BoxLayout.Y_AXIS));
        panelListaEntrenamientos.setLayout(new BoxLayout(panelListaEntrenamientos,BoxLayout.Y_AXIS));
        panelListaEjercicios.setLayout(new BoxLayout(panelListaEjercicios,BoxLayout.Y_AXIS));
        panelListaSeries.setLayout(new BoxLayout(panelListaSeries,BoxLayout.Y_AXIS));
    }
    
    //Métodos de creación de paneles de formulario
    
    private void crearFormularioTemporada(){
        panelFormularioTemporada = new JPanel();
        lblTemporadas = new JLabel("Temporadas");
        lblFechaInicio = new JLabel ("Fecha de inicio   ");
        lblFechaFin = new JLabel ("Fecha de finalización   ");
        lblDescripcionTemporada = new JLabel ("Descripcion   ");
        txtFechaInicio = new JTextField();
        txtFechaFin = new JTextField();
        txtDescripcionTemporada = new JTextField();
        btnGuardarTemporada = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnAnnadirTemporada = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        panelListaTemporadas = new JPanel();
        scrollTemporadas = new JScrollPane();
        
        
        panelFormularioTemporada.setBackground(new Color(180, 160, 255));
        scrollTemporadas.setViewportView(panelListaTemporadas);
        
        btnGuardarTemporada.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirTemporada.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
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
        
        GroupLayout layout = new GroupLayout(panelFormularioTemporada);
        panelFormularioTemporada.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaInicio)
                    .addComponent(lblFechaFin)
                    .addComponent(lblDescripcionTemporada)  
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaInicio,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionTemporada,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                )
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarTemporada)
                    .addComponent(btnAnnadirTemporada)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
            )
            .addComponent(lblTemporadas)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollTemporadas)
                .addContainerGap()
            )
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFechaInicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaFin,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcionTemporada,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(txtDescripcionTemporada,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)               
                    .addComponent(txtFechaInicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(btnGuardarTemporada)
                    .addComponent(btnAnnadirTemporada)
                )
            )
            .addGap(20)
            .addComponent(lblTemporadas)
            .addComponent(scrollTemporadas)
        );
    }
    
    private void crearFormularioEntrenamiento(){
        
        panelFormularioEntrenamiento = new JPanel();
        lblFecha = new JLabel ("Fecha   ");
        lblPesoCorporal = new JLabel ("Peso corporal(kg)   ");
        lblDescripcionEntrenamiento = new JLabel ("Descripcion   ");
        txtFecha = new JTextField();
        txtPesoCorporal = new JTextField();
        txtDescripcionEntrenamiento = new JTextField();
        btnGuardarEntrenamiento = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnAnnadirEntrenamiento = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        
        btnGuardarEntrenamiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirEntrenamiento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
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
        
        GroupLayout layout = new GroupLayout(panelFormularioEntrenamiento);
        panelFormularioEntrenamiento.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFecha)
                    .addComponent(txtFecha,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblPesoCorporal)
                    .addComponent(txtPesoCorporal,GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblDescripcionEntrenamiento)
                    .addComponent(txtDescripcionEntrenamiento,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(btnGuardarEntrenamiento)
                .addComponent(btnAnnadirEntrenamiento)
            )
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblPesoCorporal,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPesoCorporal,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionEntrenamiento,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionEntrenamiento,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnGuardarEntrenamiento)
                .addComponent(btnAnnadirEntrenamiento)
            )
        );
    }
    
    private void crearFormularioEjercicio(){
        
        panelFormularioEjercicio = new JPanel();
        lblNombre = new JLabel ("Nombre   ");
        lblVariante = new JLabel ("Variante   ");
        lblDescripcionEjercicio = new JLabel ("Descripcion   ");
        txtNombre = new JTextField();
        txtVariante = new JTextField();
        txtDescripcionEjercicio = new JTextField();
        btnGuardarEjercicio = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnAnnadirEjercicio = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        
        btnGuardarEjercicio.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirEjercicio.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        btnGuardarEjercicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEntrenamiento();
            }
        });
        btnAnnadirEjercicio.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoEjercicio();
            }
        });
        
        GroupLayout layout = new GroupLayout(panelFormularioEjercicio);
        panelFormularioEjercicio.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblNombre)
                    .addComponent(txtNombre,GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblVariante)
                    .addComponent(txtVariante,GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblDescripcionEjercicio)
                    .addComponent(txtDescripcionEjercicio,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(btnGuardarEjercicio)
                .addComponent(btnAnnadirEjercicio)
            )
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblVariante,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVariante,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))              
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescripcionEjercicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionEjercicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnGuardarEjercicio)
                .addComponent(btnAnnadirEjercicio)
            )
        );           
    }
    
    private void crearFormularioSerie(){
        panelFormularioSerie = new JPanel();
        lblPeso = new JLabel ("Peso(kg)   ");
        lblRepeticiones = new JLabel ("Repeticiones   ");
        lblExtra = new JLabel ("Extra   ");
        txtPeso = new JTextField();
        txtRepeticiones = new JTextField();
        txtExtra = new JTextField();
        btnGuardarSerie = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnAnnadirSerie = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\add.png"));
        
        btnGuardarSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnAnnadirSerie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        btnGuardarSerie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEntrenamiento();
            }
        });
        btnAnnadirSerie.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevaSerie();
            }
        });        
        
        GroupLayout layout = new GroupLayout(panelFormularioSerie);
        panelFormularioSerie.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblPeso)
                    .addComponent(txtPeso,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRepeticiones)
                    .addComponent(txtRepeticiones,GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblExtra)
                    .addComponent(txtExtra,GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
            .addGap(20)
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(btnGuardarSerie)
                .addComponent(btnAnnadirSerie)
            )
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblPeso,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPeso,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRepeticiones,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRepeticiones,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblExtra,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExtra,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnGuardarSerie)
                .addComponent(btnAnnadirSerie)
            )
        );
    }
    
    /*=========================================================================================
            Métodos de actualización de registros y formulario
    ===========================================================================================*/
    
    private void recargarCamposTemporada(){
        clearTemporada();
        if(temporadaSeleccionada != null){
            Temporada temporada = temporadaSeleccionada.getTemporada();
            if(temporada != null){
                if(temporada.getFechaInicio() != null){
                    txtFechaInicio.setText(temporada.getFechaInicio().toString());
                }
                if(temporada.getFechaFin() != null){
                    txtFechaFin.setText(temporada.getFechaFin().toString());
                }      
                if(temporada.getDescripcion() != null){
                    txtDescripcionTemporada.setText(temporada.getDescripcion());
                }
            }   
        }
    }
    
    private void actualizarTemporada(){
        Temporada temporada = temporadaSeleccionada.getTemporada();
        try{
            temporada.setFechaInicio(Date.valueOf(txtFechaInicio.getText()));
            temporada.setFechaFin(Date.valueOf(txtFechaInicio.getText()));
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "La fecha introducida es erronea", "Fecha erronea",JOptionPane.WARNING_MESSAGE );
        }
        temporada.setDescripcion(txtDescripcionTemporada.getText());
        GestorBaseDatos.actualizarTemporada(temporada);
        temporadaSeleccionada.generarTexto();
        temporadaSeleccionada.updateUI();  
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
    
    private void actualizarEntrenamiento(){
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
        entrenamientoSeleccionado.generarTexto();
        entrenamientoSeleccionado.updateUI();
        
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
    
    private void actualizarEjercicio(){
        Ejercicio ejercicio = ejercicioSeleccionado.getEjercicio();
        ejercicio.setNombre(txtNombre.getText());
        ejercicio.setVariante(txtVariante.getText());
        ejercicio.setDescripcion(txtDescripcionEjercicio.getText());
        GestorBaseDatos.actualizarEjercicio(ejercicio);
        ejercicioSeleccionado.generarTexto();
        ejercicioSeleccionado.updateUI();
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
    
    private void actualizarSerie(){
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
        txtFechaFin.setText("");
        txtDescripcionTemporada.setText("");
        panelListaEntrenamientos.removeAll();
        panelListaEntrenamientos.updateUI();
        clearEntrenamiento();
    }
    private void clearEntrenamiento(){
        txtFecha.setText("");
        txtPesoCorporal.setText("");
        txtDescripcionEntrenamiento.setText("");
        panelListaEjercicios.removeAll();
        panelListaEjercicios.updateUI();
        clearEjercicio();
    }
    private void clearEjercicio(){
        txtNombre.setText("");
        txtVariante.setText("");
        txtDescripcionEjercicio.setText("");
        panelListaSeries.removeAll();
        panelListaSeries.updateUI();
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
        if( ! txtFechaInicio.getText().isEmpty())
            temporada.setFechaInicio(Date.valueOf(txtFechaInicio.getText()));
        else{
            temporada.setFechaInicio((Date)Date.valueOf(LocalDate.now()));    
        }
        if( ! txtFechaFin.getText().isEmpty())
            temporada.setFechaFin(Date.valueOf(txtFechaFin.getText()));

        temporada.setDescripcion(txtDescripcionTemporada.getText());
        
        GestorBaseDatos.nuevaTemporada(temporada);
        seleccionar( new PanelTemporada(temporada,this,this));
        panelListaTemporadas.add(temporadaSeleccionada);
        panelListaTemporadas.updateUI();
    }
    
    private void nuevoEntrenamiento(){
        if(temporadaSeleccionada != null){
            Entrenamiento entrenamiento = new Entrenamiento();
            if( ! txtFecha.getText().isEmpty())
                entrenamiento.setFecha(Date.valueOf(txtFecha.getText()));
            else{
                entrenamiento.setFecha((Date)Date.valueOf(LocalDate.now()));    
            }
            if( ! txtPesoCorporal.getText().isEmpty())
                entrenamiento.setPesoCorporal(Float.parseFloat(txtPesoCorporal.getText()));

            entrenamiento.setDescripcion(txtDescripcionEntrenamiento.getText());
            entrenamiento.setIdTemporada(temporadaSeleccionada.getTemporada().getId());

            GestorBaseDatos.nuevoEntrenamiento(entrenamiento);
            seleccionar(new PanelEntrenamiento(entrenamiento,this,this));
            panelListaEntrenamientos.add(entrenamientoSeleccionado);
            panelListaEntrenamientos.updateUI();
        }
        else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ninguna temporada seleccionada", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void nuevoEjercicio(){
        if(entrenamientoSeleccionado != null){
            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setNombre(txtNombre.getText());
            ejercicio.setVariante(txtVariante.getText());
            ejercicio.setDescripcion(txtDescripcionEjercicio.getText());
            ejercicio.setIdEntrenamiento(entrenamientoSeleccionado.getEntrenamiento().getId());

            GestorBaseDatos.nuevoEjercicio(ejercicio);
            seleccionar(new PanelEjercicio(ejercicio,this,this));
            panelListaEjercicios.add(ejercicioSeleccionado);
            panelListaEjercicios.updateUI();
        }
        else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún entrenamiento seleccionado", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
    private void nuevaSerie(){
        if(ejercicioSeleccionado != null){
            Serie serie = new Serie();
            
            if( ! txtPeso.getText().isEmpty())
                serie.setPeso(Float.parseFloat(txtPeso.getText()));
            if( ! txtRepeticiones.getText().isEmpty())
                serie.setRepeticiones(Integer.parseInt(txtRepeticiones.getText()));
            
            serie.setExtra(txtExtra.getText());
            serie.setIdEjercicio(ejercicioSeleccionado.getEjercicio().getId());

            GestorBaseDatos.nuevaSerie(serie);
            seleccionar(new PanelSerie(serie,this,this));
            panelListaSeries.add(serieSeleccionada);
            panelListaSeries.updateUI();
        }
        else{
            JOptionPane.showMessageDialog(new JPanel(), "No hay ningún ejercicio seleccionado", "", JOptionPane.INFORMATION_MESSAGE);
        }        
    }
}
