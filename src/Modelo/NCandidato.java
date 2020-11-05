/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author German
 */
public class NCandidato {

    private DCandidato modelo;

    public NCandidato() {
        modelo = new DCandidato();
    }

    public void guardar(String nombre, String apellidoPat, String apellidoMat, int ci, int idCandidatura, int idPartido) {
        modelo.setNombre(nombre);
        modelo.setApellidoPat(apellidoPat);
        modelo.setApellidoMat(apellidoMat);
        modelo.setCi(ci);
        modelo.setIdCandidatura(idCandidatura);
        modelo.setIdPartido(idPartido);

        modelo.guardar();
    }

    public void modificar(int id, String nombre, String apellidoPat, String apellidoMat, int ci, int idCandidatura, int idPartido) {
        modelo.setIdCandidato(id);
        modelo.setNombre(nombre);
        modelo.setApellidoPat(apellidoPat);
        modelo.setApellidoMat(apellidoMat);
        modelo.setCi(ci);
        modelo.setIdCandidatura(idCandidatura);
        modelo.setIdPartido(idPartido);
        modelo.modificar();

    }

    public void eliminar(int id) {
        modelo.setIdCandidato(id);
        modelo.eliminar();

    }

    public DefaultTableModel listar() {
        return modelo.getTabla();
    }
}
