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
public class NRecinto {

    private DRecinto modelo;

    public NRecinto() {
        modelo = new DRecinto();
    }

    public void guardar(String nombre, int idLugar) {

        modelo.setNombre(nombre);
        modelo.setIdLugar(idLugar);
        try {
            if (!"".equals(modelo.getNombre()) && modelo.getIdLugar() != -1) {
                modelo.guardar();
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            } else {
                JOptionPane.showMessageDialog(null, "Revise que esten llenos los campos necesarios");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hay un problema al registrar");
        }
    }

    public void modificar(int id, String nombre, int idLugar) {
        modelo.setIdRecinto(id);
        modelo.setNombre(nombre);
        modelo.setIdLugar(idLugar);
        try {
            if (!"".equals(modelo.getNombre()) && modelo.getIdLugar() != -1) {
                modelo.modificar();
                JOptionPane.showMessageDialog(null, "Registro Actualizado");
            } else {
                JOptionPane.showMessageDialog(null, "Revise que esten llenos los campos necesarios");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hay un problema al actualizar");
        }
    }

    public void eliminar(int id) {
        modelo.setIdRecinto(id);
        try {
            modelo.eliminar();
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hay un problema al eliminar");
        }
    }

    public DefaultTableModel listar() {
        return modelo.getTabla();
    }

    public DefaultComboBoxModel getCombo() {
        return modelo.getSelect();
    }

    public int getKey(String nombre) {
        modelo.setNombre(nombre);
        return modelo.getKey();
    }
}
