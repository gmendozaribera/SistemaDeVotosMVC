/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VotosMVC;

import Controlador.CLogin;
import Modelo.NUsuario;
import Vista.VLogin;

/**
 *
 * @author German
 */
public class Votos {
        public static void main(String[] args) {
        NUsuario modelo = new NUsuario();
        VLogin vista = new VLogin();
        CLogin control = new CLogin(modelo, vista);
        vista.setVisible(true);

    }
}
