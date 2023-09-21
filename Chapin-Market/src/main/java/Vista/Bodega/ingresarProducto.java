/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Bodega;

import com.coderhouse.chapin.market.Conexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emili_zxg0ruq
 */
public class ingresarProducto extends javax.swing.JPanel {

    /**
     * Creates new form ingresarProducto
     */
    public ingresarProducto() {
        initComponents();
    }
     Connection conexion = new Conexion().establecerConexion();
     public int id;

    public ingresarProducto(int id) {
        initComponents();
        this.id = id;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFabricante = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jCantidad = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(710, 510));
        setMinimumSize(new java.awt.Dimension(710, 510));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombre.setToolTipText("");
        txtNombre.setBorder(null);
        txtNombre.setOpaque(true);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 400, 33));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Nombre ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, -1, -1));

        jLabel2.setText("_____________________________________________________________");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 437, -1));

        txtFabricante.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtFabricante.setBorder(null);
        txtFabricante.setOpaque(true);
        add(txtFabricante, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 400, 33));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Fabricante");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, -1, -1));

        jLabel7.setText("_____________________________________________________________");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 437, -1));

        txtPrecio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtPrecio.setBorder(null);
        txtPrecio.setOpaque(true);
        add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 400, 33));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Cantidad");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, -1, -1));

        jLabel9.setText("_____________________________________________________________");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 437, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Precio");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, -1, -1));

        jCantidad.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jCantidad.setBorder(null);
        add(jCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 80, -1));

        jLabel3.setBackground(new java.awt.Color(22, 199, 154));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   NUEVO PRODUCTO");
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 440, 50));

        btnIngresar.setBackground(new java.awt.Color(25, 69, 107));
        btnIngresar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIngresar.setText("CONFIRMAR");
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setOpaque(true);
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresarMouseExited(evt);
            }
        });
        add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 410, 150, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseClicked
        // TODO add your handling code here:
       // INSERT INTO ControlEmpresas.Producto(nombreProducto, fabricante, precio, cantidad, estado, pasillo, idTienda)
    //VALUES (p_nombreProducto, p_fabricante, p_precio, p_cantidad, p_estado, p_pasillo, p_idTienda);
         // TODO add your handling code here:
            
        try {
            String sql = "INSERT INTO ControlEmpresas.Producto(nombreProducto, fabricante, precio, cantidad, estado, pasillo, idTienda) VALUES (?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, txtNombre.getText());
            pst.setString(2, txtFabricante.getText());
            pst.setDouble(3, Double.parseDouble(txtPrecio.getText())); // Se corrige aquí
            int cantidadSeleccionada = (int) jCantidad.getValue();
            pst.setInt(4, cantidadSeleccionada); // Se corrige aquí
            pst.setBoolean(5, false);
            pst.setInt(6, 0);
            pst.setInt(7, id);
            
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("La inserción se realizó correctamente.");
            }
           
            //JOptionPane.showMessageDialog(rootPane, "Ingresado");
           // jLabel5.setText("Producto ingresada");
            //txtname.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(ingresarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }//GEN-LAST:event_btnIngresarMouseClicked

    private void btnIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseEntered
        // TODO add your handling code here:
         btnIngresar.setBackground(new Color(22, 199, 154));
    }//GEN-LAST:event_btnIngresarMouseEntered

    private void btnIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseExited
        // TODO add your handling code here:
       btnIngresar.setBackground(new Color(25, 69, 107));
    }//GEN-LAST:event_btnIngresarMouseExited

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnIngresar;
    private javax.swing.JSpinner jCantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtFabricante;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
