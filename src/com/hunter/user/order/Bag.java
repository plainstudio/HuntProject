package com.hunter.user.order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hunter.user.UserMain;

public class Bag extends JPanel {
	ImageIcon i_cart;// ��ٱ��� ������
	ImageIcon i_list;// �ֹ����� ������
	JLabel l_cart; // ��ٱ��� ������ ��
	JLabel l_list; // �ֹ����� ������ ��
	String[] list = { "C:/java_developer/javaSE/HuntingProject/res/shopping-cart.png",
			"C:/java_developer/javaSE/HuntingProject/res/list.png" };// �����̵� �̹��� �迭

	JTable table;
	MyTableModel model;
	TotalOrderModel totalOrderModel;
	JScrollPane scroll;

	UserMain userMain;
	OrderMain orderMain;
	JPanel p_north;
	JPanel p_south;
	JButton bt_del, bt_pay, bt_card, bt_cash;
	JLabel la_total, la_money, la_won; // �� ������ �հ�
	int row;
	int cart_id;
	int sum = 0;
	int total;

	int order_summary_id;
	boolean check1, check2; // order_summary, order_Detail�� ���������� ���´��� Ȯ�ο���

	int EntryListId;

	public Bag(UserMain userMain, OrderMain orderMain) {
		this.userMain = userMain;
		this.orderMain = orderMain;
		System.out.println(orderMain.table_no);
		bt_del = new JButton("���� �׸� ��ٱ��Ͽ��� ����");
		bt_pay = new JButton("��ü �ֹ��ϱ�");
		bt_card = new JButton("ī�����");
		bt_cash = new JButton("���ݰ���");
		p_north = new JPanel();
		p_south = new JPanel();
		i_cart = new ImageIcon();
		i_list = new ImageIcon();
		l_cart = new JLabel(i_cart);
		l_list = new JLabel(i_list);
		la_total = new JLabel("�� �ݾ� :");
		la_money = new JLabel("");
		la_won = new JLabel("��");
		table = new JTable();
		scroll = new JScrollPane(table);

		setSize(0, l_cart);
		setSize(1, l_list);

		p_north.add(l_cart);
		p_north.add(l_list);

		l_list.setEnabled(false);
		l_cart.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				table.setModel(model);
				table.updateUI();
				
				bt_pay.setVisible(true);
				bt_del.setVisible(true);
				bt_card.setVisible(false);
				bt_cash.setVisible(false);
			}
		});
		l_list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getTotalOrderList();
				
				bt_card.setVisible(true);
				bt_cash.setVisible(true);
				bt_pay.setVisible(false);
				bt_del.setVisible(false);
			}
		});

		la_total.setLayout(new FlowLayout());
		la_money.setLayout(new FlowLayout());
		la_won.setLayout(new FlowLayout());
		bt_del.setLayout(new FlowLayout());
		bt_pay.setLayout(new FlowLayout());
		bt_card.setLayout(new FlowLayout());
		bt_cash.setLayout(new FlowLayout());

		la_total.setPreferredSize(new Dimension(180, 35));
		la_money.setPreferredSize(new Dimension(45, 35));
		la_won.setPreferredSize(new Dimension(20, 35));
		bt_del.setPreferredSize(new Dimension(220, 30));
		bt_pay.setPreferredSize(new Dimension(180, 30));
		bt_card.setPreferredSize(new Dimension(220, 30));
		bt_cash.setPreferredSize(new Dimension(220, 30));

		add(p_north, BorderLayout.NORTH);
		add(scroll);
		add(la_total);
		add(la_money);
		add(la_won);
		add(la_won);
		add(bt_del);
		add(bt_pay);
		add(bt_card);
		add(bt_cash);
		bt_card.setVisible(false);
		bt_cash.setVisible(false);

		table.setRowHeight(100);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				cart_id = (Integer) table.getValueAt(row, 0);
			}
		});
		bt_del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cart_id == 0) {
					JOptionPane.showMessageDialog(null, "������ ��ǰ�� �����ϼ���!");
					return;
				}
				if (JOptionPane.showConfirmDialog(null, "���� �Ͻðڽ��ϱ�?") == JOptionPane.OK_OPTION) {
					delete();

					getCartList();
					table.updateUI();
					la_total.updateUI();
				}
			}

		});
		bt_pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "��ٱ��� ǰ����� �ֹ� �Ͻðھ��?") == JOptionPane.OK_OPTION) {
					payment();
				} else {
					return;
				}
			}
		});
		bt_card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "ī��� �����Ͻðھ��?") == JOptionPane.OK_OPTION) {
					setPayList(2);
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�. �����մϴ�.");
				} else {
					return;
				}
			}
		});
		bt_cash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "�������� �ֹ� �Ͻðھ��?") == JOptionPane.OK_OPTION) {
					setPayList(2);
					JOptionPane.showMessageDialog(null, "������ �Ϸ�Ǿ����ϴ�. �����մϴ�.");
				} else {
					return;
				}
			}
		});
		table.setModel(model = new MyTableModel());
		totalOrderModel = new TotalOrderModel();
		scroll.setPreferredSize(new Dimension(280, 660));
		getCartList();
		setPreferredSize(new Dimension(300, 400));
		if (!la_money.getText().equals(null)) {
			la_money.setText(Integer.toString(getSum()));
		}
		getEntryListId();
	}

	public void getCartList() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select cart_id,menu_list.menu_list_img,menu_list.menu_list_name, menu_list.menu_list_price,cart.ea from cart, menu_list where menu_list.menu_list_id = cart.menu_list_id";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// pstmt.setInt(1, 8); //���� �α��ε� ���̺��� ������ �˾ƾ���..
			rs = pstmt.executeQuery();

			rs.last();
			int total = rs.getRow();
			Object[][] data = new Object[total][model.columnName.length];

			rs.beforeFirst();
			for (int i = 0; i < total; i++) {
				rs.next();
				int cart_id = rs.getInt("cart_id");
				// ImageIcon icon=new ImageIcon(model.path+rs.getString("menu_list_img"));
				// icon=new ImageIcon(icon.getImage().getScaledInstance(50, 50,
				// Image.SCALE_SMOOTH));
				String menu_list_name = rs.getString("menu_list_name");
				int menu_list_price = rs.getInt("menu_list_price");
				int ea = rs.getInt("ea");
				data[i][0] = cart_id;
				data[i][1] = menu_list_name;
				data[i][2] = menu_list_price;
				data[i][3] = ea;
			}
			getSum();
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

	public void delete() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		String sql = "delete from cart where cart_id = " + cart_id;
		try {
			pstmt = con.prepareStatement(sql);

			int result = pstmt.executeUpdate();
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "���� ����!");
			} else {
				JOptionPane.showMessageDialog(this, "���� ����!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getSum();
		la_money.setText(Integer.toString(sum));
	}

	public int getSum() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select sum(menu_list_price*ea) from cart,menu_list where cart.menu_list_id = menu_list.menu_list_id";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.next();
			int total = rs.getInt(1);

			// System.out.println(total);
			sum = total;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	public void payment() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt3 = null;

		String sql3 = "delete from order_summary";
		try {
			pstmt3 = con.prepareStatement(sql3);
			int result = pstmt3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(this, "�ֹ��� �Ϸ�Ǿ����ϴ�!");
		l_list.setEnabled(true);
		// �ֹ��ϱ��ư ������ ��ٱ��� �� ������µ� ����������� orderDetail�̶� orderSummary�� ������ �������� �����ؾ���!!
		sendOrderSummary();
		getOrderSummaryId();
		System.out.println("order_summary_id��" + order_summary_id + " entry_list_id��" + EntryListId);
		 sendOrderDetail(order_summary_id);

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		String sql2 = "select t.table_no, m.menu_list_id,m.menu_list_name, od.order_detail_count, os.order_summary_date from table_info t, entry_list e, menu_list m, order_detail od, order_summary os where t.table_info_id=e.table_info_id and e.entry_list_id=os.entry_list_id and os.order_summary_id=od.order_summary_id and od.menu_list_id=m.menu_list_id";

		try {
			pstmt2 = con.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs2 = pstmt2.executeQuery();

			int table_no = 0;
			int menu_list_id = 0;
			String menu_list_name = "";
			int order_detail_count = 0;
			while (rs2.next()) {
				table_no = rs2.getInt("table_no");
				menu_list_id = rs2.getInt("menu_list_id");
				menu_list_name = rs2.getString("menu_list_name");
				order_detail_count = rs2.getInt("order_detail_count");

				System.out.println("���̺� �ѹ���" + table_no + "\n�޴� ����Ʈ id�� " + menu_list_id + "\n �޴�����Ʈ �̸��� "
						+ menu_list_name + "\n����ī��Ʈ��" + order_detail_count + "\n����Ʈ��");
				// userMain.getSendData("order", Integer.toString(table_no),
				// Integer.toString(menu_list_id), menu_list_name,
				// Integer.toString(order_detail_count));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		PreparedStatement pstmt = null;

		String sql = "delete from cart";
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		getCartList();
		la_money.setText("0");
	}

	// ���� �̱���
	public void sendOrderSummary() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "insert into order_summary(order_summary_id,entry_list_id,order_summary_date) values(seq_order_summary.nextval,"
				+ EntryListId + ",sysdate)";
		int result;
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate();
			if (result == 0) {
				check1 = false;
			} else {
				check1 = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* order_Detail�� ������ order_summary_id�� �˰��־�� �ϱ� ������.. */
	public void getOrderSummaryId() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from order_summary order by order_summary_id desc";

		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.next();
			int get_order_summary_id = rs.getInt(1);
			order_summary_id = get_order_summary_id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ���� �̱���
	public void sendOrderDetail(int order_summary_id) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// order_detail�� ���� Į���� ��ȸ�ϱ�!!
		String sql = "select order_summary_id,cart.menu_list_id,cart.ea as order_detail_count from cart,order_summary where cart.table_no in  ( select entry_list.table_info_id from entry_list,order_summary where entry_list.entry_list_id = order_summary.entry_list_id) and order_summary_id = "
				+ order_summary_id;
		int menu_list_id = 0;
		int order_detail_count = 0;

		
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				menu_list_id = rs.getInt("menu_list_id");
				order_detail_count = rs.getInt("order_detail_count");
				PreparedStatement pstmt2 = null;
				ResultSet rs2 = null;

				String sql2 = "insert into order_detail(order_detail_id,order_summary_id,menu_list_id,order_detail_count) values(seq_order_detail.nextval,"
						+ order_summary_id + "," + menu_list_id + "," + order_detail_count + ")";
				
				System.out.println(sql2);
				
				int result2;
				try {
					pstmt2 = con.prepareStatement(sql2);
					result2 = pstmt2.executeUpdate();
					if (result2 == 0) {
						check1 = false;
					} else {
						check1 = true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("�޴�����Ʈ ���̵��" + menu_list_id);
		System.out.println("���������� ī��Ʈ�� " + order_detail_count);

	}

	public void increaseEA(int menu_list_id) {
		// JOptionPane.showMessageDialog(this, "�̹� ��ٱ��Ͽ� ����ִ�");
		System.out.println("increase ea ����!");
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int ea = 0;
		String sql = "select * from cart where menu_list_id = " + menu_list_id;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				ea = rs.getInt("ea");
				System.out.println("�ٲ���� " + ea);
				ea++;
				System.out.println("�ٲ���� " + ea);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		String sql2 = "update cart set ea = " + ea + " where menu_list_id = " + menu_list_id;
		try {
			pstmt2 = con.prepareStatement(sql2);
			rs2 = pstmt2.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.updateUI();
	}

	// entry_List_id ���!!
	public void getEntryListId() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from entry_list order by entry_list_id desc";
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.next();
			EntryListId = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // System.out.println("entry_list_id��"+EntryListId);
	}

	public void setSize(int i, JLabel label) {
		ImageIcon icon = new ImageIcon(list[i]);
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(145, 45, Image.SCALE_SMOOTH);
		ImageIcon newImc = new ImageIcon(newImg);
		label.setIcon(newImc);
	}

	// �� �ֹ� ������ ���̺� �ѷ��ִ� �޼���
	public void getTotalOrderList() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select menu_list_name, menu_list_price, order_detail_count, order_summary_date");
		sb.append(" from order_detail od, order_summary os, menu_list m");
		sb.append(
				" where os.order_summary_id=? and os.order_summary_id=od.order_summary_id and od.menu_list_id=m.menu_list_id");

		System.out.println(sb.toString());

		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, order_summary_id);// order_summary_id
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			Object[][] data = new Object[total][totalOrderModel.columnTitle.length];
			rs.beforeFirst();

			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][0] = (i + 1);
				data[i][1] = rs.getString("menu_list_name");
				data[i][2] = rs.getString("menu_list_price");
				data[i][3] = rs.getString("order_detail_count");
				data[i][4] = rs.getString("order_summary_date");
			}
			totalOrderModel.data = data;// ��ü!!
			table.setModel(totalOrderModel);
			System.out.println("�ֹ������� ���̴�" + totalOrderModel.data.length);
			table.updateUI();

			getPayTotal();// �� ���� �ݾ� ���ϱ�

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ���̺��� �� �ݾ� ���ϴ� �޼���
	public void getPayTotal() {
		for (int i = 0; i < totalOrderModel.data.length; i++) {
			int price = (int) table.getValueAt(i, 2);
			int cnt = (int) table.getValueAt(i, 3);
			int sum = price * cnt;
			total += sum;
		}
		la_money.setText((Integer.toString(total))); // �� ���� �ݾ� ����
	}
	//pay_list�� ���� ������ �޼���
	public void setPayList(int type) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into pay_list(pay_list_id ,order_summary_id, pay_type_id,pay_list_date)");
		sb.append(" values(seq_pay_list.nextval,?,?, sysdate)");
		
		System.out.println(sb.toString());
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, order_summary_id);// order_summary_id
			pstmt.setInt(2, type);// pay_type_id
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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