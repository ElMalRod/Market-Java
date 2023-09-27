/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emili_zxg0ruq
 */


public class CustomTableModel extends DefaultTableModel {
    public CustomTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 2) {
            return Double.class; 
        } else {
            return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

