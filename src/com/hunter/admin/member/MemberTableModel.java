package com.hunter.admin.member;

import javax.swing.table.AbstractTableModel;

public class MemberTableModel extends AbstractTableModel{
	String[] columnTitle = {"ȸ�� ��ȣ","��ȭ��ȣ","��й�ȣ","��ϳ�¥"};
	Object[][] data;	
	public String getColumnName(int col) {
		return columnTitle[col];
	}
	public int getColumnCount() {
		return columnTitle.length;
	}

	public int getRowCount() {
		return data.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
}
