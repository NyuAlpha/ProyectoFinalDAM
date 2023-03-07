package com.mycompany.diariodeentrenamiento;

import POJOs.Temporada;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author victo
 */
public class PanelFormularioTemporada extends JPanel{
    
    private JLabel lblFechaInicio;
    private JLabel lblFechaFin;
    private JLabel lblDescripcion;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;
    private JTextField txtDescripcion;
    private JButton btnGuardar;
    
    private PanelTemporada panelTemporada;
    
    public PanelFormularioTemporada(){
        panelTemporada = null;
        crearComponentes();
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtDescripcion.setText("");
    }
    
    public void recargarCampos(PanelTemporada panelTemporada){
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        txtDescripcion.setText("");
        this.panelTemporada = panelTemporada;
        Temporada temporada = panelTemporada.getTemporada();
        
        if(temporada != null){
            if(temporada.getFechaInicio() != null){
                txtFechaInicio.setText(temporada.getFechaInicio().toString());
            }
            if(temporada.getFechaFin() != null){
                txtFechaFin.setText(temporada.getFechaFin().toString());
            }      
            if(temporada.getDescripcion() != null){
                txtDescripcion.setText(temporada.getDescripcion());
            }
        }
    }
    
    private void actualizarTemporada(){
        Temporada temporada = panelTemporada.getTemporada();
        try{
            temporada.setFechaInicio(Date.valueOf(txtFechaInicio.getText()));
            temporada.setFechaFin(Date.valueOf(txtFechaInicio.getText()));
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "La fecha introducida es erronea", "Fecha erronea",JOptionPane.WARNING_MESSAGE );
        }
        temporada.setDescripcion(txtDescripcion.getText());
        GestorDatos.actualizarTemporada(temporada);
        panelTemporada.generarTexto();
        panelTemporada.updateUI();
        
    }
    
    private void crearComponentes(){
        
        lblFechaInicio = new JLabel ("Fecha de inicio   ");
        lblFechaFin = new JLabel ("Fecha de finalización   ");
        lblDescripcion = new JLabel ("Descripcion   ");
        txtFechaInicio = new JTextField();
        txtFechaFin = new JTextField();
        txtDescripcion = new JTextField();
        btnGuardar = new JButton(new ImageIcon(".\\src\\main\\java\\iconos\\save.png"));
        btnGuardar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTemporada();
            }
        });
        
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFechaInicio)
                    .addComponent(txtFechaInicio,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblFechaFin)
                    .addComponent(txtFechaFin,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
            //.addGap(10)
            .addComponent(btnGuardar)
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaInicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaInicio,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaFin,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaFin,javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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
