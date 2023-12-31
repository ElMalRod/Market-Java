/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Vista.Cajero;

import Controlador.VentaDAO;
import Modelo.CustomTableModel;
import Modelo.DetalleVenta;
import Modelo.Producto;
import Modelo.Venta;
import Vista.Bodega.ingresarProducto;
import Vista.Inventario.trasladarProducto;
import com.coderhouse.chapin.market.Conexion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emili_zxg0ruq
 */
public class PanelVenta extends javax.swing.JPanel {

    /**
     * Creates new form PanelVenta
     */
    public int idTienda;
    public int idEmpleado;
    public float totalDescuento = 0;
    public int puntosCliente = 0;
    public int puntosAntes = 0;
    public int descuentoGastado = 0;

    public boolean confirmarDescuento = false;

    public PanelVenta(int idTienda, int idEmpleado) {

        this.idTienda = idTienda;
        this.idEmpleado = idEmpleado;
        this.idTienda = idTienda;
        initComponents();
        agregarDatosSelect();
        MostrarLupa();
        jTableProductos.setModel(tableModel);
        ventaDAO = new VentaDAO(conexion);
        txtTarjeta.addItem("comun");
        txtTarjeta.addItem("oro");
        txtTarjeta.addItem("platino");
        txtTarjeta.addItem("diamante");
        txtTarjeta.addItem("ninguna");

    }

    CustomTableModel tableModel = new CustomTableModel(new Object[]{"Nombre", "Cantidad", "Precio"}, 0);
    private double totalVenta = 0.0;
    private VentaDAO ventaDAO;

    Connection conexion = new Conexion().establecerConexion();

    // Método para cargar la lista de productos disponibles en la tienda
    // Método para actualizar el combo box de productos disponibles
    public void agregarDatosSelect() {

        String sql = "SELECT nombreProducto FROM ControlEmpresas.Producto WHERE idTienda = ? AND estado = false AND cantidad > 0";
        PreparedStatement pst;
        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, idTienda);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String nombreProducto = rs.getString("nombreProducto");
                System.out.println("nom " + nombreProducto);
                jProducto.addItem(nombreProducto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(trasladarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int obtenerItem() {
        String sql = "SELECT idProducto FROM ControlEmpresas.Producto WHERE idTienda = ? AND nombreProducto = ?";
        PreparedStatement pst;
        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, idTienda);
            pst.setString(2, jProducto.getSelectedItem().toString());
            ResultSet rs = pst.executeQuery();

            // Mueve el cursor al primer registro (si existe)
            if (rs.next()) {
                int aux = rs.getInt("idProducto");
                return aux;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(trasladarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean cantidadDisponible() {
        int item = obtenerItem();
        if (item != 0) {
            String sql = "SELECT cantidad FROM ControlEmpresas.Producto WHERE idTienda = ? AND idProducto = ?";
            PreparedStatement pst;
            try {
                pst = conexion.prepareStatement(sql);
                pst.setInt(1, idTienda);
                pst.setInt(2, item);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    int aux = rs.getInt("cantidad");
                    int cantidadSeleccionada = (int) jCantidadProd.getValue();
                    return aux >= cantidadSeleccionada;
                }
            } catch (SQLException ex) {
                Logger.getLogger(trasladarProducto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public double obtenerPrecioProducto() {
        String sql = "SELECT precio FROM ControlEmpresas.Producto WHERE idTienda = ? AND idProducto = ?";
        PreparedStatement pst;
        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, idTienda);
            pst.setInt(2, obtenerItem());
            ResultSet rs = pst.executeQuery();

            // Mueve el cursor al primer registro (si existe)
            if (rs.next()) {
                double precio = rs.getDouble("precio");
                return precio;
            }
        } catch (SQLException ex) {
            Logger.getLogger(trasladarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0.0; // En caso de no encontrar el precio, retorna un valor predeterminado
    }

    public void restarCantidadProducto(String nombreProducto, int cantidadSeleccionada) {
        String sql = "UPDATE ControlEmpresas.Producto SET cantidad = cantidad - ? WHERE idTienda = ? AND nombreProducto = ?";
        PreparedStatement pst;
        try {
            pst = conexion.prepareStatement(sql);
            pst.setInt(1, cantidadSeleccionada);
            pst.setInt(2, idTienda);
            pst.setString(3, nombreProducto);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(trasladarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarTotalDeVenta() {

        lbTotal.setText("Q." + totalVenta);

    }

    private int obtenerIdProductoPorNombre(String nombreProducto) {
        // Implementa la lógica para obtener el ID del producto según su nombre
        // Puedes hacer una consulta a la base de datos aquí
        // Debes retornar el ID del producto encontrado o -1 si no se encuentra
        // Por ejemplo:
        int idProducto = -1;
        String sql = "SELECT idProducto FROM ControlEmpresas.Producto WHERE nombreProducto = ?";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, nombreProducto);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                idProducto = rs.getInt("idProducto");
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(PanelVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idProducto;
    }

    private void reiniciarVenta() {
        // Limpia los campos y la tabla para una nueva venta
        txtNit.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtPuntos.setText("");
        txtTarjeta.setSelectedIndex(4);
        txtDireccion.setText("");
        txtDPI.setText("");
        lbTotal.setText("Q.0.0");
        tableModel.setRowCount(0); // Limpia la tabla
        totalVenta = 0.0; // Restablece el total de la venta
        lbDescuento.setText("");

    }

    private int getTarjeta(String tarjeta) {

        if (tarjeta.equals("comun")) {
            return 0;
        }
        if (tarjeta.equals("oro")) {
            return 1;
        }
        if (tarjeta.equals("platino")) {
            return 2;
        }
        if (tarjeta.equals("diamante")) {
            return 3;
        }
        if (tarjeta.equals("ninguna")) {
            return 4;
        }
        return 0;
    }

    // Método para calcular el descuento
    private double calcularDescuento(String tipoTarjeta, int puntosCliente) {
        double descuento = 0.0;

        switch (tipoTarjeta) {
            case "comun":
                descuento = puntosCliente * 0.05; // 5 puntos equivalen a Q1.00 de descuento
                break;
            case "oro":
                descuento = puntosCliente * 0.1; // 10 puntos equivalen a Q1.00 de descuento
                break;
            case "platino":
                descuento = puntosCliente * 0.2; // 20 puntos equivalen a Q1.00 de descuento
                break;
            case "diamante":
                descuento = puntosCliente * 0.3; // 30 puntos equivalen a Q1.00 de descuento
                break;
            default:
                break;
        }

        return descuento;
    }

    private int calcularPuntos(String tipoTarjeta, int puntos, int gastado, int actual) {
        double descuento = 0.0;
        int aux;
        int puntosActuales;

        System.out.println("esta entrando");
        switch (tipoTarjeta) {
            case "comun":
                //calcular los puntos fijos sin lo gastado acualmente y obtener el residuo
                aux = gastado % 200;
                System.out.println("---------->Residuo " + aux);

                puntosActuales = ((aux + actual) / 200);
                System.out.println("---------->Monto sin multiplicar " + aux);
                puntosActuales = puntosActuales * 5;
                System.out.println("---------->Puntos finales " + puntosActuales);
                return puntosActuales;
            //int residuo = gastado/puntos
            //calcular los puntos con el residuo y lo gastado
            case "oro":
                aux = gastado % 200;
                puntosActuales = ((aux + actual) / 200);
                puntosActuales = puntosActuales * 10;
                return puntosActuales;
            case "platino":
                aux = gastado % 200;
                puntosActuales = ((aux + actual) / 200);
                puntosActuales = puntosActuales * 20;
                return puntosActuales;
            case "diamante":
                aux = gastado % 200;
                puntosActuales = ((aux + actual) / 200);
                puntosActuales = puntosActuales * 30;
                return puntosActuales;
            default:
                break;
        }
        return 0;

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
        txtbuscador = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPuntos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        lbTotal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jCantidadProd = new javax.swing.JSpinner();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnIngresarProd = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jProducto = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbDescuento = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnConfirmarDescuento = new javax.swing.JLabel();
        btbuscar = new javax.swing.JLabel();
        btnIngresarCliente = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtDPI = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1500, 710));
        setMinimumSize(new java.awt.Dimension(1500, 710));
        setPreferredSize(new java.awt.Dimension(1500, 710));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("BUSCAR CLIENTE POR NIT");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        txtbuscador.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtbuscador.setToolTipText("");
        txtbuscador.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 2, true));
        txtbuscador.setOpaque(true);
        txtbuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscadorActionPerformed(evt);
            }
        });
        add(txtbuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 30));

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("NIT:");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 40, -1));

        txtNit.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNit.setToolTipText("");
        txtNit.setBorder(null);
        txtNit.setOpaque(true);
        txtNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNitActionPerformed(evt);
            }
        });
        add(txtNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 100, 33));

        jLabel3.setText("_____________");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 110, -1));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 1020, 10));

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("NOMBRE:");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, -1, -1));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNombre.setToolTipText("");
        txtNombre.setBorder(null);
        txtNombre.setOpaque(true);
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 260, 33));

        jLabel8.setText("___________________________");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 260, -1));

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("TELÉFONO:");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, -1, -1));

        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTelefono.setToolTipText("");
        txtTelefono.setBorder(null);
        txtTelefono.setOpaque(true);
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 120, 33));

        jLabel10.setText("________________________");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 120, -1));

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("PUNTOS:");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, -1));

        txtPuntos.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtPuntos.setToolTipText("");
        txtPuntos.setBorder(null);
        txtPuntos.setOpaque(true);
        txtPuntos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPuntosActionPerformed(evt);
            }
        });
        add(txtPuntos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 100, 33));

        jLabel12.setText("_____________");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 110, -1));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 1020, 10));

        jLabel15.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("TOTAL");
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, -1, -1));

        lbTotal.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lbTotal.setToolTipText("");
        lbTotal.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        lbTotal.setOpaque(true);
        lbTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbTotalActionPerformed(evt);
            }
        });
        add(lbTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 120, 33));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(22, 199, 154), 1, true));

        jCantidadProd.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jCantidadProd.setBorder(null);

        jLabel19.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Cantidad");

        jLabel20.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Producto");

        btnIngresarProd.setBackground(new java.awt.Color(17, 105, 142));
        btnIngresarProd.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnIngresarProd.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresarProd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIngresarProd.setText("CONFIRMAR");
        btnIngresarProd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresarProd.setOpaque(true);
        btnIngresarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarProdMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresarProdMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresarProdMouseExited(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText(" AGREGAR PRODCUTOS");

        jProducto.setEditable(true);
        jProducto.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jProducto.setToolTipText("");
        jProducto.setBorder(null);
        jProducto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jProducto.setDoubleBuffered(true);
        jProducto.setFocusable(false);
        jProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProductoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jProductoMouseEntered(evt);
            }
        });
        jProducto.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jProductoPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19)
                                    .addComponent(jCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel23)))
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(btnIngresarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel19)
                .addGap(8, 8, 8)
                .addComponent(jCantidadProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnIngresarProd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 370, 290));

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("DIRECCIÓN:");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDireccion.setToolTipText("");
        txtDireccion.setBorder(null);
        txtDireccion.setOpaque(true);
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, 220, 33));

        jLabel2.setText("_________________________________________");
        jLabel2.setToolTipText("");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(22, 199, 154), 1, true));

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

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("  PRODCUTOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 464, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, 590, 290));

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("TARJETA:");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

        lbDescuento.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        lbDescuento.setToolTipText("");
        lbDescuento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        lbDescuento.setOpaque(true);
        lbDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbDescuentoActionPerformed(evt);
            }
        });
        add(lbDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 650, 120, 33));

        jLabel21.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("TOTAL CON DESCUENTO");
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 630, -1, -1));

        btnConfirmarDescuento.setBackground(new java.awt.Color(22, 199, 154));
        btnConfirmarDescuento.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnConfirmarDescuento.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarDescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConfirmarDescuento.setText("APLICAR DESCUENTO");
        btnConfirmarDescuento.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmarDescuento.setOpaque(true);
        btnConfirmarDescuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmarDescuentoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmarDescuentoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmarDescuentoMouseExited(evt);
            }
        });
        add(btnConfirmarDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 640, 220, 40));

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

        btnIngresarCliente.setBackground(new java.awt.Color(17, 105, 142));
        btnIngresarCliente.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnIngresarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIngresarCliente.setText("INGRESAR NUEVO");
        btnIngresarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresarCliente.setOpaque(true);
        btnIngresarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarClienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresarClienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresarClienteMouseExited(evt);
            }
        });
        add(btnIngresarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 230, 170, 40));

        btnConfirmar.setBackground(new java.awt.Color(25, 69, 107));
        btnConfirmar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnConfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnConfirmar.setText("CONFIRMAR VENTA");
        btnConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnConfirmar.setOpaque(true);
        btnConfirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnConfirmarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConfirmarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConfirmarMouseExited(evt);
            }
        });
        add(btnConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 640, 220, 40));

        txtTarjeta.setEditable(true);
        txtTarjeta.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTarjeta.setToolTipText("");
        txtTarjeta.setBorder(null);
        txtTarjeta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtTarjeta.setDoubleBuffered(true);
        txtTarjeta.setFocusable(false);
        txtTarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTarjetaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtTarjetaMouseEntered(evt);
            }
        });
        txtTarjeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTarjetaActionPerformed(evt);
            }
        });
        txtTarjeta.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtTarjetaPropertyChange(evt);
            }
        });
        add(txtTarjeta, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 140, 30));

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("DPI");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));

        txtDPI.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtDPI.setToolTipText("");
        txtDPI.setBorder(null);
        txtDPI.setOpaque(true);
        txtDPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDPIActionPerformed(evt);
            }
        });
        add(txtDPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 120, 33));

        jLabel17.setText("________________________");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 120, -1));
    }// </editor-fold>//GEN-END:initComponents

    public void MostrarLupa() {
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/Images/lupa.png"));
        Image image = iconLogo.getImage();
        Image scaledImage = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        btbuscar.setIcon(scaledIcon);

    }

    private void txtNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void txtPuntosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPuntosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPuntosActionPerformed

    private void lbTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbTotalActionPerformed

    private void jProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProductoMouseClicked
        // TODO add your handling code here:
        //noti.setBackground(Color.white);
    }//GEN-LAST:event_jProductoMouseClicked

    private void jProductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProductoMouseEntered
        // TODO add your handling code here:
        //noti.setBackground(new Color(255, 255, 255));
    }//GEN-LAST:event_jProductoMouseEntered

    private void jProductoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jProductoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jProductoPropertyChange

    private void btnIngresarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarProdMouseClicked
        // TODO add your handling code here:
        if (cantidadDisponible()) {
            String nombreProducto = jProducto.getSelectedItem().toString();
            int cantidadSeleccionada = (int) jCantidadProd.getValue();
            double precioProducto = obtenerPrecioProducto();
            double totalProducto = cantidadSeleccionada * precioProducto;

            // Restar la cantidad seleccionada del producto en la base de datos
            restarCantidadProducto(nombreProducto, cantidadSeleccionada);

            // Agregar el producto y su total a la tabla
            Object[] rowData = {nombreProducto, cantidadSeleccionada, totalProducto};
            tableModel.addRow(rowData);

            // Actualizar el total de la venta (sumar el total del producto)
            totalVenta += totalProducto;

            // Mostrar el total de la venta en otro lugar (si lo deseas)
            mostrarTotalDeVenta();

            System.out.println("Producto confirmado: " + nombreProducto);
        } else {
            System.out.println("No disponible");
        }
    }//GEN-LAST:event_btnIngresarProdMouseClicked

    private void btnIngresarProdMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarProdMouseEntered
        // TODO add your handling code here:
        btnIngresarProd.setBackground(new Color(22, 199, 154));
    }//GEN-LAST:event_btnIngresarProdMouseEntered

    private void btnIngresarProdMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarProdMouseExited
        // TODO add your handling code here:
        btnIngresarProd.setBackground(new Color(25, 69, 107));
    }//GEN-LAST:event_btnIngresarProdMouseExited

    private void lbDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbDescuentoActionPerformed

    private void btnConfirmarDescuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmarDescuentoMouseClicked
        // TODO add your handling code here:
        String tipoTarjeta = txtTarjeta.getSelectedItem().toString();

        // Obtén la cantidad de puntos del cliente
        puntosCliente = Integer.parseInt(txtPuntos.getText());
        puntosAntes = Integer.parseInt(txtPuntos.getText());

        // Crea un cuadro de diálogo personalizado para ingresar la cantidad de puntos a utilizar
        String input = JOptionPane.showInputDialog(this, "Tipo de tarjeta: " + tipoTarjeta + "\nPuntos del cliente: " + puntosCliente
                + "\nIngrese la cantidad de puntos a utilizar como descuento:");

        if (input != null && !input.isEmpty()) {
            try {
                int puntosDescuento = Integer.parseInt(input);
                if (puntosDescuento > puntosCliente) {
                    JOptionPane.showMessageDialog(this, "La cantidad de puntos a utilizar no puede ser mayor que los puntos disponibles.");
                } else {
                    // Calcula el descuento en función de los puntos ingresados
                    double descuento = calcularDescuento(tipoTarjeta, puntosDescuento);

                    // Resta el descuento al total de la venta
                    totalVenta -= descuento;
                    totalDescuento = (float) descuento;

                    // Resta los puntos utilizados al cliente
                    puntosCliente -= puntosDescuento;
                    txtPuntos.setText(String.valueOf(puntosCliente));
                    confirmarDescuento = true;
                    System.out.println("Los nuevos puntos: " + puntosCliente);

                    // Muestra un mensaje con el tipo de tarjeta, la cantidad de puntos y el descuento aplicado
                    JOptionPane.showMessageDialog(this, "Tipo de tarjeta: " + tipoTarjeta
                            + "\nPuntos del cliente: " + puntosCliente
                            + "\nDescuento aplicado: Q" + descuento);

                    // Actualiza el total de la venta
                    lbDescuento.setText("Q." + totalVenta);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida de puntos.");
            }
        }
    }//GEN-LAST:event_btnConfirmarDescuentoMouseClicked

    private void btnConfirmarDescuentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmarDescuentoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmarDescuentoMouseEntered

    private void btnConfirmarDescuentoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmarDescuentoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmarDescuentoMouseExited

    private void txtbuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscadorActionPerformed

    private void btbuscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbuscarMouseClicked
        // TODO add your handling code here:
        // Obtén el NIT ingresado por el usuario desde el campo de texto txtbuscador
        String nitCliente = txtbuscador.getText().trim();

        if (!nitCliente.isEmpty()) {
            // Realiza una consulta SQL para buscar al cliente por NIT
            String consulta = "SELECT nombreCliente, telefono, dpi, tarjeta, puntos, descuento, direccion "
                    + "FROM ControlPersonal.Cliente WHERE nitCliente = ?";

            Connection conexion = new Conexion().establecerConexion();

            try {
                PreparedStatement pstmt = conexion.prepareStatement(consulta);
                pstmt.setString(1, nitCliente); // Asigna el valor del NIT a la consulta
                ResultSet resultado = pstmt.executeQuery();

                boolean clienteEncontrado = false; // Variable para indicar si se encontró un cliente

                while (resultado.next()) {
                    String nombre = resultado.getString("nombreCliente");
                    String telefono = resultado.getString("telefono");
                    String dpi = resultado.getString("dpi");
                    String tarjeta = resultado.getString("tarjeta");
                    int puntos = resultado.getInt("puntos");
                    double descuento = resultado.getDouble("descuento");
                    String direccion = resultado.getString("direccion");

                    // Establece los atributos del cliente en los campos de texto
                    txtNit.setText(nitCliente);
                    txtNombre.setText(nombre);
                    txtTelefono.setText(telefono);
                    txtPuntos.setText(String.valueOf(puntos));
                    txtTarjeta.setSelectedIndex(getTarjeta(tarjeta));
                    txtDireccion.setText(direccion);
                    txtDPI.setText(dpi);

                    descuentoGastado = (int) descuento;

                    clienteEncontrado = true; // Se encontró al menos un cliente
                }

                resultado.close();
                pstmt.close();
                conexion.close();

                if (!clienteEncontrado) {
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al buscar el cliente: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un NIT válido para buscar al cliente.");
        }
    }//GEN-LAST:event_btbuscarMouseClicked

    private void btbuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbuscarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btbuscarMouseEntered

    private void btbuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btbuscarMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btbuscarMouseExited

    private void btnIngresarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarClienteMouseClicked
        // TODO add your handling code here:

        try {
            String sql = "INSERT INTO ControlPersonal.Cliente(nitCliente, nombreCliente, telefono, dpi, tarjeta, puntos, descuento, direccion) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

            PreparedStatement pst = conexion.prepareStatement(sql);
            pst.setString(1, txtNit.getText());
            pst.setString(2, txtNombre.getText());
            pst.setString(3, txtTelefono.getText());
            pst.setString(4, txtDPI.getText());
            pst.setString(5, txtTarjeta.getSelectedItem().toString());
            pst.setInt(6, Integer.parseInt(txtPuntos.getText()));
            pst.setInt(7, 0); // Se corrige aquí
            pst.setString(8, txtDireccion.getText());

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("La inserción se realizó correctamente.");
                JOptionPane.showMessageDialog(this, "Cliente Registrado");
            }

            // jLabel5.setText("Producto ingresada");
            //txtname.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(ingresarProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnIngresarClienteMouseClicked

    private void btnIngresarClienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarClienteMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIngresarClienteMouseEntered

    private void btnIngresarClienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarClienteMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_btnIngresarClienteMouseExited

    private void btnConfirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmarMouseClicked
        // TODO add your handling code here:
        if (confirmarDescuento != true) {
            puntosCliente = Integer.parseInt(txtPuntos.getText());
        }
        double descuentoUtilizado = 0;
        String nitCliente = txtNit.getText().trim();
        int puntosUtilizados = Integer.parseInt(txtPuntos.getText());
        String textoLabel = lbDescuento.getText();
        if (textoLabel != null && !textoLabel.trim().isEmpty()) {
            String descuentoString = textoLabel.trim().replace("Q.", ""); // Eliminar "Q." si está presente
            try {
                descuentoUtilizado = Double.parseDouble(descuentoString);
            } catch (NumberFormatException e) {
                // Manejar la excepción si el texto no es un número válido
            }
        } else {
            // Manejar el caso en el que el label esté vacío
            String descuentoString = lbTotal.getText().trim().replace("Q.", ""); // Eliminar "Q." si está presente
            try {
                descuentoUtilizado = Double.parseDouble(descuentoString);
            } catch (NumberFormatException e) {
                // Manejar la excepción si el texto no es un número válido
            }

        }
        int actual = (int) totalVenta;
        puntosCliente = puntosCliente + calcularPuntos(txtTarjeta.getSelectedItem().toString(), puntosAntes, descuentoGastado, actual);
        System.out.println("puntosssssssssssss " + puntosCliente);

// Verifica que se haya ingresado un NIT válido
        if (!nitCliente.isEmpty()) {
            try {
                // Actualizacion de DESCUENTO CLIENTE
                String sqlActualizarDescuento = "UPDATE ControlPersonal.Cliente SET descuento = descuento + ? WHERE nitCliente = ?";
                PreparedStatement pstActualizarDescuento = conexion.prepareStatement(sqlActualizarDescuento);
                pstActualizarDescuento.setDouble(1, descuentoUtilizado);
                pstActualizarDescuento.setString(2, nitCliente);
                pstActualizarDescuento.executeUpdate();

                // Actualizacion de PUNTOS CLIENTE
                String sqlActualizarPuntos = "UPDATE ControlPersonal.Cliente SET puntos =  ? WHERE nitCliente = ?";
                PreparedStatement pstActualizarPuntos = conexion.prepareStatement(sqlActualizarPuntos);
                pstActualizarPuntos.setInt(1, puntosCliente);
                pstActualizarPuntos.setString(2, nitCliente);
                pstActualizarPuntos.executeUpdate();

                // Crea una instancia de la clase Venta con los datos de la venta
                Venta venta = new Venta(0, idEmpleado, nitCliente, new Date(), totalVenta, descuentoUtilizado, idTienda);

                // Crea una lista para almacenar los detalles de la venta
                List<DetalleVenta> detallesVenta = new ArrayList<>();

                // Recorre las filas de la tabla jTableProductos para obtener los detalles
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String nombreProducto = tableModel.getValueAt(i, 0).toString();
                    int cantidad = Integer.parseInt(tableModel.getValueAt(i, 1).toString());
                    double precioUnitario = Double.parseDouble(tableModel.getValueAt(i, 2).toString()) / cantidad;

                    // Obtén el ID del producto según el nombre
                    int idProducto = obtenerIdProductoPorNombre(nombreProducto);

                    if (idProducto != -1) { // Verifica que se haya obtenido un ID válido
                        // Crea un objeto DetalleVenta y agrégalo a la lista
                        DetalleVenta detalle = new DetalleVenta(0, idProducto, cantidad, precioUnitario, nombreProducto);
                        detallesVenta.add(detalle);
                    } else {
                        JOptionPane.showMessageDialog(this, "El producto '" + nombreProducto + "' no se encuentra en la base de datos.");
                    }
                }

                // Llama al método para insertar la venta en la base de datos
                ventaDAO.insertarVenta(venta, detallesVenta);

                // Restablece los campos y la tabla para una nueva venta
                reiniciarVenta();

                JOptionPane.showMessageDialog(this, "Venta registrada con éxito.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al registrar la venta: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un NIT válido del cliente.");
        }
    }//GEN-LAST:event_btnConfirmarMouseClicked

    private void btnConfirmarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfirmarMouseEntered

    private void btnConfirmarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConfirmarMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_btnConfirmarMouseExited

    private void txtTarjetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTarjetaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaMouseClicked

    private void txtTarjetaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTarjetaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaMouseEntered

    private void txtTarjetaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtTarjetaPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaPropertyChange

    private void txtTarjetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTarjetaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTarjetaActionPerformed

    private void txtDPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDPIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDPIActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btbuscar;
    private javax.swing.JLabel btnConfirmar;
    private javax.swing.JLabel btnConfirmarDescuento;
    private javax.swing.JLabel btnIngresarCliente;
    private javax.swing.JLabel btnIngresarProd;
    private javax.swing.JSpinner jCantidadProd;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> jProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTableProductos;
    private javax.swing.JTextField lbDescuento;
    private javax.swing.JTextField lbTotal;
    private javax.swing.JTextField txtDPI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPuntos;
    private javax.swing.JComboBox<String> txtTarjeta;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtbuscador;
    // End of variables declaration//GEN-END:variables
}
