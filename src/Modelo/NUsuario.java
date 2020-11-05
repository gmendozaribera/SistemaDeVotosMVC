/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author German
 */
public class NUsuario {

    private DUsuario modelo;

    public NUsuario() {
        modelo = new DUsuario();
    }

    public DUsuario datosUsuario(){
        return modelo;
    }
    
    public boolean login(String usuario, String password) {
        modelo.setUsuario(usuario);
        modelo.setPassword(password);
        return modelo.login();
    }
    
    public boolean existeUsuario(String usuario) {
        modelo.setUsuario(usuario);
        if (modelo.existeUsuario() == 0) {
            return false;
        }
        return true;
    }

    public void guardar(String nombre, String apellidoPat, String apellidoMat, String correo, String user, String password, int telefono, int ci, int idCargo, int idRecinto) throws SQLException {
        modelo.setNombre(nombre);
        modelo.setApellidoMat(apellidoMat);
        modelo.setApellidoPat(apellidoPat);
        modelo.setCi(ci);
        modelo.setCorreo(correo);
        modelo.setIdCargo(idCargo);
        modelo.setIdRecinto(idRecinto);
        modelo.setTelefono(telefono);
        modelo.setPassword(password);
        modelo.setUsuario(user);
        modelo.guardar();
    }

    public void modificar(int id, String nombre, String apellidoPat, String apellidoMat, String correo, String user, String password, int telefono, int ci, int idCargo, int idRecinto) throws SQLException {
        modelo.setIdUsuario(id);
        modelo.setNombre(nombre);
        modelo.setApellidoMat(apellidoMat);
        modelo.setApellidoPat(apellidoPat);
        modelo.setCi(ci);
        modelo.setCorreo(correo);
        modelo.setIdCargo(idCargo);
        modelo.setIdRecinto(idRecinto);
        modelo.setTelefono(telefono);
        modelo.setPassword(password);
        modelo.setUsuario(user);
        modelo.modificar();
    }

    public void eliminar(int id) throws SQLException {
        modelo.setIdUsuario(id);
        modelo.eliminar();
    }

    public DefaultTableModel listar() {
        return modelo.getTabla();
    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        return mather.find();

    }

    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /* Retorna un hash SHA1 a partir de un texto */
    public static String sha1(String txt) {
        return getHash(txt, "SHA1");
    }

    //convierte el valor de un string a entero, si no existe te devuelve -1
    public int getNumero(String valor) {
        if (valor.equals("")) {
            return -1;
        }
        int numero = Integer.valueOf(valor);
        return numero;
    }

}
