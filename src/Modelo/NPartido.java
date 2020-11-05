/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author German
 */
public class NPartido {

    DPartido modelo;

    public NPartido() {
        modelo = new DPartido();
    }

    public boolean existePartido(String partido) {
        modelo.setNombre(partido);
        if (modelo.existePartido() == 0) {
            return false;
        }
        return true;
    }

    public void guardar(String nombre, String sigla) throws SQLException {
        modelo.setNombre(nombre);
        modelo.setSigla(sigla);

        modelo.guardar();
    }

    public void modificar(int id, String nombre, String sigla) {
        modelo.setIdPartido(id);
        modelo.setNombre(nombre);
        modelo.setSigla(sigla);
        try {
            modelo.modificar();
        } catch (SQLException ex) {
            Logger.getLogger(NPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(int id) {
        modelo.setIdPartido(id);
        try {
            modelo.eliminar();
        } catch (SQLException ex) {
            Logger.getLogger(NPartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DefaultTableModel listar() {
        return modelo.getTabla();
    }

    public DefaultComboBoxModel getCombo() {
        return modelo.getSelect();
    }

    public int getKey(String nombre){
        modelo.setNombre(nombre);
        return modelo.getKey();
    }
}
