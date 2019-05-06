	package com.hunter.admin.money;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class MoneyDayTableModel extends AbstractTableModel {
		String[] columnTitle = { "No.", " ���̺� ��ȣ", "ȸ�� ��ȣ", "���� �ð�", "�ݾ�"};
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