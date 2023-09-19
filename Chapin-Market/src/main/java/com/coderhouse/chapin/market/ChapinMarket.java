/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.coderhouse.chapin.market;
import Vista.Login;

/**
 *
 * @author emili_zxg0ruq
 */
public class ChapinMarket {

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        conexion.establecerConexion();
       Login mostrar = new Login();
        mostrar.setVisible(true);
    }
}
