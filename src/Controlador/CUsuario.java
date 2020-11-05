/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NCargo;
import Modelo.NRecinto;
import Modelo.NUsuario;
import Vista.VIndex;
import Vista.VUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CUsuario implements ActionListener {

    NUsuario modelo;
    NCargo modeloCargo;
    NRecinto modeloRecinto;
    VUsuario vista;

    int privilegio;

    public CUsuario(int privilegio) {

        this.modelo = new NUsuario();
        this.modeloCargo = new NCargo();
        this.modeloRecinto = new NRecinto();
        this.vista = new VUsuario(privilegio);
        this.privilegio = privilegio;
        vista.setVisible(true);
        initComponente();
    }

    public void initComponente() {
        vista.cargarComboCargo(modeloCargo.getCombo());
        vista.cargarComboRecinto(modeloRecinto.getCombo());
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

        int idUsuario = modelo.getNumero(vista.lbIdUsuario.getText());
        String nombre = vista.txtNombre.getText();
        String paterno = vista.txtApPat.getText();
        String materno = vista.txtApMat.getText();
        int ci = modelo.getNumero(vista.txtCi.getText());
        String correo = vista.txtCorreo.getText();
        int telefono = modelo.getNumero(vista.txtTelefono.getText());
        String user = vista.txtUsuario.getText();
        String pass = new String(vista.txtPassword.getPassword());
        switch (e.getActionCommand()) {
            case "btnGuardar":
                if (nombre.equals("") || paterno.equals("") || ci < 1000
                        || correo.equals("") || user.equals("") || pass.equals("") || vista.cbCargo.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los datos");
                } else {
                    if (!modelo.existeUsuario(user)) {
                        if (modelo.esEmail(correo)) {
                            int cargo = modeloCargo.getKey((String) vista.cbCargo.getSelectedItem());
                            if (privilegio < cargo || privilegio == 1) {
                                int recinto = modeloRecinto.getKey((String) vista.cbRecinto.getSelectedItem());
                                String nuevoPass = modelo.sha1(pass);
                                try {

                                    modelo.guardar(nombre, paterno, materno, correo, user, nuevoPass, telefono, ci, cargo, recinto);
                                    vista.actualizar();
                                    JOptionPane.showMessageDialog(null, "Registro Guardado");
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Hay un problema al registrar");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No tienes privilegio para registrar alguien del mismo cargo");
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "El email es incorrecto");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe");
                    }
                }
                break;
            case "btnModificar":
                if (nombre.equals("") || paterno.equals("") || ci < 1000
                        || correo.equals("") || user.equals("") || pass.equals("") || idUsuario < 1 || vista.cbCargo.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Hay campos vacios, debe llenar todos los datos");
                } else {
                    if (!modelo.existeUsuario(user)) {
                        if (modelo.esEmail(correo)) {
                            int cargo = modeloCargo.getKey((String) vista.cbCargo.getSelectedItem());
                            System.err.println("privilego =" + privilegio);
                            System.err.println("cargo seleccionado= " + cargo);
                            if (privilegio == 1) {
                                int recinto = modeloRecinto.getKey((String) vista.cbRecinto.getSelectedItem());
                                String nuevoPass = modelo.sha1(pass);
                                System.out.println(idUsuario + " " + nombre + " " + paterno + " " + materno + " " + correo + " " + user + " " + telefono + " " + ci + " " + cargo + " " + recinto);
                                try {

                                    modelo.modificar(idUsuario, nombre, paterno, materno, correo, user, nuevoPass, telefono, ci, cargo, recinto);
                                    vista.actualizar();
                                    JOptionPane.showMessageDialog(null, "Registro Actualizado");
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Hay un problema al actualizar");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "No tienes privilegio para modificar");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "El email es incorrecto");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "El usuario ya existe");
                    }
                }
                break;
            case "btnEliminar":
                try {
                    if (idUsuario > 0) {
                        modelo.eliminar(idUsuario);
                        JOptionPane.showMessageDialog(null, "Registro Eliminado");
                        vista.actualizar();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hay un problema al eliminar");
                }
                break;
        }
    }

//    public static void main(String[] args) {
//        VUsuario vista = new VUsuario();
//        NUsuario modelo = new NUsuario();
//        NRecinto recinto = new NRecinto();
//        NCargo cargo = new NCargo();
//        CUsuario contro = new CUsuario(modelo, vista, cargo, recinto);
//        vista.setVisible(true);
//    }
}
