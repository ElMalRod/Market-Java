/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author emili_zxg0ruq
 */
import Modelo.DetalleVenta;
import Modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDAO {

    private Connection conexion; 

    public VentaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    private int obtenerIdProductoPorNombre(String nombreProducto) throws SQLException {
        int idProducto = -1;
        String sql = "SELECT idProducto FROM ControlEmpresas.Producto WHERE nombreProducto = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, nombreProducto);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                idProducto = rs.getInt("idProducto");
            }
        }

        return idProducto;
    }

    public void insertarVenta(Venta venta, List<DetalleVenta> detalles) throws SQLException {
        // Inserta la venta en la tabla ControlVentas.Venta
        String sqlVenta = "INSERT INTO ControlVentas.Venta (idEmpleado, nitCliente, fecha, total,idTienda) VALUES (?, ?, ?, ?,?)";
        PreparedStatement pstmtVenta = conexion.prepareStatement(sqlVenta);
        pstmtVenta.setInt(1, venta.getIdEmpleado());
        pstmtVenta.setString(2, venta.getNitCliente());
        pstmtVenta.setDate(3, new java.sql.Date(venta.getFecha().getTime()));
        pstmtVenta.setDouble(4, venta.getTotal());
        pstmtVenta.setInt(5, venta.getIdTienda());
        pstmtVenta.executeUpdate();

        // Obtiene el ID de la venta recién insertada
        int idVentaGenerado = obtenerUltimoIdGenerado();

        // Inserta los detalles de la venta en la tabla ControlVentas.DetalleVenta
        String sqlDetalleVenta = "INSERT INTO ControlVentas.DetalleVenta (idVenta, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement pstmtDetalleVenta = conexion.prepareStatement(sqlDetalleVenta);

        for (DetalleVenta detalle : detalles) {
            detalle.setIdVenta(idVentaGenerado); // Establece el ID de venta en el detalle
            int idProducto = obtenerIdProductoPorNombre(detalle.getNombreProducto()); // Obtén el ID del producto por su nombre
            detalle.setIdProducto(idProducto); // Establece el ID del producto en el detalle
            pstmtDetalleVenta.setInt(1, detalle.getIdVenta());
            pstmtDetalleVenta.setInt(2, detalle.getIdProducto());
            pstmtDetalleVenta.setInt(3, detalle.getCantidad());
            pstmtDetalleVenta.executeUpdate();
        }

        // Cierra los PreparedStatement y realiza cualquier otro manejo de excepciones necesario
        pstmtVenta.close();
        pstmtDetalleVenta.close();
    }

    private int obtenerUltimoIdGenerado() throws SQLException {
        int idGenerado = -1;
        String sql = "SELECT MAX(idVenta) as idVenta FROM ControlVentas.Venta";
        PreparedStatement pstmt = conexion.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            idGenerado = rs.getInt("idVenta");
        }

        rs.close();
        pstmt.close();

        return idGenerado;
    }

}
