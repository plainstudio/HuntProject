package com.hunter.user.home;

import javax.swing.table.AbstractTableModel;

public class BillModel extends AbstractTableModel{
	String[] columnName= {"no.","�޴��̸�","�޴�����","����"};
	String[][] data=new String[1][1];
	
	public int getRowCount() {
		return data.length;
	}
	public int getColumnCount() {
		return columnName.length;
	}
	//�÷��� �̸��� ��������
	public String getColumnName(int col) {
		return columnName[col];
	}
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	
}
