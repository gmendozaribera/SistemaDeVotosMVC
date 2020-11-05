/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NCandidatura;
import Vista.VCandidatura;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CCandidatura implements ActionListener {

    private NCandidatura modelo;
    private VCandidatura vista;

    public CCandidatura() {
        this.modelo = new NCandidatura();
        this.vista = new VCandidatura();
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
        switch (e.getActionCommand()) {
            case "btnGuardar":
                if (!"".equals(vista.txtNombre.getText())) {
                    modelo.guardar(vista.txtNombre.getText());
                    vista.actualizar();
                }
                break;
            case "btnModificar":
                if (!"".equals(vista.lbIdCandidatura.getText())) {
                    modelo.modificar(Integer.valueOf(vista.lbIdCandidatura.getText()), vista.txtNombre.getText());
                    this.vista.actualizar();
                }
                break;
            case "btnEliminar":
                if (!"".equals(vista.lbIdCandidatura.getText())) {
                    modelo.eliminar(Integer.valueOf(vista.lbIdCandidatura.getText()));
                    this.vista.actualizar();
                }
                break;
        }
    }
    public static void main(String[] args) {
     new CCandidatura();
    }  
}
