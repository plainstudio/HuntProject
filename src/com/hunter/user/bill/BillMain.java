package com.hunter.user.bill;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hunter.user.User;
import com.hunter.user.UserMain;

public class BillMain extends JPanel {
	UserMain userMain;
	User user;

	JLabel la_tableNum, la_id;
	JTable table;
	JScrollPane scroll;
	JLabel la_total;
	JButton bt_exit;

	int tableNum;// ���̺� ��ȣ
	String userId;// ����� ���̵�
	int sum = 0; // �� �ֹ� �ݾ�

	BillModel billModel; // ���̺� ��

	public BillMain(UserMain userMain, User user) {
		this.userMain = userMain;
		this.user = user;

		la_tableNum = new JLabel();
		la_id = new JLabel();
		la_total = new JLabel();
		bt_exit = new JButton("�ݱ�");
		// ���̺� ����
		table = new JTable(5, 6);
		scroll = new JScrollPane(table);

		// ���ļ���
		Dimension d = new Dimension(400, 50);
		la_tableNum.setPreferredSize(d);
		la_id.setPreferredSize(d);
		la_total.setPreferredSize(new Dimension(200, 50));

		la_tableNum.setFont(new Font("����", Font.BOLD, 20));
		la_id.setFont(new Font("����", Font.BOLD, 20));
		la_total.setFont(new Font("����", Font.BOLD, 20));

		add(la_tableNum);
		add(la_id);
		add(scroll);
		add(la_total);
		add(bt_exit);

		bt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				sum=0;
			}
		});
		
		setBackground(Color.pink);
		setPreferredSize(new Dimension(500, 650));
	}


	// ���̺� ������ �ϴ� �޼���
	public void getRecords() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select menu_list_name, menu_list_price,order_detail_count");
		sb.append(" from order_detail o, menu_list m");
		sb.append(" where o.menu_list_id=m.menu_list_id");

		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			// ������ �迭�� ���ڵ� ä���ֱ�!!
			rs.last();
			int total = rs.getRow();
			String[][] data = new String[total][4];
			rs.beforeFirst();// ����ġ!!
			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = (Integer.toString(i+1));
				int price=rs.getInt("menu_list_price");
				int count=rs.getInt("order_detail_count");
				sum+=price*count;

				for (int a = 1; a < 4; a++) {
					data[i][a] = rs.getString(a);
				}
			}
			
			// ���� ��������� �����
			billModel = new BillModel();
			billModel.data = data;// ��ü!!

			// setModel�� ��������� ��� ������ ������
			table.setModel(billModel);

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
	// �α����� ����� ���̺��ȣ, ���̵� �����ֱ�
	public void setUserInfo() {
		tableNum = user.getTableNum();
		la_tableNum.setText("���̺� ��ȣ : " + tableNum + "    ");
		la_total.setText("�� �հ�: " + sum + " ��");
		if (user.getFlag()) {
			userId = user.getMemeber().getId();
			la_id.setText("���� ���̵� : " + userId + " ��");
			la_total.setText("�� �հ�: " + sum + " ��");
		} else {
			userId = (Integer.toString(user.getGuest().getGuestId()));
			la_id.setText("Guest " + userId);
		}
	}
	//���̺� ���� ������
	public void tableDesign() {
		table.setRowHeight(50);
		table.getColumn("no.").setPreferredWidth(1);
		table.getColumn("����").setPreferredWidth(1);
		table.updateUI();
	}
}
