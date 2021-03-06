/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author German
 */
public class DCandidatura {
    private int idCandidatura;
    private String nombre;
    private Conexion conexion;

    public DCandidatura() {
        conexion = new Conexion();
    }

    public int getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(int idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Conexion getConexion() {
        return conexion;
    }
    
    public void guardar() throws SQLException{
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("INSERT INTO candidatura (nombre) VALUES (?)");
        ps.setString(1, getNombre());
        ps.execute();
    }
    
    public void modificar() throws SQLException{
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("UPDATE candidatura SET nombre=? WHERE idCandidatura=?");

        ps.setString(1, getNombre());
        ps.setInt(2, getIdCandidatura());

        ps.execute();
    }
    
    public void eliminar() throws SQLException{
        PreparedStatement ps = null;
        java.sql.Connection conn = conexion.getConexion();
        ps = conn.prepareStatement("DELETE FROM candidatura WHERE idCandidatura=?");
        ps.setInt(1, getIdCandidatura());
        ps.execute();
    }
    
    public DefaultTableModel getTabla() {
            DefaultTableModel listar = new DefaultTableModel();
        
            listar.addColumn("Codigo");
            listar.addColumn("Nombre");
        try {
            PreparedStatement ps = null;
            ResultSet rs = null; 
            java.sql.Connection con = conexion.getConexion();
            String sql = "SELECT idCandidatura, nombre FROM candidatura";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = (ResultSetMetaData) rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                listar.addRow(filas);
            }
            ps.close();
//            conexion.closeConexion();
    } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    return listar;
    }
    
    DefaultComboBoxModel getSelect() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try{
            PreparedStatement ps;
            ResultSet rs = null;
            String sql = "select nombre from candidatura;";
            String encabezado = "Candidatura";
            java.sql.Connection con = conexion.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            combo.addElement(encabezado);
            while (rs.next()) {
                combo.addElement(rs.getString(1));
            }
            ps.close();
//            conexion.closeConexion();
        }catch (SQLException ex){
            System.out.println("no se pudo listar los datos");
        }
        return combo;
    }

    int getKey() {
        int key=-1;       
        try {
            PreparedStatement ps;
            String sql = "select idCandidatura from candidatura where nombre=?";
            ResultSet rs = null;
            java.sql.Connection con = conexion.getConexion();
            ps = con.prepareStatement(sql);
            //nuevo
            ps.setString(1, getNombre());
//            ps.execute();
            //final
            rs = ps.executeQuery();
            while (rs.next()) {
                key = Integer.valueOf(rs.getObject(1).toString());
            }
            ps.close();
//            conexion.closeConexion();
            
        } catch (Exception e) {
            System.out.println("no encontro la llave");
        }
        return key;
    }
}
