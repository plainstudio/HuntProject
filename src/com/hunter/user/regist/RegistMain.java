package com.hunter.user.regist;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hunter.user.User;
import com.hunter.user.UserMain;
import com.hunter.user.home.HomeMain;

public class RegistMain extends JFrame {
	String TAG = this.getClass().getName();
	UserMain userMain;
	User user;
	HomeMain homeMain;

	JPanel p_north, p_center;
	JLabel la_title, la_genger, la_num;
	Choice ch;
	JTextField t_num;
	JButton bt_enter;
	JButton bt_cancel;

	int gender_type_id;// ������ ���� Ÿ���� �ε���
	int total = 0;// �ۼ��� �� �ο���

	// ����
	///////////////////////////////����//////////////////////////////////////////
	int table_info_id; // ���̺� pk
	////////////////////////////////////////////////////////////////////////////
	String tableIp; // ���̺� ������
	int tableIndex; // ���̺� ��ȣ

	///////////////////////////////����//////////////////////////////////////////
	int user_list_id; // user_list�� pk�� (�Խ�Ʈ�ų�, ���)
	String guestName;// �Խ�Ʈ�� ���� �̸�(�ڵ� �ο�)
	String memberName;// ����� �����̸�(��ȭ��ȣ)
	int guest_list_id;// guest_list�� pk��
	int member_list_id;// member_list�� pk��
	////////////////////////////////////////////////////////////////////////////

	public RegistMain(UserMain userMain, User user) {
		this.userMain = userMain;
		this.user = user;

		// �޸𸮿� �ø���
		p_north = new JPanel();
		p_center = new JPanel();
		la_title = new JLabel("REGIST");
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		la_genger = new JLabel("�� ��");
		la_genger.setBounds(39, 5, 200, 50);
		la_genger.setHorizontalAlignment(SwingConstants.CENTER);
		la_num = new JLabel("�ο� ��");
		la_num.setBounds(39, 60, 200, 50);
		la_num.setHorizontalAlignment(SwingConstants.CENTER);
		ch = new Choice();
		ch.setBounds(244, 15, 200, 30);
		t_num = new JTextField();
		t_num.setHorizontalAlignment(SwingConstants.CENTER);
		t_num.setBounds(244, 60, 200, 50);

		// ������ �����ϱ�
		Dimension d = new Dimension(200, 50);
		la_title.setPreferredSize(new Dimension(200, 50));
		la_genger.setPreferredSize(d);
		la_num.setPreferredSize(d);
		ch.setPreferredSize(d);
		t_num.setPreferredSize(d);

		la_title.setFont(new Font("MD����ü", Font.BOLD, 40));
		la_genger.setFont(new Font("MD����ü", Font.BOLD, 20));
		ch.setFont(new Font("����", Font.BOLD, 20));
		la_num.setFont(new Font("MD����ü", Font.BOLD, 20));

		// �����ϱ�
		ch.addItem("====����====");
		ch.addItem("����");
		ch.addItem("����");
		ch.addItem("ȥ��");
		p_north.add(la_title);
		p_center.setLayout(null);
		p_center.add(la_genger);
		p_center.add(ch);
		p_center.add(la_num);
		p_center.add(t_num);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p_north, BorderLayout.NORTH);
		getContentPane().add(p_center);
		bt_enter = new JButton("����");
		bt_enter.setBounds(130, 140, 100, 30);
		p_center.add(bt_enter);

		bt_enter.setPreferredSize(new Dimension(100, 30));
		bt_enter.setFont(new Font("����", Font.BOLD, 20));
		bt_cancel = new JButton("���");
		bt_cancel.setBounds(260, 140, 100, 30);
		p_center.add(bt_cancel);
		bt_cancel.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setFont(new Font("����", Font.BOLD, 20));

		ch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				gender_type_id = ch.getSelectedIndex();
				System.out.println("������ ���̽��� �ε��� ��ȣ��: " + gender_type_id);
			}
		});
		bt_enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enter();
			}
		});
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeRegist();
			}
		});

		setSize(500, 300);
		setLocationRelativeTo(null);
		setVisible(true);// â����
	}

	public void closeRegist() {
		setVisible(false);
	}

	public void enter() {
		// db�� �α����� ����(���̺��ȣ, �������̵�,����,���ο���,������) �ѱ��
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		// �������� ���, ȭ����ȯ
		int result = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?");
		if (result == JOptionPane.OK_OPTION) {

			user.setGender(gender_type_id);
			System.out.println("���â���� ����� ������ �ε�����: " + gender_type_id);

			total = (Integer.parseInt(t_num.getText()));// �� �ο��� ����
			user.setTotal(total);
			System.out.println("���â���� ����� �� �ο����� : " + total);

			setVisible(false);
			userMain.setVisible(false);

			// ���̺� ���� ����
			setMyTable();
			save();

			// Ȩ ȭ�� ����
			homeMain = new HomeMain(userMain, user);
		}
	}

	// table_info ���̺��� ��ȸ�ؼ� ���̺� ����, ���� ip�� ���
	public void setMyTable() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// db������ ���̺� ���� ��ƿ���
		String sql = "select table_info_id, table_ip,table_no from table_info where table_ip=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, getMyLocalIp());
			System.out.println("�� �����Ǵ� :" + getMyLocalIp());
			rs = pstmt.executeQuery();
			if (rs.next()) {
///////////////////////////////����//////////////////////////////////////////
				table_info_id = rs.getInt("table_info_id");
////////////////////////////////////////////////////////////////////////////
				tableIp = rs.getString("table_ip");
				tableIndex = rs.getInt("table_no");

				user.setTableNum(tableIndex);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// db�� ����� ����ip�� ���ϱ� ���� localhost�κ��� ip���ϱ�
		public String getMyLocalIp() {
			String localIp = null;
			try {
				InetAddress inet = InetAddress.getLocalHost();
				localIp = inet.getHostAddress();
				user.setTableIp(localIp);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			return localIp;
		}
	// db�� entry_list�� ����(ȸ���̵�, ��ȸ���̵� ����ϴ� �̰����� db�� entry_list�� �߰���Ų��.)
	public void save() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		sb.append(
				"insert into entry_list(entry_list_id, table_info_id, user_list_id, gender_type_id, entry_list_total, entry_list_date)");
		sb.append(" values(seq_entry_list.nextval,?,?,?,?,?)");

		///////////////////////////////����//////////////////////////////////////////
		if (user.getFlag()) { // �������pk ���ϱ�
			memberName = user.getMemeber().getPhone();
			getMemberId();
			getMemberUserId(memberName);
			System.out.println("entry_list�� ���� ���: " + user_list_id);

		} else {
			registGuest(); // �Խ�Ʈ ����Ʈ�� ������,Ÿ��,�̸� ���
			getGuestId(); // �Խ�Ʈ ����Ʈ�� guest_list_id ���
			addUserList(); // user_list�� ����
			getGuestUserId(guestName); // entry_list�� ���� user_list_id ����
			System.out.println("entry_list�� ���� �Խ�Ʈ : " + user_list_id);
		}
		///////////////////////////////����//////////////////////////////////////////
		
		// entry_list�� �߰��ϴ� �κ�
		java.util.Date utilDate = new java.util.Date(); // ���� ������ ���ϱ�
		Date date = new Date(utilDate.getTime());
		user.setDate(date);
		System.out.println("������ ��¥��: " + user.getDate());//<<<<<<<�̰� �����ϽŴ� �Ѱ���? �̰� �Ȱǵ�Ⱦ��

		try {
			pstmt = con.prepareStatement(sb.toString());
			///////////////////////////////����//////////////////////////////////////////
			pstmt.setInt(1, table_info_id); // table_info_id
			pstmt.setInt(2, user_list_id);// user_list_id
			////////////////////////////////////////////////////////////////////////////
			pstmt.setInt(3, gender_type_id);// gender_type_id
			pstmt.setInt(4, total);// entry_list_total
			pstmt.setDate(5, date);// entry_list_date

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}

///////////////////////////////����//////////////////////////////////////////
	// user����(����)�� user_list_id�� ��� �޼���
	public void getMemberUserId(String memberName) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select user_list_id");
		sb.append(" from user_list u, member_list m");
		sb.append(" where member_list_phone=?");
		sb.append(" and u.user_type_id=m.user_type_id and user_id=member_list_id");

		System.out.println(sb.toString());
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, memberName);
			rs = pstmt.executeQuery();
			
			boolean result=rs.next();
			System.out.println(result);
			
			if (result) {
				user_list_id = rs.getInt("user_list_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
///////////////////////////////����//////////////////////////////////////////
	// user����(�Խ�Ʈ)�� user_list_id�� ��� �޼���
	public void getGuestUserId(String guestName) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();
		sb.append("select user_list_id");
		sb.append(" from user_list u, guest_list g");
		sb.append(" where guest_list_name=?");
		sb.append(" and u.user_type_id=g.user_type_id and user_id=guest_list_id");

		System.out.println(sb.toString());

		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, guestName);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println(rs.getInt("user_list_id"));
				user_list_id = rs.getInt("user_list_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
///////////////////////////////����//////////////////////////////////////////
	// �Խ�Ʈ �α��ν� ���̺� ���
	public void registGuest() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		guestName = "guest" + System.currentTimeMillis();  //<<db�� guest_list_name�� �߰���µ�, ���⿡ �ڵ����� ������ �Խ�Ʈ�̸��� �ο������ٶ� ���

		StringBuffer sb = new StringBuffer();
		sb.append("insert into guest_list(guest_list_id, user_type_id, guest_list_name)");
		sb.append(" values(seq_guest_list.nextval,1,?)");

		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, guestName);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
///////////////////////////////����//////////////////////////////////////////
	// ���� ����� �Խ�Ʈ�� guest_list_id�� ��ȯ �ϴ� �޼���
	public void getGuestId() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select guest_list_id from guest_list where guest_list_name=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guestName); // ���� ����� �Խ�Ʈ�� �̸����� pk�� ��������
			rs = pstmt.executeQuery();

			if (rs.next()) {
				guest_list_id = rs.getInt("guest_list_id");
				System.out.println("user_list�� ���� ����� �Խ�Ʈ��" + guestName + ", pk:" + guest_list_id);
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
///////////////////////////////����//////////////////////////////////////////
	// ���� ����� ����� member_list_id�� ��ȯ �ϴ� �޼���
	public void getMemberId() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member_list where member_list_phone=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberName); // ���� ����� �Խ�Ʈ�� �̸����� pk�� ��������
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member_list_id = rs.getInt("member_list_id");
				System.out.println("user_list�� ���� ����� ������̸�" + memberName + ", pk:" + member_list_id);
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
///////////////////////////////����//////////////////////////////////////////
	// �Խ�Ʈ�� ��ϵǰ� ����, user_list�� �߰��ϴ� �޼���
	public void addUserList() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into user_list(user_list_id ,user_type_id, user_id)");
		sb.append(" values(seq_user_list.nextval,?,?)");
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, 1);// user_type_id
			pstmt.setInt(2, guest_list_id);// user_id�� ���� guest_list_id
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
/////////////////////////////////////////////////////////////////////////////
}