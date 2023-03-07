
package com.mycompany.diariodeentrenamiento;

import POJOs.Entrenamiento;
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
public class PanelFormularioEntrenamiento extends JPanel{
  
    private JLabel lblFecha;
    private JLabel lblPesoCorporal;
    private JLabel lblDescripcion;
    private JTextField txtFecha;
    private JTextField txtPesoCorporal;
    private JTextField txtDescripcion;
    
    private JButton btnGuardar;
    
    private PanelEntrenamiento panelEntrenamiento;
    
     public PanelFormularioEntrenamiento(){
        panelEntrenamiento= null;
        crearComponentes();
        txtFecha.setText("");
        txtPesoCorporal.setText("");
        txtDescripcion.setText("");
    }
    
    public void recargarCampos(PanelEntrenamiento panelEntrenamiento){
        txtFecha.setText("");
        txtPesoCorporal.setText("");
        txtDescripcion.setText("");
        this.panelEntrenamiento = panelEntrenamiento;
        Entrenamiento entrenamiento = panelEntrenamiento.getEntrenamiento();
        
        if(entrenamiento != null){
            if(entrenamiento.getFecha() != null){
                txtFecha.setText(entrenamiento.getFecha().toString());
            }
            txtPesoCorporal.setText(Float.toString(entrenamiento.getPesoCorporal()));   
            if(entrenamiento.getDescripcion() != null){
                txtDescripcion.setText(entrenamiento.getDescripcion());
            }
        }
    }
    
    private void actualizarEntrenamiento(){
        Entrenamiento entrenamiento = panelEntrenamiento.getEntrenamiento();
        try{
            entrenamiento.setFecha(Date.valueOf(txtFecha.getText()));
            entrenamiento.setPesoCorporal(Float.parseFloat(txtPesoCorporal.getText()));
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "La fecha o el peso introducido es erroneo", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
        }
        entrenamiento.setDescripcion(txtDescripcion.getText());
        GestorDatos.actualizarEntrenamiento(entrenamiento);
        panelEntrenamiento.generarTexto();
        panelEntrenamiento.updateUI();
        
    }
    
    private void crearComponentes(){
        
        lblFecha = new JLabel ("Fecha   ");
        lblPesoCorporal = new JLabel ("Peso corporal(kg)   ");
        lblDescripcion = new JLabel ("Descripcion   ");
        txtFecha = new JTextField();
        txtPesoCorporal = new JTextField();
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
                    .addComponent(lblFecha)
                    .addComponent(txtFecha,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblPesoCorporal)
                    .addComponent(txtPesoCorporal,GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblDescripcion)
                    .addComponent(txtDescripcion,GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
            //.addGap(10)
            .addComponent(btnGuardar)
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
