/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author emili_zxg0ruq
 */
public class Venta {
    private int idVenta;
    private int idEmpleado;
    private String nitCliente;
    private Date fecha;
    private double total;
    private double totalDescuento;
    private int idTienda;

    public Venta(int idVenta, int idEmpleado, String nitCliente, Date fecha, double total, double totalDescuento, int idTienda) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.nitCliente = nitCliente;
        this.fecha = fecha;
        this.total = total;
        this.totalDescuento = totalDescuento;
        this.idTienda = idTienda;
    }

    public double getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(double totalDescuento) {
        this.totalDescuento = totalDescuento;
    }



    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
}
