package com.hunter.user.join;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.hunter.user.User;
import com.hunter.user.UserMain;
import com.hunter.util.StringUtil;
import javax.swing.SwingConstants;

public class JoinMain extends JFrame {
	UserMain userMain;
	User user;

	JPanel p_north, p_firstRow, p_center;
	JLabel la_title, la_phone, la_password, la_password2;
	JTextField t_phone;
	JPasswordField t_password;
	JPasswordField t_password2;

	JButton bt_check;
	JButton bt_join;
	JButton bt_cancel;

	String phone;
	int memberId;
	
	boolean check = false;
	private JPanel panel;
	private JPanel panel_1;

	public JoinMain(UserMain userMain, User user) {
		this.userMain = userMain;
		this.user = user;

		// 메모리에 올리기
		p_north = new JPanel();
		p_firstRow = new JPanel();
		p_firstRow.setBounds(7, 5, 470, 60);
		p_center = new JPanel();
		la_title = new JLabel("JOIN");
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		la_phone = new JLabel("전화번호");
		la_phone.setHorizontalAlignment(SwingConstants.CENTER);
		t_phone = new JTextField();
		t_phone.setHorizontalAlignment(SwingConstants.CENTER);
		bt_check = new JButton("중복확인");

		// 사이즈 조정하기
		la_title.setPreferredSize(new Dimension(150, 60));
		Dimension d = new Dimension(300, 50);
		la_phone.setPreferredSize(new Dimension(150, 50));
		t_phone.setPreferredSize(new Dimension(200, 50));

		bt_check.setPreferredSize(new Dimension(100, 50));

		la_title.setFont(new Font("MD개성체", Font.BOLD, 40));
		la_phone.setFont(new Font("MD개성체", Font.BOLD, 20));
		t_phone.setFont(new Font("돋움", Font.BOLD, 20));

		bt_check.setFont(new Font("MD개성체", Font.BOLD, 15));
		// 부착하기
		p_north.add(la_title);
		p_center.setLayout(null);
		p_firstRow.add(la_phone);
		p_firstRow.add(t_phone);
		p_firstRow.add(bt_check);
		p_center.add(p_firstRow);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p_north, BorderLayout.NORTH);
		getContentPane().add(p_center);

		panel = new JPanel();
		panel.setBounds(9, 70, 465, 60);
		p_center.add(panel);
		la_password = new JLabel("비밀번호");
		la_password.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(la_password);
		la_password.setPreferredSize(new Dimension(150, 50));
		la_password.setFont(new Font("MD개성체", Font.BOLD, 20));
		t_password = new JPasswordField();
		t_password.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(t_password);
		t_password.setPreferredSize(d);
		t_password.setFont(new Font("돋움", Font.BOLD, 20));

		panel_1 = new JPanel();
		panel_1.setBounds(9, 135, 465, 60);
		p_center.add(panel_1);
		la_password2 = new JLabel("비밀번호 확인");
		la_password2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(la_password2);
		la_password2.setPreferredSize(new Dimension(150, 50));
		la_password2.setFont(new Font("MD개성체", Font.BOLD, 20));
		t_password2 = new JPasswordField();
		t_password2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(t_password2);
		t_password2.setPreferredSize(d);
		t_password2.setFont(new Font("돋움", Font.BOLD, 20));
		bt_join = new JButton("가입");
		bt_join.setBounds(130, 220, 100, 30);
		p_center.add(bt_join);
		bt_join.setPreferredSize(new Dimension(100, 30));
		bt_join.setFont(new Font("MD개성체", Font.BOLD, 20));
		bt_cancel = new JButton("취소");
		bt_cancel.setBounds(260, 220, 100, 30);
		p_center.add(bt_cancel);
		bt_cancel.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setFont(new Font("MD개성체", Font.BOLD, 20));
		bt_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeJoin();
				t_phone.setText("");
				t_password.setText("");
				t_password2.setText("");
			}
		});
		bt_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				join();
				t_phone.setText("");
				t_password.setText("");
				t_password2.setText("");
			}
		});

		// 버튼과 리스너 연결
		bt_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idCheck();
			}
		});

		setSize(500, 400);
		setLocationRelativeTo(null);
		setVisible(true);// 창띄우기
	}

	public void closeJoin() {
		setVisible(false);
	}

	// 아이디의 중복을 체크하는 메서드
	public void idCheck() {
		phone = t_phone.getText();
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member_list where member_list_phone=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				check = false; // 중복 전화번호가 있는 경우
				JOptionPane.showMessageDialog(this, "중복된 아이디 입니다.");
			} else {
				check = true; // 가입가능 전화번호
				JOptionPane.showMessageDialog(this, "가입 가능한 번호 입니다.");
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

	// 가입을 담당하는 메서드
	public void join() {
		// 초기화

		// 사용자가 입력한 값 받아오기
		String id = StringUtil.getId(phone);
		String pw = new String(t_password.getPassword());
		String pw2 = new String(t_password2.getPassword());

		// 중복체크에 따라 반응하기
		if (check) {
			if (pw.equals(pw2)) {
				JOptionPane.showMessageDialog(this, id + "님 가입이 완료 되었습니다.");
				save(phone, pw);
/////////////////////////////수정//////////////////////////////////////////
				getMemberId();
				addUserList();
//////////////////////////////////////////////////////////////////////////
				closeJoin();

			} else {
				JOptionPane.showMessageDialog(this, "비밀번호가 불일치 합니다.");
			}
		} else {
			JOptionPane.showMessageDialog(this, "중복 확인을 진행해 주세요.");
			t_password.setText("");
			t_password2.setText("");
		}
	}

	// 회원가입한 회원의 정보를 db에 저장하는 메서드
	public void save(String phone, String pw) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append(
				"insert into member_list(member_list_id ,user_type_id, member_list_phone ,member_list_pw,member_list_date)");
		sb.append(" values(seq_member_list.nextval,?,?,?,?)");

		int type = user.getMemeber().getType();
		java.util.Date utilDate = new java.util.Date();
		Date date = new Date(utilDate.getTime());

		user.getMemeber().setDate(date);
		System.out.println("회원가입한 날짜는: " + user.getMemeber().getDate());

		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, type);// user_type_id
			pstmt.setString(2, phone);// member_list_phone
			pstmt.setString(3, pw);// member_list_pw
			pstmt.setDate(4, date);// member_list_date

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
/////////////////////////////수정//////////////////////////////////////////
	//새로 등록한 회원의 member_list_id를 반환 하는 메서드
	public void getMemberId() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select member_list_id from member_list where member_list_phone=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				memberId=rs.getInt("member_list_id");
				System.out.println("user_list에 새로 등록한 번호는"+phone+", pk:"+memberId);
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
/////////////////////////////수정//////////////////////////////////////////
	//회원이 등록하고 나서, user_list에 추가하는 메서드
	public void addUserList() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into user_list(user_list_id ,user_type_id, user_id)");
		sb.append(" values(seq_user_list.nextval,?,?)");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, 2);// user_type_id
			pstmt.setInt(2, memberId);// user_id에 넣을 member_list_id
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
//////////////////////////////////////////////////////////////////////////
}
