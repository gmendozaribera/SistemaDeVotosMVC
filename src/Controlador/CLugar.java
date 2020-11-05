/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NLugar;
import Vista.VLugar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CLugar implements ActionListener {

    private NLugar modelo;
    private VLugar vista;

    public CLugar() {
        this.modelo = new NLugar();
        this.vista = new VLugar();
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
                modelo.guardar(vista.txtNombre.getText(), (String) vista.cbTipo.getSelectedItem());
                vista.actualizar();
                break;
            case "btnModificar":
                if (!"".equals(vista.lbId.getText())) {
                    modelo.modificar(
                            Integer.valueOf(vista.lbId.getText()),
                            vista.txtNombre.getText(),
                            (String) vista.cbTipo.getSelectedItem());
                    vista.actualizar();
                }
                break;
            case "btnEliminar":
                if (!"".equals(vista.lbId.getText())) {
                    modelo.eliminar(Integer.valueOf(vista.lbId.getText()));
                    vista.actualizar();
                }
                break;
        }
    }
       public static void main(String[] args) {
        new CLugar();  
    }

}
