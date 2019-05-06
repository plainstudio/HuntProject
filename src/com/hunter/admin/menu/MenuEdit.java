package com.hunter.admin.menu;

import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.hunter.admin.Main;
import com.hunter.util.StringUtil;

public class MenuEdit extends JFrame {
	// �޴� �߰��� ��..
	Choice ch_type; // ���ַ� / �ַ� / ���̵�� ���� ī�װ�
	Choice ch_option; // BEST , ���� ���� ī�װ�
	JTextField t_name; // ����� ���� �̸�
	JTextField t_price; // ����� ���� ����
	Canvas can; // �޴� ��� �гο��� �̹��� ������ ĵ����

	JLabel l_image;
	JLabel l_menuname;
	JLabel l_price;
	JLabel l_type;
	JLabel l_option;

	JButton bt_open; // �̹��� ����
	JButton bt_ok; // �� �޴� ���
	JButton bt_cancle; // ���

	// ���� ����
	MenuMain menuMain;
	JFileChooser chooser;
	File file;
	Image regist_img;
	String regist_path;
	String img;
	String userData = "C:/data";
	ArrayList type_list = new ArrayList();
	ArrayList option_list = new ArrayList();
	String check_name;
	String check_price;
	int menu_list_id;
	int id;
	String price, name, type, option;
	ImageIcon icon;

	public MenuEdit(MenuMain menuMain, int menu_list_id) {
		// �޴� �߰��� ��..
		this.menuMain = menuMain;
		this.menu_list_id = menu_list_id;
		setTitle("�� �޴� ���");
		ch_type = new Choice(); // ���ַ� / �ַ� / ���̵�� ���� ī�װ�
		ch_type.setBounds(249, 246, 175, 21);
		ch_option = new Choice(); // BEST , ���� ���� ī�װ�
		ch_option.setBounds(249, 276, 175, 21);
		t_name = new JTextField(); // ����� ���� �̸�
		t_name.setBounds(249, 183, 175, 25);
		t_price = new JTextField(); // ����� ���� ����
		t_price.setBounds(249, 213, 175, 25);

		l_image = new JLabel("�޴� �̹���");
		l_image.setBounds(64, 17, 101, 25);
		l_menuname = new JLabel("�޴� �̸�");
		l_menuname.setBounds(69, 183, 175, 25);
		l_price = new JLabel("����");
		l_price.setBounds(69, 213, 175, 25);
		l_type = new JLabel("�з�");
		l_type.setBounds(69, 243, 175, 25);
		l_option = new JLabel("�ɼ�");
		l_option.setBounds(69, 273, 175, 25);

		bt_open = new JButton("�̹��� ����");
		bt_open.setBounds(249, 98, 175, 25);
		bt_ok = new JButton("���� �ϱ�");
		bt_ok.setBounds(69, 303, 175, 25);
		bt_cancle = new JButton("���� ���");
		bt_cancle.setBounds(249, 303, 175, 25);

		can = new Canvas() {
			public void paint(Graphics g) {
				g.drawImage(regist_img, 0, 0, 145, 145, null);
			}
		};
		can.setBounds(64, 48, 160, 130);

		// ���� ����
		chooser = new JFileChooser("C:/java_developer/javaSE/HuntingProject/res");
		Dimension d = new Dimension(175, 25);

		check_name = t_name.getText();
		check_price = t_price.getText();
		ch_type.setPreferredSize(d);
		ch_option.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_price.setPreferredSize(d);

		l_image.setPreferredSize(new Dimension(450, 10));
		l_menuname.setPreferredSize(d);
		l_price.setPreferredSize(d);
		l_type.setPreferredSize(d);
		l_option.setPreferredSize(d);

		bt_open.setPreferredSize(new Dimension(200, 30));
		bt_ok.setPreferredSize(d);
		bt_cancle.setPreferredSize(d);
		can.setPreferredSize(new Dimension(160, 130));
		getContentPane().setLayout(null);

		getContentPane().add(l_image);
		getContentPane().add(can);
		getContentPane().add(bt_open);
		getContentPane().add(l_menuname);
		getContentPane().add(t_name);
		getContentPane().add(l_price);
		getContentPane().add(t_price);
		getContentPane().add(l_type);
		getContentPane().add(ch_type);
		getContentPane().add(l_option);
		getContentPane().add(ch_option);
		getContentPane().add(bt_ok);
		getContentPane().add(bt_cancle);

		getMenu();

		t_price.setText(price);
		t_name.setText(name);
		// ch_type.set;
		// ch_option.set;
		can.repaint();

		bt_open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile();
			}
		});

		bt_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = t_name.getText();
				String price = t_price.getText();
				if (regist_img == null) {
					JOptionPane.showMessageDialog(menuMain, "�̹����� �߰��ϼ���!!");
					return;
				}
				if (name == null || name.length() == 0) {
					JOptionPane.showMessageDialog(menuMain, "�޴� �̸��� �Է��ϼ���!!");
					return;
				}
				if (price == null || price.length() == 0) {
					JOptionPane.showMessageDialog(menuMain, "�޴� ������ �Է��ϼ���!!");
					return;
				} else {
					upload();
					regist();
				}
			}
		});

		bt_cancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getMenuOption();
		getMenuType();
		setVisible(true);
		setSize(500, 400);
		setLocationRelativeTo(null);
	}

	public void openFile() {
		int result = chooser.showOpenDialog(this);
		if (result == chooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			regist_path = file.getAbsolutePath();
			ImageIcon icon2 = new ImageIcon(file.getAbsolutePath());
			regist_img = icon2.getImage();
			can.repaint();

			img = System.currentTimeMillis() + "." + StringUtil.getExt(regist_path);
		}
	}

	public void regist() {
		Connection con = menuMain.main.getCon();
		PreparedStatement pstmt = null;
		String name = t_name.getText();
		String price = t_price.getText();
		int t_index = ch_type.getSelectedIndex();
		int menu_type_id = (Integer) type_list.get(t_index);
		int o_index = ch_option.getSelectedIndex();
		int menu_option_id = (Integer) option_list.get(o_index);

		String sql = "insert into menu_list(menu_list_id,menu_type_id,menu_option_id,menu_list_name,menu_list_price,menu_list_img)";
		sql += " values(seq_menu_list.nextval," + menu_type_id + "," + menu_option_id + ",'" + name + "','" + price
				+ "','" + img + "')";
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			menuMain.list.add(img);
			if (result == 0) {
				JOptionPane.showMessageDialog(this, "��� ����");
			} else {
				JOptionPane.showMessageDialog(this, "��� ����");
				menuMain.menuDetail.selectAll();
			}
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

	// ���� �з�
	public void getMenuType() {
		Connection con = menuMain.main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select menu_list.menu_list_id,menu_list.menu_type_id,menu_type.menu_type_name from menu_list,menu_type where menu_list.menu_type_id = menu_type.menu_type_id and menu_list_id = "
					+ menu_list_id;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ch_type.add(rs.getString("menu_type_name"));
				type_list.add(rs.getInt("menu_type_id"));
			}
			for (int i = 0; i < type_list.size(); i++) {
				if (ch_type.getItem(i).equals(menuMain.name)) {
					System.out.println(i + "��°���� ��ġ");
					ch_type.select(i);
				}
			}
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

	// ���� �ɼ� ����
	public void getMenuOption() {
		Connection con = menuMain.main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select menu_list.menu_list_id,menu_list.menu_option_id,menu_option.menu_option_name from menu_list,menu_option where menu_list.menu_option_id = menu_option.menu_option_id and menu_list_id = "
					+ menu_list_id;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ch_option.add(rs.getString("menu_option_name"));
				option_list.add(rs.getInt("menu_option_id"));
			}
			for (int i = 0; i < option_list.size(); i++) {
				if (ch_option.getItem(i).equals(menuMain.name)) {
					System.out.println(i + "��°���� ��ġ");
					ch_option.select(i);
				}
			}
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

	public void upload() {

		// data ������ �����ϱ�!!!
		FileInputStream fis = null;
		FileOutputStream fos = null;
//         imgName = System.currentTimeMillis() + "." + StringUtil.getExt(regist_path);
		try {
			fis = new FileInputStream(regist_path);
			fos = new FileOutputStream("C:/data" + File.separator + img);
			System.out.println(userData + File.separator + img);

			byte[] b = new byte[1024];
			int data = -1;
			while (true) {
				data = fis.read(b); // �Է�
				if (data == -1)
					break;
				fos.write(b); // ���
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void getMenu() {
		Connection con = menuMain.main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append(
				"select l.menu_list_id,l.menu_list_img,t.menu_type_name as type_name,l.menu_list_name,l.menu_list_price,o.menu_option_name as option_name from menu_list l, menu_type t,menu_option o where l.menu_type_id = t.menu_type_id and l.menu_option_id = o.menu_option_id and l.menu_list_id ="
						+ menu_list_id);

		// System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			rs.next();
			icon = new ImageIcon(menuMain.model.path + rs.getString("menu_list_img"));
			icon = new ImageIcon(icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
			regist_img = icon.getImage();
			id = rs.getInt("menu_list_id");
			price = rs.getString("menu_list_price");
			name = rs.getString("menu_list_name");
			type = rs.getString("type_name");
			option = rs.getString("option_name");
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