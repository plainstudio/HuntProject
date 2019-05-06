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

	int gender_type_id;// 선택한 성별 타입의 인덱스
	int total = 0;// 작성한 총 인원수

	// 정보
	///////////////////////////////수정//////////////////////////////////////////
	int table_info_id; // 테이블 pk
	////////////////////////////////////////////////////////////////////////////
	String tableIp; // 테이블 아이피
	int tableIndex; // 테이블 번호

	///////////////////////////////수정//////////////////////////////////////////
	int user_list_id; // user_list의 pk값 (게스트거나, 멤버)
	String guestName;// 게스트의 고유 이름(자동 부여)
	String memberName;// 멤버의 고유이름(전화번호)
	int guest_list_id;// guest_list의 pk값
	int member_list_id;// member_list의 pk값
	////////////////////////////////////////////////////////////////////////////

	public RegistMain(UserMain userMain, User user) {
		this.userMain = userMain;
		this.user = user;

		// 메모리에 올리기
		p_north = new JPanel();
		p_center = new JPanel();
		la_title = new JLabel("REGIST");
		la_title.setHorizontalAlignment(SwingConstants.CENTER);
		la_genger = new JLabel("성 별");
		la_genger.setBounds(39, 5, 200, 50);
		la_genger.setHorizontalAlignment(SwingConstants.CENTER);
		la_num = new JLabel("인원 수");
		la_num.setBounds(39, 60, 200, 50);
		la_num.setHorizontalAlignment(SwingConstants.CENTER);
		ch = new Choice();
		ch.setBounds(244, 15, 200, 30);
		t_num = new JTextField();
		t_num.setHorizontalAlignment(SwingConstants.CENTER);
		t_num.setBounds(244, 60, 200, 50);

		// 사이즈 조정하기
		Dimension d = new Dimension(200, 50);
		la_title.setPreferredSize(new Dimension(200, 50));
		la_genger.setPreferredSize(d);
		la_num.setPreferredSize(d);
		ch.setPreferredSize(d);
		t_num.setPreferredSize(d);

		la_title.setFont(new Font("MD개성체", Font.BOLD, 40));
		la_genger.setFont(new Font("MD개성체", Font.BOLD, 20));
		ch.setFont(new Font("돋움", Font.BOLD, 20));
		la_num.setFont(new Font("MD개성체", Font.BOLD, 20));

		// 부착하기
		ch.addItem("====성별====");
		ch.addItem("남성");
		ch.addItem("여성");
		ch.addItem("혼성");
		p_north.add(la_title);
		p_center.setLayout(null);
		p_center.add(la_genger);
		p_center.add(ch);
		p_center.add(la_num);
		p_center.add(t_num);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p_north, BorderLayout.NORTH);
		getContentPane().add(p_center);
		bt_enter = new JButton("입장");
		bt_enter.setBounds(130, 140, 100, 30);
		p_center.add(bt_enter);

		bt_enter.setPreferredSize(new Dimension(100, 30));
		bt_enter.setFont(new Font("돋움", Font.BOLD, 20));
		bt_cancel = new JButton("취소");
		bt_cancel.setBounds(260, 140, 100, 30);
		p_center.add(bt_cancel);
		bt_cancel.setPreferredSize(new Dimension(100, 30));
		bt_cancel.setFont(new Font("돋움", Font.BOLD, 20));

		ch.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				gender_type_id = ch.getSelectedIndex();
				System.out.println("선택한 초이스의 인덱스 번호는: " + gender_type_id);
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
		setVisible(true);// 창띄우기
	}

	public void closeRegist() {
		setVisible(false);
	}

	public void enter() {
		// db에 로그인한 정보(테이블번호, 유저아이디,성별,총인원수,입장일) 넘기기
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		// 입장정보 등록, 화면전환
		int result = JOptionPane.showConfirmDialog(this, "입장하시겠습니까?");
		if (result == JOptionPane.OK_OPTION) {

			user.setGender(gender_type_id);
			System.out.println("등록창에서 등록할 성별의 인덱스는: " + gender_type_id);

			total = (Integer.parseInt(t_num.getText()));// 총 인원수 저장
			user.setTotal(total);
			System.out.println("등록창에서 등록할 총 인원수는 : " + total);

			setVisible(false);
			userMain.setVisible(false);

			// 테이블 정보 저장
			setMyTable();
			save();

			// 홈 화면 생성
			homeMain = new HomeMain(userMain, user);
		}
	}

	// table_info 테이블을 조회해서 테이블 수랑, 고유 ip를 취득
	public void setMyTable() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// db연결후 테이블 정보 담아오기
		String sql = "select table_info_id, table_ip,table_no from table_info where table_ip=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, getMyLocalIp());
			System.out.println("내 아이피는 :" + getMyLocalIp());
			rs = pstmt.executeQuery();
			if (rs.next()) {
///////////////////////////////수정//////////////////////////////////////////
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
	// db에 저장된 고유ip와 비교하기 위해 localhost로부터 ip구하기
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
	// db에 entry_list에 저장(회원이든, 비회원이든 등록하는 이곳에서 db에 entry_list에 추가시킨다.)
	public void save() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		sb.append(
				"insert into entry_list(entry_list_id, table_info_id, user_list_id, gender_type_id, entry_list_total, entry_list_date)");
		sb.append(" values(seq_entry_list.nextval,?,?,?,?,?)");

		///////////////////////////////수정//////////////////////////////////////////
		if (user.getFlag()) { // 멤버유저pk 구하기
			memberName = user.getMemeber().getPhone();
			getMemberId();
			getMemberUserId(memberName);
			System.out.println("entry_list에 넣을 멤버: " + user_list_id);

		} else {
			registGuest(); // 게스트 리스트에 시퀀스,타입,이름 등록
			getGuestId(); // 게스트 리스트의 guest_list_id 얻기
			addUserList(); // user_list에 저장
			getGuestUserId(guestName); // entry_list에 넣을 user_list_id 구함
			System.out.println("entry_list에 넣을 게스트 : " + user_list_id);
		}
		///////////////////////////////수정//////////////////////////////////////////
		
		// entry_list에 추가하는 부분
		java.util.Date utilDate = new java.util.Date(); // 유저 입장일 구하기
		Date date = new Date(utilDate.getTime());
		user.setDate(date);
		System.out.println("입장한 날짜는: " + user.getDate());//<<<<<<<이거 사용안하신다 한거죠? 이건 안건드렸어요

		try {
			pstmt = con.prepareStatement(sb.toString());
			///////////////////////////////수정//////////////////////////////////////////
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

///////////////////////////////수정//////////////////////////////////////////
	// user들의(유저)의 user_list_id를 얻는 메서드
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
///////////////////////////////수정//////////////////////////////////////////
	// user들의(게스트)의 user_list_id를 얻는 메서드
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
///////////////////////////////수정//////////////////////////////////////////
	// 게스트 로그인시 테이블에 등록
	public void registGuest() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		guestName = "guest" + System.currentTimeMillis();  //<<db에 guest_list_name이 추가됬는데, 여기에 자동으로 고유의 게스트이름을 부여시켜줄때 사용

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
///////////////////////////////수정//////////////////////////////////////////
	// 새로 등록한 게스트의 guest_list_id를 반환 하는 메서드
	public void getGuestId() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select guest_list_id from guest_list where guest_list_name=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guestName); // 새로 등록한 게스트의 이름으로 pk값 가져오기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				guest_list_id = rs.getInt("guest_list_id");
				System.out.println("user_list에 새로 등록한 게스트는" + guestName + ", pk:" + guest_list_id);
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
///////////////////////////////수정//////////////////////////////////////////
	// 새로 등록한 멤버의 member_list_id를 반환 하는 메서드
	public void getMemberId() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select * from member_list where member_list_phone=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberName); // 새로 등록한 게스트의 이름으로 pk값 가져오기
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member_list_id = rs.getInt("member_list_id");
				System.out.println("user_list에 새로 등록한 멤버의이름" + memberName + ", pk:" + member_list_id);
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
///////////////////////////////수정//////////////////////////////////////////
	// 게스트가 등록되고 나서, user_list에 추가하는 메서드
	public void addUserList() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;

		StringBuffer sb = new StringBuffer();
		sb.append("insert into user_list(user_list_id ,user_type_id, user_id)");
		sb.append(" values(seq_user_list.nextval,?,?)");
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, 1);// user_type_id
			pstmt.setInt(2, guest_list_id);// user_id에 넣을 guest_list_id
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