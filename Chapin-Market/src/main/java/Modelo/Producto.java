/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author emili_zxg0ruq
 */
public class Producto {

    private int idProducto;
    private String nombreProducto;
    private String fabricante;
    private double precio;
    private int cantidad;
    private boolean estado;
    private int pasillo;
    private int idTienda;

    // Constructor
    public Producto(int idProducto, String nombreProducto, String fabricante, double precio, int cantidad, boolean estado, int pasillo, int idTienda) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.fabricante = fabricante;
        this.precio = precio;
        this.cantidad = cantidad;
        this.estado = estado;
        this.pasillo = pasillo;
        this.idTienda = idTienda;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getPasillo() {
        return pasillo;
    }

    public void setPasillo(int pasillo) {
        this.pasillo = pasillo;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

}
