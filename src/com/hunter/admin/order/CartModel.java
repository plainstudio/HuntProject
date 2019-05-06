	package com.hunter.admin.order;
	
	import java.awt.Image;
	
	import javax.swing.ImageIcon;
	import javax.swing.table.AbstractTableModel;
	
	public class CartModel extends AbstractTableModel {
		String[] columnTitle = { "No.", "테이블 번호","메뉴명","수량"};
		Object[][] data=new Object[4][5];	
		
		public CartModel() {
			data[0][0]="0";
			data[0][1]="11";
			data[0][2]="12";
			data[0][3]="13";
			data[1][0]="1";
			data[1][1]="21";
			data[1][2]="22";
			data[1][3]="23";
			data[2][0]="2";
			data[2][1]="31";
			data[2][2]="32";
			data[2][3]="33";
			data[3][0]="1";
			data[3][1]="41";
			data[3][2]="42";
			data[3][3]="43";
		}
		
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