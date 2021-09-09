
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.CRUD_model;
import model.Serie_model;
import view.Series_view;

public class Series_control implements ActionListener{
    
    private Serie_model modPro;
    private CRUD_model modCts;
    private Series_view viewPro;
    DefaultTableModel model = new DefaultTableModel();

    public Series_control(Serie_model modPro, CRUD_model modCts, Series_view viewPro) {
        this.modPro = modPro;
        this.modCts = modCts;
        this.viewPro = viewPro;
        this.viewPro.jButtonGuardar.addActionListener(this);
        this.viewPro.jButtonModificar.addActionListener(this);
        this.viewPro.jButtonEliminar.addActionListener(this);
        this.viewPro.jButtonBuscar.addActionListener(this);
        this.viewPro.jButtonLimpiar.addActionListener(this);
        this.viewPro.jButtonListar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== viewPro.jButtonGuardar){
            
            modPro.setidSerie(Integer.parseInt(viewPro.jTFidSerie.getText()));
            modPro.setTitulo(viewPro.jTFTitulo.getText());
            modPro.setTemporadas(Integer.parseInt(viewPro.jTFTemporadas.getText()));
            modPro.setEpisodios(Double.parseDouble(viewPro.jTFEpisodios.getText()));
              
             if(modCts.registrar(modPro)) {
                JOptionPane.showMessageDialog(null,"Serie guardada correctamente");
                limpiar();
             }else{
                JOptionPane.showMessageDialog(null,"Error en el guardado");
             }
        }
        if(e.getSource()== viewPro.jButtonModificar){
            modPro.setidSerie(Integer.parseInt(viewPro.jTFidSerie.getText()));
            modPro.setTitulo(viewPro.jTFTitulo.getText());
            modPro.setTemporadas(Integer.parseInt(viewPro.jTFTemporadas.getText()));
            modPro.setEpisodios(Double.parseDouble(viewPro.jTFEpisodios.getText()));
             if(modCts.modificar(modPro)) {
                JOptionPane.showMessageDialog(null,"Serie actualizada");
                limpiar();
             }else{
                JOptionPane.showMessageDialog(null,"Error al actualizar serie");
             }
        }
        
        if(e.getSource()== viewPro.jButtonEliminar){
            modPro.setidSerie(Integer.parseInt(viewPro.jTFidSerie.getText()));

             if(modCts.eliminar(modPro)) {
                JOptionPane.showMessageDialog(null,"Serie eliminada");
                limpiar();
             }else{
                JOptionPane.showMessageDialog(null,"Error al eliminar serie");
             }
        }
        
        if(e.getSource()== viewPro.jButtonBuscar){
            modPro.setidSerie(Integer.parseInt(viewPro.jTFidSerie.getText()));

             if(modCts.buscar(modPro)) {
                viewPro.jTFTitulo.setText(String.valueOf(modPro.getTitulo()));
                viewPro.jTFTemporadas.setText(String.valueOf(modPro.getTemporadas()));
                viewPro.jTFEpisodios.setText(String.valueOf(modPro.getEpisodios()));               
             }else{
                JOptionPane.showMessageDialog(null,"No se encontro registro");
             }
        }
        
        if(e.getSource()== viewPro.jButtonLimpiar){
            limpiar();
        }
        
        if(e.getSource()== viewPro.jButtonListar){
            listar(viewPro.tabla);
        }
    }
    
    public void iniciar(){
    
        viewPro.setTitle("Series");
        viewPro.setLocationRelativeTo(null);
    
    }
    
    public void limpiar(){
        viewPro.jTFidSerie.setText(null);
        viewPro.jTFTitulo.setText(null);
        viewPro.jTFTemporadas.setText(null);
        viewPro.jTFEpisodios.setText(null);
        DefaultTableModel tb = (DefaultTableModel)viewPro.tabla.getModel();
        int a = viewPro.tabla.getRowCount()-1;
        for (int i = a; i >= 0; i--) {          
         tb.removeRow(tb.getRowCount()-1);
        }
        viewPro.jButtonListar.setVisible(true);
    }
    
    public void listar(JTable tabla){
        model=(DefaultTableModel)tabla.getModel();
        List<Serie_model>lista= modCts.listar();
        Object[] objeto= new Object[4];
        for(int i=0; i<lista.size();i++){
            objeto[0]=lista.get(i).getidSerie();
            objeto[1]=lista.get(i).getTitulo();
            objeto[2]=lista.get(i).getTemporadas();
            objeto[3]=lista.get(i).getEpisodios();
            model.addRow(objeto);
        }
        viewPro.tabla.setModel(model);
        viewPro.jButtonListar.setVisible(false);

    }
    
    
    
}
