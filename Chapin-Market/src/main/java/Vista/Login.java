/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Vista.Admin.JFAdmin;
import Vista.Bodega.JFBodega;
import Vista.Cajero.JFCajero;
import Vista.Inventario.JFInventario;
import com.coderhouse.chapin.market.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author emili_zxg0ruq
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContra = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 470, -1, -1));

        jLabel1.setText("Usuario");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 270, -1, -1));

        jLabel2.setText("Contraseña");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 350, -1, -1));

        txtUsuario.setText("jTextField1");
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 310, 310, -1));

        txtContra.setText("jTextField2");
        jPanel1.add(txtContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, 310, -1));

        jPasswordField1.setText("jPasswordField1");
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Verifica si se ingresó un usuario y una contraseña
        if (txtUsuario.getText().isEmpty() || txtContra.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un usuario y una contraseña");
            return;
        }

        String username = txtUsuario.getText();
        String password = txtContra.getText();

        Connection conexion = new Conexion().establecerConexion();

        try {
            // Hacemos el query
            String consultaUsuario = "SELECT * FROM ControlPersonal.Empleado WHERE username = ?";
            PreparedStatement pstmtUsuario = conexion.prepareStatement(consultaUsuario);
            pstmtUsuario.setString(1, username);
            ResultSet resultadoUsuario = pstmtUsuario.executeQuery();

            if (resultadoUsuario.next()) {
                String contraseñaAlmacenada = resultadoUsuario.getString("password");
                if (password.equals(contraseñaAlmacenada)) {
                    String rol = resultadoUsuario.getString("rol");
                    int id = resultadoUsuario.getInt("idTienda");
                    System.out.println("id " + id);

                    String nombreUsuario = resultadoUsuario.getString("nombreEmpleado");

                    if ("Administrador".equals(rol)) {
                        JFAdmin adminFrame = new JFAdmin();
                        //adminFrame.setUsuarioYRol(nombreUsuario, rol); // Pasa el nombre de usuario y el rol
                        adminFrame.setVisible(true);
                    } else if ("Cajero".equals(rol)) {
                        JFCajero cajeroFrame = new JFCajero();
                        //cajeroFrame.setUsuarioYRol(nombreUsuario, rol); // Pasa el nombre de usuario y el rol
                        cajeroFrame.setVisible(true);
                    } else if ("Bodega".equals(rol)) {
                        JFBodega bodegaFrame = new JFBodega(id);
                        bodegaFrame.setUsuarioYRol(nombreUsuario, rol); // Pasa el nombre de usuario y el rol
                        bodegaFrame.setVisible(true);

                    } else if ("Inventario".equals(rol)) {
                        JFInventario inventarioFrame = new JFInventario();
                        //inventarioFrame.setUsuarioYRol(nombreUsuario, rol); // Pasa el nombre de usuario y el rol
                        inventarioFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Rol desconocido");
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Contraseña incorrecta");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nombre de usuario no encontrado");
            }

            resultadoUsuario.close();
            pstmtUsuario.close();
            conexion.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar la base de datos: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField txtContra;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
