package com.hunter.user.chat;

import javax.swing.table.AbstractTableModel;

public class CurrChatListTableModel extends AbstractTableModel{
	String[] columnTitle= {"No","Host","Guest"};
	Object[][] data;//db�����ؼ� �޸𸮿� �ø�
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnTitle.length;
	}
	//�÷� ���� ǥ�� �޼���
	@Override
	public String getColumnName(int col) {
		return columnTitle[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
