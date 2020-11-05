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
public class NCandidatura {

    private DCandidatura modelo;

    public NCandidatura() {
        modelo = new DCandidatura();
    }

    public void guardar(String nombre) {

        modelo.setNombre(nombre);
        try {
            if (!"".equals(modelo.getNombre())) {
                modelo.guardar();
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            } else {
                JOptionPane.showMessageDialog(null, "Revise que esten llenos los campos necesarios");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hay un problema al registrar");
        }
    }

    public void modificar(int id, String nombre) {
        modelo.setIdCandidatura(id);
        modelo.setNombre(nombre);
        try {
            if (!"".equals(modelo.getNombre())) {
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
        modelo.setIdCandidatura(id);
        try {
            modelo.eliminar();
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Hay un problema al eliminar");
        }
    }

    public DefaultComboBoxModel getCombo() {
        return modelo.getSelect();
    }

    public int getKey(String nombre) {
        modelo.setNombre(nombre);
        return modelo.getKey();
    }

    public DefaultTableModel listar() {
        return modelo.getTabla();
    }
}
