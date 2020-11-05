/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.NLugar;
import Modelo.NRecinto;
import Vista.VRecinto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author German
 */
public class CRecinto implements ActionListener {

    private NRecinto modelo;
    private VRecinto vista;
    private NLugar modelolugar;

    public CRecinto() {
        this.modelo = new NRecinto();
        this.vista = new VRecinto();
        this.modelolugar = new NLugar();
        initComponente();
    }

    public void initComponente() {
        vista.setVisible(true);
        vista.cargarCombo(modelolugar.getCombo());
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
        int idLugar;
        switch (e.getActionCommand()) {
            case "btnGuardar":
                idLugar = modelolugar.getKey((String) vista.cbLugar.getSelectedItem());
                modelo.guardar(vista.txtNombre.getText(), idLugar);
                vista.actualizar();
                break;
            case "btnModificar":
                if (!"".equals(vista.lbIdRecinto.getText())) {
                    idLugar = modelolugar.getKey((String) vista.cbLugar.getSelectedItem());
                    modelo.modificar(Integer.valueOf(vista.lbIdRecinto.getText()), vista.txtNombre.getText(), idLugar);
                    this.vista.actualizar();
                }
                break;
            case "btnEliminar":
                if (!"".equals(vista.lbIdRecinto.getText())) {
                    modelo.eliminar(Integer.valueOf(vista.lbIdRecinto.getText()));
                    this.vista.actualizar();
                }
                break;
        }
    }
//    public static void main(String[] args) {
//     VRecinto vista = new VRecinto();
//     NRecinto modelo=new NRecinto();
//     NLugar modelolugar = new NLugar();
//     CRecinto control = new CRecinto(modelo,vista,modelolugar);
//     vista.setVisible(true);
//    }  
}
