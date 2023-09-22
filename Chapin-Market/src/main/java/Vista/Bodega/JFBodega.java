/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista.Bodega;

import com.coderhouse.chapin.market.Conexion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author emili_zxg0ruq
 */
public class JFBodega extends javax.swing.JFrame {

    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JFBodega(int id) {
        initComponents();
        // MostrarLogo();
        this.id = id;
    }

    private void MostrarLogo() {
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/Images/4.png"));
        Image image = iconLogo.getImage();
        Image scaledImage = image.getScaledInstance(lbLogo.getWidth(), lbLogo.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lbLogo.setIcon(scaledIcon);

    }

    public void setUsuarioYRol(String nombreUsuario, String rol) {
        btUser.setText(rol + " - " + nombreUsuario);
        String nombreTienda = obtenerNombreTienda();
        lblNombreTienda.setText("Tienda: " + nombreTienda); // Muestra el nombre de la tienda en un JLabel
    }

    private String obtenerNombreTienda() {
        Connection conexion = new Conexion().establecerConexion();
        String nombreTienda = "";

        try {
            String consultaTienda = "SELECT nombreTienda FROM ControlEmpresas.Tienda WHERE idTienda = ?";
            PreparedStatement pstmtTienda = conexion.prepareStatement(consultaTienda);
            pstmtTienda.setInt(1, id);
            ResultSet resultadoTienda = pstmtTienda.executeQuery();

            if (resultadoTienda.next()) {
                nombreTienda = resultadoTienda.getString("nombreTienda");
            }

            resultadoTienda.close();
            pstmtTienda.close();
            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar la tienda: " + e.getMessage());
        }

        return nombreTienda;
    }

    /**
     * Creates new form JFBodega
     */
    public JFBodega() {
        initComponents();
        System.out.println("KDFJHKDJA " + id);
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
        btnVer = new javax.swing.JLabel();
        btIngresar = new javax.swing.JLabel();
        btUser = new javax.swing.JLabel();
        lblNombreTienda = new javax.swing.JLabel();
        lbLogo = new javax.swing.JLabel();
        btnVerInv = new javax.swing.JLabel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(900, 550));
        setMinimumSize(new java.awt.Dimension(900, 550));
        setSize(new java.awt.Dimension(900, 550));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(25, 69, 107));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnVer.setBackground(new java.awt.Color(25, 69, 107));
        btnVer.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVer.setText("INVENTARIO");
        btnVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer.setOpaque(true);
        btnVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerMouseExited(evt);
            }
        });
        jPanel1.add(btnVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 192, 50));

        btIngresar.setBackground(new java.awt.Color(25, 69, 107));
        btIngresar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btIngresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btIngresar.setText("INGRESAR");
        btIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btIngresar.setOpaque(true);
        btIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btIngresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btIngresarMouseExited(evt);
            }
        });
        jPanel1.add(btIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 190, 50));

        btUser.setBackground(new java.awt.Color(25, 69, 107));
        btUser.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btUser.setForeground(new java.awt.Color(255, 255, 255));
        btUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btUser.setOpaque(true);
        btUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btUserMouseExited(evt);
            }
        });
        jPanel1.add(btUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 190, 50));

        lblNombreTienda.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblNombreTienda.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreTienda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(lblNombreTienda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 40));
        jPanel1.add(lbLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, 130));

        btnVerInv.setBackground(new java.awt.Color(25, 69, 107));
        btnVerInv.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnVerInv.setForeground(new java.awt.Color(255, 255, 255));
        btnVerInv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVerInv.setText("VER INVENTARIO");
        btnVerInv.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerInv.setOpaque(true);
        btnVerInv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVerInvMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerInvMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerInvMouseExited(evt);
            }
        });
        jPanel1.add(btnVerInv, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 192, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 560));

        content.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        getContentPane().add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 710, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerMouseEntered
        // TODO add your handling code here:
        btnVer.setBackground(new Color(22, 199, 154));
    }//GEN-LAST:event_btnVerMouseEntered

    private void btnVerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerMouseExited
        // TODO add your handling code here:
        btnVer.setBackground(new Color(25, 69, 107));
    }//GEN-LAST:event_btnVerMouseExited

    private void btIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btIngresarMouseEntered
        // TODO add your handling code here:
        btIngresar.setBackground(new Color(22, 199, 154));
    }//GEN-LAST:event_btIngresarMouseEntered

    private void btIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btIngresarMouseExited
        // TODO add your handling code here:
        btIngresar.setBackground(new Color(25, 69, 107));
    }//GEN-LAST:event_btIngresarMouseExited

    private void btnVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerMouseClicked
        // TODO add your handling code here:
        ingresarProducto p = new ingresarProducto();
        p.setSize(710, 510);
        p.setLocation(0, 0);

        content.removeAll();
        content.repaint();
    }//GEN-LAST:event_btnVerMouseClicked

    private void btIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btIngresarMouseClicked
        // TODO add your handling code here:
        ingresarProducto p = new ingresarProducto(id);
        p.setSize(710, 510);
        p.setLocation(0, 0);
        content.removeAll();
        content.add(p, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_btIngresarMouseClicked

    private void btUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btUserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btUserMouseClicked

    private void btUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btUserMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btUserMouseEntered

    private void btUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btUserMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btUserMouseExited

    private void btnVerInvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerInvMouseClicked
        // TODO add your handling code here:
        VerProducto verProductoPanel = new VerProducto(id);
        verProductoPanel.setSize(710, 510);
        verProductoPanel.setLocation(0, 0);
        content.removeAll();
        content.add(verProductoPanel, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();

    }//GEN-LAST:event_btnVerInvMouseClicked

    private void btnVerInvMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerInvMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerInvMouseEntered

    private void btnVerInvMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerInvMouseExited
        // TODO add your handling code here:
         btnVerInv.setBackground(new Color(22, 199, 154));
    }//GEN-LAST:event_btnVerInvMouseExited

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
            java.util.logging.Logger.getLogger(JFBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFBodega.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFBodega().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btIngresar;
    private javax.swing.JLabel btUser;
    private javax.swing.JLabel btnVer;
    private javax.swing.JLabel btnVerInv;
    private javax.swing.JPanel content;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbLogo;
    private javax.swing.JLabel lblNombreTienda;
    // End of variables declaration//GEN-END:variables
}
