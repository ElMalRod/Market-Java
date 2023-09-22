/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Bodega;

import com.coderhouse.chapin.market.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emili_zxg0ruq
 */
public class VerProducto extends javax.swing.JPanel {

    public int id;

    /**
     * Creates new form VerProducto
     */
    public VerProducto(int id) {
        initComponents();
        this.id = id;
        cargarProductosTienda();
    }

private void cargarProductosTienda() {
    // Realiza una consulta SQL para obtener los productos de la tienda
    String consulta = "SELECT nombreProducto, precio, cantidad FROM ControlEmpresas.Producto WHERE idTienda = ? AND estado = ?"; // Estado 1 indica que está en la estantería
    Connection conexion = new Conexion().establecerConexion();

    DefaultTableModel modelo = new DefaultTableModel();
    modelo.addColumn("Nombre");
    modelo.addColumn("Precio");
    modelo.addColumn("Cantidad");

    try {
        PreparedStatement pstmt = conexion.prepareStatement(consulta);
        pstmt.setInt(1, id); // Asigna el valor correcto del ID de la tienda
        pstmt.setBoolean(2, true); // Asigna el valor booleano true para estado
        ResultSet resultado = pstmt.executeQuery();

        while (resultado.next()) {
            String nombre = resultado.getString("nombreProducto");
            double precio = resultado.getDouble("precio");
            int cantidad = resultado.getInt("cantidad");

            // Agrega una fila al jTable con los datos del producto
            modelo.addRow(new Object[]{nombre, precio, cantidad});
        }

        resultado.close();
        pstmt.close();
        conexion.close();

        jTableProductos.setModel(modelo); // Asigna el modelo al jTable
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los productos: " + e.getMessage());
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(22, 199, 154));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("PRODUCTO EN TIENDA");
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 440, 50));

        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 670, 300));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProductos;
    // End of variables declaration//GEN-END:variables
}
