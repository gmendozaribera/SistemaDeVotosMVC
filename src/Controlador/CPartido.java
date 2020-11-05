/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NPartido;
import Vista.VPartido;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CPartido implements ActionListener {

    private NPartido modelo;
    private VPartido vista;

    public CPartido() {
        this.modelo = new NPartido();
        this.vista = new VPartido();
        vista.setVisible(true);
        initComponente();
    }

    public void initComponente() {
        vista.setVisible(true);
        this.vista.btnGuardar.setActionCommand("btnGuardar");
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.setActionCommand("btnModificar");
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.setActionCommand("btnEliminar");
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBack.setActionCommand("btnBack");
        this.vista.btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String nombre = vista.txtNombre.getText();
        String sigla = vista.txtSigla.getText();
        switch (e.getActionCommand()) {
            case "btnGuardar":
                if (nombre.equals("") || sigla.equals("")) {
                    JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los datos");
                } else {
                    if (!modelo.existePartido(nombre)) {
                        try {
                            modelo.guardar(nombre, sigla);
                            vista.actualizar();
                            JOptionPane.showMessageDialog(null, "Registro Guardado");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Hay un problema al registrar");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "El partido ya existe");
                    }
                }
                break;
            case "btnModificar":
                if (nombre.equals("") || sigla.equals("") || vista.lbIdPartido.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los datos");
                } else {
                    if (!modelo.existePartido(nombre)) {

                        try {
                            modelo.modificar(Integer.valueOf(vista.lbIdPartido.getText()), nombre, sigla);
                            vista.actualizar();
                            JOptionPane.showMessageDialog(null, "Registro Actualizado");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Hay un problema al actualizar");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "El partido ya existe");
                    }
                }
                break;
            case "btnEliminar":
                try {
                    if (!vista.lbIdPartido.getText().equals("")) {
                        modelo.eliminar(Integer.valueOf(vista.lbIdPartido.getText()));
                        JOptionPane.showMessageDialog(null, "Registro Eliminado");
                        vista.actualizar();
                    }else{
                        JOptionPane.showMessageDialog(null, "Seleccione un Partido a eliminar");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hay un problema al eliminar");
                }
                break;
        }
    }

    public static void main(String[] args) {
        new CPartido();  
    }
}
