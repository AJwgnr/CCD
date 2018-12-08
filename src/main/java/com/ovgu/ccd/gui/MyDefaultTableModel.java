package com.ovgu.ccd.gui;

/*
 * Overriding DefaultTableModel and  isCellEditable method
 * (history cannot be edited by player)
 */

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel {

    MyDefaultTableModel() {
        super();
    }

    @Override
    public boolean isCellEditable(int a, int b) {
        return false;
    }
}
