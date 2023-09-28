/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Admin;

import com.coderhouse.chapin.market.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emili_zxg0ruq
 */
public class Historial extends javax.swing.JPanel {

    public int idTienda;
    public int mes;
    Connection conexion = new Conexion().establecerConexion();

    public Historial(int idTienda) {
        this.idTienda = idTienda;
        initComponents();
        txtMes.addItem("Enero");
        txtMes.addItem("Febrero");
        txtMes.addItem("Marzo");
        txtMes.addItem("Abril");
        txtMes.addItem("Mayo");
        txtMes.addItem("Junio");
        txtMes.addItem("Julio");
        txtMes.addItem("Agosto");
        txtMes.addItem("Septiembre");
        txtMes.addItem("Noviembre");
        txtMes.addItem("Diciembre");
    }
    /**
     * Creates new form Historial
     */
    public Historial() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btbuscar = new javax.swing.JLabel();
        txtMes = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(910, 680));
        setMinimumSize(new java.awt.Dimension(910, 680));
        setName(""); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("SELECIONAR MES");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        btbuscar.setBackground(new java.awt.Color(17, 105, 142));
        btbuscar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btbuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btbuscar.setMaximumSize(new java.awt.Dimension(50, 50));
        btbuscar.setMinimumSize(new java.awt.Dimension(50, 50));
        btbuscar.setOpaque(true);
        btbuscar.setPreferredSize(new java.awt.Dimension(50, 50));
        btbuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btbuscarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btbuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btbuscarMouseExited(evt);
            }
        });
        add(btbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 40, 30));

        txtMes.setEditable(true);
        txtMes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtMes.setToolTipText("");
        txtMes.setBorder(null);
        txtMes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtMes.setDoubleBuffered(true);
        txtMes.setFocusable(false);
        txtMes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtMesMouseEntered(evt);
            }
        });
        txtMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMesActionPerformed(evt);
            }
        });
        txtMes.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtMesPropertyChange(evt);
            }
        });
        add(txtMes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btbuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbuscarMouseClicked
        // TODO add your handling code here:
        // Obtén el NIT ingresado por el usuario desde el campo de texto txtbuscador
        mes = txtMes.getSelectedIndex()+1;
        String sql = "SELECT * FROM ControlVentas.Venta WHERE EXTRACT(MONTH FROM fecha) = ?";
            try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
                preparedStatement.setInt(1, mes);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int idVenta = resultSet.getInt("idVenta");
                        String fechaVenta = resultSet.getString("fecha");
                        float totalDescuento = resultSet.getFloat("totalDescuento");
                        // Otros campos de la tabla ventas

                        System.out.println("ID Venta: " + idVenta);
                        System.out.println("Fecha de Venta: " + fechaVenta);
                        System.out.println("desc: " + totalDescuento);
                        // Imprimir otros datos de ventas
                    }
                }
            } catch (SQLException ex) {
            Logger.getLogger(Historial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btbuscarMouseClicked

    private void btbuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbuscarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btbuscarMouseEntered

    private void btbuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbuscarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btbuscarMouseExited

    private void txtMesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMesMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesMouseClicked

    private void txtMesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMesMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesMouseEntered

    private void txtMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesActionPerformed

    private void txtMesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtMesPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMesPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btbuscar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> txtMes;
    // End of variables declaration//GEN-END:variables
}