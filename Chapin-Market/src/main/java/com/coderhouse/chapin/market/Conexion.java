/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coderhouse.chapin.market;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author emili_zxg0ruq
 */
public class Conexion {

    Connection conectar = null;

    String user = "emilio-market";
    String password = "123";
    String db = "chapin-db";
    String ip = "localhost";
    String port = "5432";

    String conect = "jdbc:postgresql://" +ip+":"+port+"/"+db;

    public Connection establecerConexion() {

        try {
            Class.forName("org.postgresql.Driver");
            conectar = DriverManager.getConnection(conect, user, password);
            //JOptionPane.showMessageDialog(null, "SE CONECTO CORRECTAMENTE A LA BASE DE DATOS");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " ERROR AL CONECTAR A LA BASE DE DATOS, ERRO:" + e.toString());
        }
        return conectar;
    }
}
