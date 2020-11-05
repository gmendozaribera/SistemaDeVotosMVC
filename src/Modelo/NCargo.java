/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.*;

/**
 *
 * @author German
 */
public class NCargo {

    private DCargo modelo;

    public NCargo() {
        modelo = new DCargo();
    }
    
    public DefaultComboBoxModel getCombo() {
        return modelo.getSelect();
    }

    public int getKey(String nombre){
        modelo.setNombre(nombre);
        return modelo.getKey();
    }
}
