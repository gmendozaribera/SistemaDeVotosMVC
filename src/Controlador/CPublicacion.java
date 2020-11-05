/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NMesa;
import Modelo.NPartido;
import Modelo.NPublicacion;
import Vista.VPublicacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class CPublicacion implements ActionListener {

    NPublicacion modeloPublicacion;
    VPublicacion vistaPublicacion;
    NMesa modeloMesa;
    NPartido modeloPartido;
    int[] usuarioRecinto;

    public CPublicacion(int[] usuarioRecinto) {
        modeloPublicacion = new NPublicacion();
        vistaPublicacion = new VPublicacion();
        modeloMesa = new NMesa();
        modeloPartido = new NPartido();
        this.usuarioRecinto = usuarioRecinto;
        initComponente();

    }

    public void initComponente() {
        vistaPublicacion.setVisible(true);
        vistaPublicacion.cargarComboMesa(modeloMesa.getCombo(usuarioRecinto[0]));
        vistaPublicacion.cargarComboPartido(modeloPartido.getCombo());
        this.vistaPublicacion.btnGuardar.setActionCommand("btnGuardar");
        this.vistaPublicacion.btnGuardar.addActionListener(this);
        this.vistaPublicacion.btnBack.setActionCommand("btnBack");
        this.vistaPublicacion.btnBack.addActionListener(this);
        this.vistaPublicacion.btnAgregar.setActionCommand("btnAgregar");
        this.vistaPublicacion.btnAgregar.addActionListener(this);
        this.vistaPublicacion.btnQuitar.setActionCommand("btnQuitar");
        this.vistaPublicacion.btnQuitar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnGuardar": {
                if (vistaPublicacion.tbDetalle.getRowCount() > 0) {
                    int idMesa = modeloMesa.getKey(vistaPublicacion.cbMesa.getSelectedItem().toString(), usuarioRecinto[1]);
                    modeloPublicacion.guardar(idMesa, vistaPublicacion.getCantidadTotal());
                    modeloPublicacion.guardarDetalle(vistaPublicacion.getListaIdPartido(), vistaPublicacion.getListaCantidad());
                    vistaPublicacion.actualizar();

                } else {
                    JOptionPane.showMessageDialog(null, "No hay detalle de publicacion para guardar", "Atencion", 1);
                }
            }
            break;
            case "btnAgregar": {
                vistaPublicacion.cbMesa.setEnabled(false);
                int idPartido = modeloPartido.getKey(vistaPublicacion.getPartido());
                DefaultTableModel modelo = (DefaultTableModel) vistaPublicacion.tbDetalle.getModel();
                modelo.addRow(new Object[]{vistaPublicacion.getNroMesa(), vistaPublicacion.getPartido(), idPartido, vistaPublicacion.getCantidad()});
                vistaPublicacion.tbDetalle.setModel(modelo);
            }
            break;
            case "btnQuitar": {
                if (vistaPublicacion.tbDetalle.getRowCount() > 0) {
                    if (!(vistaPublicacion.tbDetalle.getSelectedRow() < 0)) {
                        DefaultTableModel modelo = (DefaultTableModel) vistaPublicacion.tbDetalle.getModel();
                        modelo.removeRow(vistaPublicacion.tbDetalle.getSelectedRow());
                        if (vistaPublicacion.tbDetalle.getRowCount() == 0) {
                            vistaPublicacion.cbMesa.setEnabled(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una fila del detalle de la publicacion", "Atencion", JOptionPane.WARNING_MESSAGE, null);
                    }
                } else {
                    vistaPublicacion.cbMesa.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "No existe ningun objeto de resultado a quitar", "Atencion", JOptionPane.WARNING_MESSAGE, null);
                }
            }
            break;
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VPublicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new PPublicacion().setVisible(true);
                int[] usuarioRecinto = new int[2];
                usuarioRecinto[0] = 4;
                usuarioRecinto[1] = 2;

                new CPublicacion(usuarioRecinto);
            }
        });
    }

}
