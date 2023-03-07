package com.mycompany.diariodeentrenamiento;

import POJOs.Ejercicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author victo
 */
public class PanelFormularioEjercicio extends JPanel{
    private JLabel lblNombre;
    private JLabel lblVariante;
    private JLabel lblDescripcion;
    private JTextField txtNombre;
    private JTextField txtVariante;
    private JTextField txtDescripcion;
    
    private JButton btnGuardar;
    
    private PanelEjercicio panelEjercicio;
    
     public PanelFormularioEjercicio(){
        panelEjercicio= null;
        crearComponentes();
        txtNombre.setText("");
        txtVariante.setText("");
        txtDescripcion.setText("");
    }
    
    public void recargarCampos(PanelEjercicio panelEjercicio){
        txtNombre.setText("Escriba aquí");
        txtVariante.setText("");
        txtDescripcion.setText("");
        this.panelEjercicio = panelEjercicio;
        Ejercicio ejercicio = panelEjercicio.getEjercicio();
        
        if(ejercicio != null){
            if(ejercicio.getNombre() != null){
                txtNombre.setText(ejercicio.getNombre());
            }
            if(ejercicio.getVariante() != null){
                txtVariante.setText(ejercicio.getVariante()); 
            }
            if(ejercicio.getDescripcion() != null){
                txtDescripcion.setText(ejercicio.getDescripcion());
            }
        }
    }
    
    private void actualizarEntrenamiento(){
        Ejercicio ejercicio = panelEjercicio.getEjercicio();
        ejercicio.setNombre(txtNombre.getText());
        ejercicio.setVariante(txtVariante.getText());
        ejercicio.setDescripcion(txtDescripcion.getText());
        GestorDatos.actualizarEjercicio(ejercicio);
        panelEjercicio.generarTexto();
        panelEjercicio.updateUI();
        
    }
    
    private void crearComponentes(){
        
        lblNombre = new JLabel ("Nombre   ");
        lblVariante = new JLabel ("Variante   ");
        lblDescripcion = new JLabel ("Descripcion   ");
        txtNombre = new JTextField();
        txtVariante = new JTextField();
        txtDescripcion = new JTextField();
        btnGuardar = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEntrenamiento();
            }
        });
        
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
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
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
            //.addGap(10)
            .addComponent(btnGuardar)
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
                    .addComponent(lblDescripcion,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(btnGuardar)
        );
        updateUI();
    }
    
    public String getDescripcion(){
        return txtDescripcion.getText();
    }
}
