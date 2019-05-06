package com.hunter.admin.member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hunter.admin.Main;
import com.hunter.db.ConnectionManager;

public class MemberMain extends JPanel {
	Main main;
	// ���� ����
	MemberDetail md;
	MemberAdd memberAdd;
	MemberEdit memberEdit;
	

	JPanel memberDetail;
	JPanel p_north; // �˻�
	JPanel p_center; // JTable ��� �г�
	JPanel p_south; // ��� ��ư�� �г�

	// JTable ����
	JTable table;
	JScrollPane scroll;
	MemberTableModel model;

	JLabel l_search; // ���̺��� ��ȸ ��ư
	JButton bt_regist; // ���̺��� ��� ��ư
	JButton bt_edit; // ���̺��� ���� ��ư
	JButton bt_del; // ���̺��� ���� ��ư
	
	JTextField t_search;
	ArrayList nameArray = new ArrayList();

	int member_list_id;

	public MemberMain(Main main) {
		this.main = main;
		// db ����

		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1400, 800));
		


		memberDetail = new JPanel();
		p_center = new JPanel();
		add(p_center,BorderLayout.CENTER);
		p_south = new JPanel();
		add(p_south, BorderLayout.SOUTH);
		p_north=new JPanel();
		add(p_north,BorderLayout.NORTH);


		t_search=new JTextField();
		table = new JTable();
		scroll = new JScrollPane(table);
		model = new MemberTableModel();
		table.setRowHeight(65);
		
		scroll.setPreferredSize(new Dimension(1000, 700));
		bt_regist = new JButton("ȸ�� ���");
		l_search = new JLabel("��ȭ��ȣ�� �˻�");
		bt_edit = new JButton("ȸ�� ����");
		bt_del = new JButton("ȸ�� ����");
		md = new MemberDetail(this);
		
		Dimension d = new Dimension(145, 25);
		bt_regist.setPreferredSize(d);
		l_search.setPreferredSize(new Dimension(100,25));
		t_search.setHorizontalAlignment(SwingConstants.CENTER);
		bt_edit.setPreferredSize(d);
		bt_del.setPreferredSize(d);
		t_search.setPreferredSize(d);
		
		p_north.add(l_search);
		p_north.add(t_search);
		
		p_center.add(scroll);

		p_south.add(bt_regist);
		p_south.add(bt_edit);
		p_south.add(bt_del);

		

		table.setModel(model = new MemberTableModel());
		table.setFont(new Font("MD����ü", Font.PLAIN, 15));
		md.selectAll();

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = 0;
				member_list_id = (Integer) table.getValueAt(row, col);
			}
		});
		bt_regist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
		
		bt_edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (member_list_id == 0) {
					JOptionPane.showMessageDialog(main, "������ ȸ���� �����ϼ���!");
					return;
				}
				edit();
			}
			
		});

		bt_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (member_list_id == 0) {
					JOptionPane.showMessageDialog(main, "������ ȸ���� �����ϼ���!");
					return;
				}
				if (JOptionPane.showConfirmDialog(main, "���� �Ͻðڽ��ϱ�?") == JOptionPane.OK_OPTION) {
					delete();
					md.selectAll();
					table.updateUI();
				}
			}
		});
		 
		 t_search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
				
			}
			
		});

	}

	public void delete() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;

		String sql = "delete from member_list where member_list_id = " + member_list_id;
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);

			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "���� ����!");
				System.out.println(member_list_id);
			} else {
				JOptionPane.showMessageDialog(this, "���� ����!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void regist() {
		memberAdd=new MemberAdd(this);
	}
	
	public void edit() {
		memberEdit=new MemberEdit(this,member_list_id);
	}
	
	public void searchMember(String keyWord) {
	      for (int i = 0; i < table.getRowCount(); i++) {
	         if (nameArray.size() < table.getRowCount()) {
	            nameArray.add(table.getValueAt(i, 1));
	         }
	      }
	      for (int i = 0; i < nameArray.size(); i++) {
	         String name = (String) nameArray.get(i);
	         if (keyWord.equals(name)) {
	            if (table.getValueAt(i, 1) == name) {
	               table.setRowSelectionInterval(0, i);
	            }
	         }
	      }
	   }
	public void search() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String typed=t_search.getText();

		String sql = "select * from member_list where member_list_phone like '%"+typed+"%'";
		//System.out.println(sql);
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();

			Object[][] data = new Object[total][model.columnTitle.length];
			rs.beforeFirst();
			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = rs.getInt("member_list_id");
				data[i][1] = rs.getString("member_list_phone");
				data[i][2] = rs.getString("member_list_pw");
				data[i][3] = rs.getString("member_list_date");

			}
			model.data = data;
			table.updateUI();
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

}
