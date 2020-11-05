/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NMesa {

    private DMesa modelo;

    public NMesa() {
        this.modelo = new DMesa();
    }

    public void guardar(int cantidad, int idRecinto) {
        modelo.setIdRecinto(idRecinto);
        for (int i = 0; i < cantidad; i++) {
            modelo.setNumero(i + 1);
            modelo.guardar();

        }
    }

    public void modificar(int cantidad, int idRecinto) {
        modelo.setNumero(cantidad);
        modelo.setIdRecinto(idRecinto);
        int cantidadActual = modelo.cantidadActual();
        if (cantidadActual >= cantidad) {
            modelo.eliminar();
        } else {
            for (int i = cantidadActual; i < cantidad; i++) {
                modelo.setNumero(i + 1);
                modelo.guardar();
            }
        }
    }

    public boolean existeRecinto(int idRecinto) {
        modelo.setIdRecinto(idRecinto);
        if (modelo.existeRecinto() == 0) {
            return false;
        }
        return true;
    }

    public int getNumero(String valor) {
        try {
            int numero = Integer.valueOf(valor);
            return numero;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public DefaultTableModel listar() {
        return modelo.getTabla();
    }

    public DefaultComboBoxModel getCombo(int idUsuario) {
        return modelo.getSelect(idUsuario);
    }

    public int getKey(String numero, int recinto){
        int num = Integer.valueOf(numero);
        modelo.setNumero(num);
        modelo.setIdRecinto(recinto);
        return modelo.getKey();
    }
}
