package com.hunter.admin.order;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.hunter.admin.Main;
import java.awt.Component;

public class OrderMain extends JPanel {
	Main main;

	JLabel l_order;
	JTable orderTable;
	JScrollPane scroll;
	OrderTableModel orderTableModel;
	JButton bt_ok;
	JButton bt_delete;

//////////////////////////수정///////////////////////////////////////
	int col;
	int row;
//////////////////////////////////////////////////////////////////////

	public OrderMain(Main main) {
		setBackground(new Color(230, 230, 250));
		this.main = main;

		setPreferredSize(new Dimension(1400, 800));
		setLayout(null);

		l_order = new JLabel("실시간 주문현황");
		l_order.setHorizontalAlignment(SwingConstants.CENTER);
		l_order.setBounds(550, 25, 300, 30);
		l_order.setFont(new Font("MD개성체", Font.PLAIN, 30));
		add(l_order);

		orderTable = new JTable();
		orderTableModel = new OrderTableModel();
		orderTable.setRowHeight(65);
		orderTable.setModel(orderTableModel = new OrderTableModel());
		orderTable.setFont(new Font("MD개성체", Font.PLAIN, 15));
		scroll = new JScrollPane(orderTable);
		scroll.setLocation(350, 60);
		scroll.setSize(700, 650);
		scroll.setPreferredSize(new Dimension(700, 600));
		add(scroll);

		bt_ok = new JButton("확인");
		bt_ok.setBounds(720, 732, 100, 30);
		bt_ok.setFont(new Font("MD개성체", Font.PLAIN, 15));
		add(bt_ok);

		bt_delete = new JButton("주문 취소");
		bt_delete.setBounds(580, 730, 100, 30);
		bt_delete.setFont(new Font("MD개성체", Font.PLAIN, 15));
		add(bt_delete);

		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (row == 0) {
					JOptionPane.showMessageDialog(main, "확인한 주문을 선택해 주세요!");
					return;
				} else {
					System.out.println("선택한 row: " + row + ", col: " + col);
					
				}
			}
		});

		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (row == 0) {
					JOptionPane.showMessageDialog(main, "취소할 주문을 선택해 주세요!");
					return;
				}else {
					System.out.println("선택한 row: " + row + ", col: " + col);
				}

			}
		});
		//////////////////////////////수정/////////////////////////////////////
		orderTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = orderTable.getSelectedRow();
				col = 0;
				//orderTable.getModel().removeRow(row);
				System.out.println("checkBox의 상태는: " + orderTable.getValueAt(row, col));
			}
		});
		getOrderDetail(); //<<<<<<<메서드추가
		//////////////////////////////////////////////////////////////////////
	}
	//////////////////////////////수정/////////////////////////////////////
	public void getOrderDetail() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select t.table_no, m.menu_list_name, od.order_detail_count, os.order_summary_date");
		sb.append(" from table_info t, entry_list e, menu_list m, order_detail od, order_summary os");
		sb.append(
				" where t.table_info_id=e.table_info_id and e.entry_list_id=os.entry_list_id and os.order_summary_id=od.order_summary_id and od.menu_list_id=m.menu_list_id");

		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			System.out.println("토탈은" + total);
			System.out.println("길이는" + orderTableModel.columnTitle.length);
			Object[][] data = new Object[total][orderTableModel.columnTitle.length];
			rs.beforeFirst();

			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = false;
				data[i][1] = (i + 1);
				data[i][2] = rs.getString("table_no");
				data[i][3] = rs.getString("menu_list_name");
				data[i][4] = rs.getString("order_detail_count");
				data[i][5] = rs.getString("order_summary_date");
			}
			orderTableModel.data = data;// 교체!!

			orderTable.setModel(orderTableModel);
			orderTable.updateUI();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
//////////////////////////////////////////////////////////////////////
}
