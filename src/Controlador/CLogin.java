/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NUsuario;
import Vista.VIndex;
import Vista.VLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CLogin implements ActionListener {

    NUsuario modelo;
    VLogin vista;
    VIndex VMenu;

    public CLogin(NUsuario modelo, VLogin vista) {
        this.modelo = modelo;
        this.vista = vista;
        initComponente();
    }

    public void initComponente() {
        vista.btnIngresar.setActionCommand("btnIngresar");
        vista.btnIngresar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnIngresar":
                String pass = new String(vista.txtPassword.getPassword());

                if (!vista.txtUsuario.getText().equals("") && !pass.equals("")) {

                    String nuevoPass = modelo.sha1(pass);
                    System.err.println("datos del controlador = " + nuevoPass);
                    if (modelo.login(vista.txtUsuario.getText(), nuevoPass)) {
                        System.out.println("Entro al login del controlador");
                        vista.dispose();
                        VMenu = new VIndex(modelo);
                        VMenu.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Datos incorrectos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
                }
                break;
        }
    }
}
