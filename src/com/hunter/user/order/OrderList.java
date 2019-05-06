package com.hunter.user.order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.hunter.user.UserMain;

public class OrderList extends JPanel {
	UserMain main;
	JPanel p_tab; // ��ư3�� ���� ��
	JPanel p_center; // ���� ����Ʈ�� ���� �г�
	JButton bt_food; // ���ַ�
	JButton bt_drink; // �ַ�
	JButton bt_side; // ���̵��
	JPanel[] pages = new JPanel[3];

	List cartList = new ArrayList();
	Bag bag;
	int cart_id;
	int table_no;
	public OrderList(UserMain main, Bag bag) {
		// ����
		this.main = main;
		this.bag = bag;
		this.table_no = bag.orderMain.table_no;
		p_tab = new JPanel();
		p_center = new JPanel();
		bt_food = new JButton("���ַ�");
		bt_food.setBounds(0, 0, 152, 50);
		bt_drink = new JButton("�ַ�");
		bt_drink.setBounds(150, 0, 158, 50);
		bt_side = new JButton("���̵��");
		bt_side.setBounds(306, 0, 144, 50);

		// ���� /�ַ� / ���̵�� ������ �迭�� ���
		pages[0] = new FoodPage(main, this, bag);
		pages[1] = new DrinkPage(main, this, bag);
		pages[2] = new SidePage(main, this, bag);
		p_tab.setLayout(null);

		// �� �κп� ��ư ����
		p_tab.add(bt_food);
		p_tab.add(bt_drink);
		p_tab.add(bt_side);

		// ���ǿ� �������� ����
		p_center.add(pages[0]);
		p_center.add(pages[1]);
		p_center.add(pages[2]);

		setLayout(new FlowLayout());

		// �� ��ư�� �ش��ϴ� ������ �޼��� ȣ��
		bt_food.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPage(0);
			}
		});

		bt_drink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPage(1);
			}
		});

		bt_side.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPage(2);
			}
		});

		setLayout(new BorderLayout());
		p_tab.setPreferredSize(new Dimension(700, 50));
		p_center.setPreferredSize(new Dimension(600, 750));
		add(p_tab, BorderLayout.NORTH);
		add(p_center);
		//System.out.println("�׽�Ʈ ���̺�"+table_no);
	}

	// p_center ȭ�� ��ȯ �޼���
	public void showPage(int page) {
		for (int i = 0; i < pages.length; i++) {
			if (i == page) {
				pages[i].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}
		}
	}

	/*��ٱ��Ͽ� ���� �޴��� �ִ��� ��ȸ*/
	public int dupulicateCheck(int table_no, int menu_list_id) {
		int cart_id=0;
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		StringBuffer sb = new StringBuffer();
		
		sb.append("select * from cart where menu_list_id=? and table_no=?"); //���� �α��� �� ����� ���ؼ�...���߿� table_info_id?�� �ٲ������� ����##############
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, menu_list_id);
			pstmt.setInt(2, table_no); 
			rs= pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("�̹� ��ٱ��Ͽ� �����ϳ׿�");
				cart_id=rs.getInt("cart_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		return cart_id;
	}
	
	/* �ߺ��� �޴��� ���� ���� */ 
	public int updateEa(int cart_id) { // table_info_id�� �޾ƿ;��ϴµ�...
		int result=0;
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		//�̹� �ִ��� ����!!!	
		sb.append("update cart set ea=ea+1 where cart_id=?");
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1,cart_id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/* ��ٱ��Ͽ� �ֱ� */ 
	public int goCart(int table_no, int menu_list_id) { // table_info_id�� �޾ƿ;��ϴµ�...
		int result=0;
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();
		//�̹� �ִ��� ����!!!	
		sb.append("insert into cart(cart_id,table_no,menu_list_id,ea)");
		sb.append(" values(seq_cart_id.nextval, ?, ?, 1)");
		
		try {
			pstmt=con.prepareStatement(sb.toString());
			pstmt.setInt(1, table_no);
			pstmt.setInt(2, menu_list_id);
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}














