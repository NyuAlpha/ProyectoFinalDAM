package com.mycompany.diariodeentrenamiento;

import POJOs.Serie;
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
public class PanelFormularioSerie extends JPanel {
      
    private JLabel lblPeso;
    private JLabel lblRepeticiones;
    private JLabel lblExtra;
    private JTextField txtPeso;
    private JTextField txtRepeticiones;
    private JTextField txtExtra;
    
    private JButton btnGuardar;
    
    private PanelSerie panelSerie;
    
     public PanelFormularioSerie(){
        panelSerie= null;
        crearComponentes();
        txtPeso.setText("");
        txtRepeticiones.setText("");
        txtExtra.setText("");
    }
    
    public void recargarCampos(PanelSerie panelSerie){
        txtPeso.setText("");
        txtRepeticiones.setText("");
        txtExtra.setText("");
        this.panelSerie = panelSerie;
        Serie serie = panelSerie.getSerie();
        
        if(serie != null){
            txtPeso.setText(Float.toString(serie.getPeso()));
            txtRepeticiones.setText(Integer.toString(serie.getRepeticiones()));   
            if(serie.getExtra() != null){
                txtExtra.setText(serie.getExtra());
            }
        }
    }
    
    private void actualizarEntrenamiento(){
        Serie serie = panelSerie.getSerie();
        try{
            serie.setPeso(Float.parseFloat(txtPeso.getText()));
            serie.setRepeticiones(Integer.parseInt(txtRepeticiones.getText()));
        }
        catch(java.lang.IllegalArgumentException iae){
            JOptionPane.showMessageDialog(this, "Peso o repeticiones incorrectos", "Formato erroneo",JOptionPane.WARNING_MESSAGE );
        }
        serie.setExtra(txtExtra.getText());
        GestorDatos.actualizarSerie(serie);
        panelSerie.generarTexto();
        panelSerie.updateUI();
        
    }
    
    private void crearComponentes(){
        
        lblPeso = new JLabel ("Peso(kg)   ");
        lblRepeticiones = new JLabel ("Repeticiones   ");
        lblExtra = new JLabel ("Extra   ");
        txtPeso = new JTextField();
        txtRepeticiones = new JTextField();
        txtExtra = new JTextField();
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
                    .addComponent(lblPeso)
                    .addComponent(txtPeso,GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    //.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 45, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRepeticiones)
                    .addComponent(txtRepeticiones,GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(lblExtra)
                    .addComponent(txtExtra,GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)))
            //.addGap(10)
            .addComponent(btnGuardar)
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
            .addComponent(btnGuardar)
        );
        updateUI();
    }
    
    public String getDescripcion(){
        return txtExtra.getText();
    }
}
