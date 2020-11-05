/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NMesa;
import Modelo.NRecinto;
import Vista.VMesa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CMesa implements ActionListener {

    private NMesa modelo;
    private VMesa vista;
    private NRecinto modeloRecinto;

    public CMesa() {
        this.modelo = new NMesa();
        this.vista = new VMesa();
        this.modeloRecinto = new NRecinto();
        
        initComponente();
    }

    public void initComponente() {
        vista.setVisible(true);
        vista.cargarCombo(modeloRecinto.getCombo());
        this.vista.btnGuardar.setActionCommand("btnGuardar");
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.setActionCommand("btnModificar");
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnBack.setActionCommand("btnBack");
        this.vista.btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int cantidad = modelo.getNumero(vista.txtCantidad.getText());
        int idRecinto;
        switch (e.getActionCommand()) {
            case "btnGuardar":
                idRecinto = modeloRecinto.getKey((String) vista.cbRecinto.getSelectedItem());
                if (cantidad == -1 || idRecinto == -1) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los datos correctamente");
                } else {
                    if (modelo.existeRecinto(idRecinto)) {
                        JOptionPane.showMessageDialog(null, "Ya ingresaste datos en este recinto, puedes seleccionar y modificarlo");
                    } else {
                        modelo.guardar(cantidad, idRecinto);
                        vista.actualizar();
                        JOptionPane.showMessageDialog(null, "Registro Guardado");
                    }
                }
                break;
            case "btnModificar":
                idRecinto = modeloRecinto.getKey((String) vista.cbRecinto.getSelectedItem());
                if (cantidad == -1 || idRecinto == -1) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los datos correctamente");
                } else {
                    modelo.modificar(cantidad, idRecinto);
                    vista.actualizar();
                    JOptionPane.showMessageDialog(null, "Registro Actualizado");
                }
                break;
        }
    }
}
