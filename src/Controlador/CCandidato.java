/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NCandidato;
import Modelo.NCandidatura;
import Modelo.NPartido;

import Vista.VCandidato;
import Vista.VIndex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CCandidato implements ActionListener {

    NCandidato modelo;
    VCandidato vista;
    NCandidatura modeloCandidatura;
    NPartido modeloPartido;

    public CCandidato() {
        modelo = new NCandidato();
        vista = new VCandidato();
        modeloCandidatura = new NCandidatura();
        modeloPartido = new NPartido();
        initComponente();
    }

    public void initComponente() {
        vista.setVisible(true);
        vista.cargarComboCandidatura(modeloCandidatura.getCombo());
        vista.cargarComboPartido(modeloPartido.getCombo());
        this.vista.btnGuardar.setActionCommand("btnGuardar");
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnModificar.setActionCommand("btnModificar");
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.setActionCommand("btnEliminar");
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnBack.setActionCommand("btnBack");
        this.vista.btnBack.addActionListener(this);
        int[] anchos = {60, 80, 120, 120, 60, 140, 140};
        for (int i = 0; i < vista.tbCandidato.getColumnCount(); i++) {
            vista.tbCandidato.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nombre = vista.txtNombre.getText();
        String paterno = vista.txtPaterno.getText();
        String materno = vista.txtMaterno.getText();
        int idCandidatura = modeloCandidatura.getKey((String) vista.cbCandidatura.getSelectedItem());
        int idPartido = modeloPartido.getKey((String) vista.cbPartido.getSelectedItem());

        switch (e.getActionCommand()) {
            case "btnGuardar":

                if (vista.txtPaterno.equals("") || vista.txtCi.equals("") || vista.txtNombre.equals("")
                        || vista.cbCandidatura.getSelectedIndex() == 0 || vista.cbPartido.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Revise que esten correctamente llenados los campos", "Falta de datos", 0, null);
                } else {
                    modelo.guardar(nombre, paterno, materno, Integer.valueOf(vista.txtCi.getText()), idCandidatura, idPartido);
                    vista.actualizar();
                    JOptionPane.showMessageDialog(null, "Registro guardado exitosamente");
                }

                break;
            case "btnModificar":
                if (vista.lbIdCandidato.getText().equals("") || vista.txtPaterno.equals("") || vista.txtCi.equals("") || vista.txtNombre.equals("")
                        || vista.cbCandidatura.getSelectedIndex() == 0 || vista.cbPartido.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Revise que esten correctamente llenados los campos", "Falta de datos", 0, null);
                } else {
                    modelo.modificar(Integer.valueOf(vista.lbIdCandidato.getText()),nombre, paterno, materno, Integer.valueOf(vista.txtCi.getText()), idCandidatura, idPartido);
                    vista.actualizar();
                    JOptionPane.showMessageDialog(null, "Registro actualizado exitosamente");
                }

                break;
            case "btnEliminar":
                try {
                    if (!vista.lbIdCandidato.getText().equals("")) {
                        modelo.eliminar(Integer.valueOf(vista.lbIdCandidato.getText()));
                        JOptionPane.showMessageDialog(null, "Registro Eliminado");
                        vista.actualizar();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hay un problema al eliminar");
                }
                break;
        }
    }
}
