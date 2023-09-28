/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Admin;

import com.coderhouse.chapin.market.Conexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author emili_zxg0ruq
 */
public class IngresarEmpleado extends javax.swing.JPanel {

    public int idTienda;
    public int caja;
    Connection conexion = new Conexion().establecerConexion();

    public IngresarEmpleado(int idTienda) {
        this.idTienda = idTienda;
        initComponents();
        txtRol.addItem("Cajero");
        txtRol.addItem("Administrador");
        txtRol.addItem("Bodega");
        txtRol.addItem("Inventario");
    }

    /**
     * Creates new form IngresarEmpleado
     */
    public IngresarEmpleado() {
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

        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JLabel();
        txtDPI = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRol = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel11 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(910, 680));
        setMinimumSize(new java.awt.Dimension(910, 680));
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
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 330, 33));

        jLabel2.setText("_____________________________________________________________");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 330, -1));

        txtTel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTel.setBorder(null);
        txtTel.setOpaque(true);
        add(txtTel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 320, 33));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Telefóno");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jLabel7.setText("_____________________________________________________________");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 310, -1));

        jLabel3.setBackground(new java.awt.Color(22, 199, 154));
        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("   NUEVO EMPLEADO");
        jLabel3.setOpaque(true);
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 440, 50));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Nombre ");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

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
        add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 490, 150, 40));

        txtDPI.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDPI.setBorder(null);
        txtDPI.setOpaque(true);
        add(txtDPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 320, 33));

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("DPI");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        jLabel9.setText("_____________________________________________________________");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 310, -1));

        txtRol.setEditable(true);
        txtRol.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtRol.setToolTipText("");
        txtRol.setBorder(null);
        txtRol.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtRol.setDoubleBuffered(true);
        txtRol.setFocusable(false);
        txtRol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRolMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtRolMouseEntered(evt);
            }
        });
        txtRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRolActionPerformed(evt);
            }
        });
        txtRol.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtRolPropertyChange(evt);
            }
        });
        add(txtRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 230, 30));

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("ROL");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, -1, -1));

        txtUsername.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtUsername.setToolTipText("");
        txtUsername.setBorder(null);
        txtUsername.setOpaque(true);
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 330, 33));

        jLabel5.setText("_____________________________________________________________");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 330, -1));

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Username");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, -1, -1));

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Contraseña");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, -1, -1));

        txtPass.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtPass.setBorder(null);
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 310, 30));

        jLabel11.setText("______________________________________________________________");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 310, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseClicked
        try {
            // TODO add your handling code here:
            // INSERT INTO ControlEmpresas.Producto(nombreProducto, fabricante, precio, cantidad, estado, pasillo, idTienda)
            //VALUES (p_nombreProducto, p_fabricante, p_precio, p_cantidad, p_estado, p_pasillo, p_idTienda);
            // TODO add your handling code here:
            String roll = txtRol.getSelectedItem().toString();
            if (roll.equals("Cajero")) {
                String pas = JOptionPane.showInputDialog(this, "Ingrese numero de caja del cajera:");
                int aux = Integer.parseInt(pas);
                if (aux > 5) {
                    JOptionPane.showInputDialog(this, "Numero de caja inexistente, intentelo de nuevo.");
                } else {
                    caja = aux;
                }
            } else {
                caja = 0;
            }

            String sql = "INSERT INTO ControlPersonal.Empleado(nombreEmpleado, telefono, rol, dpi,cajas, username, password, idTienda) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, txtNombre.getText());
            pst.setString(2, txtTel.getText());
            pst.setString(3, txtRol.getSelectedItem().toString());
            pst.setString(4, txtDPI.getText());
            pst.setInt(5, caja);
            pst.setString(6, txtUsername.getText());
            pst.setString(7, txtPass.getText());
            pst.setInt(8, idTienda);

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showInputDialog(this, "Empleado ingresado al sistema");
                System.out.println("La inserción se realizó correctamente.");
            }

            //JOptionPane.showMessageDialog(rootPane, "Ingresado");
            // jLabel5.setText("Producto ingresada");
            //txtname.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(IngresarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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

    private void txtRolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRolMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRolMouseClicked

    private void txtRolMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRolMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRolMouseEntered

    private void txtRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRolActionPerformed

    private void txtRolPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtRolPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRolPropertyChange

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtDPI;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JComboBox<String> txtRol;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
