	package com.hunter.admin.order;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class OrderTableModel extends AbstractTableModel {
		String[] columnTitle = { "����", "No.", "���̺� ��ȣ","�޴���","����","�ð�"};
		Object[][] data;	
		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnTitle[col];
		}
		public int getColumnCount() {
			return columnTitle.length;
		}
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
////////////////////////////����////////////////////////////////////
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
/////////////////////////////����//////////////////////////////////
		public void setValueAt(Object obj, int row, int col) {
			if(col==0) {//check �ڽ��� ���� ������ �� �ֵ��� ������ �ο�
				data[row][col]=obj;
			}
		}
/////////////////////////////����//////////////////////////////////
		public boolean isCellEditable(int row, int col) {
			return true;
		}
//////////////////////////////////////////////////////////////////////
	
	}