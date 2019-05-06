package com.hunter.user.order;

import javax.swing.table.AbstractTableModel;

public class TotalOrderModel extends AbstractTableModel{
	String[] columnTitle = {"번호","메뉴","가격","수량", "주문날짜" };
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

	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
}
