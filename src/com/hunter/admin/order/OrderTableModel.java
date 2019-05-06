	package com.hunter.admin.order;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class OrderTableModel extends AbstractTableModel {
		String[] columnTitle = { "선택", "No.", "테이블 번호","메뉴명","수량","시간"};
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
////////////////////////////수정////////////////////////////////////
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}
/////////////////////////////수정//////////////////////////////////
		public void setValueAt(Object obj, int row, int col) {
			if(col==0) {//check 박스만 값을 변경할 수 있도록 조건을 부여
				data[row][col]=obj;
			}
		}
/////////////////////////////수정//////////////////////////////////
		public boolean isCellEditable(int row, int col) {
			return true;
		}
//////////////////////////////////////////////////////////////////////
	
	}