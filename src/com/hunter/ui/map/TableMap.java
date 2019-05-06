package com.hunter.ui.map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.hunter.user.User;
import com.hunter.user.UserMain;
import com.hunter.user.chat.ChatMain;
import com.hunter.user.chat.TableOption;

public class TableMap extends JPanel{
	String TAG = this.getClass().getName();
	JPanel p_north;
	JLabel label;
	JPanel p_west;
	ArrayList<JPanel> container = new ArrayList<>(30);
	JPanel p_center;
	JPanel p_east;
	//페이지 얻기
	ChatMain chatMain;
	UserMain userMain;
	User user;
	TableOption tableOption;

	
	ArrayList<Integer> arrTableNo = new ArrayList<Integer>();
	int tableNO=0;
	String selectedIP;//찍힌 테이블 ip주소가 뭐냐
	
	String myIp;
	int myTableNo=0;
	String myTableIp;
	
	public TableMap(ChatMain chatMain,UserMain userMain,User user) {
		System.out.println(TAG+" tableMap이 메모리에 올라갔다.");
		myTableNo=user.getTableNum();
		myTableIp=user.getTableIp();
		System.out.println("나의 테이블 넘버는: "+myTableNo);
		System.out.println("나의 테이블 ip는: "+myTableIp);
		System.out.println("나의 성별은: "+user.getGender());
		System.out.println("나의 총 인원수는: "+user.getTotal());
		
		this.chatMain = chatMain;
		this.userMain = userMain;
		this.user = user;
		
		
		setLayout(new BorderLayout(0,0));
		p_north = new JPanel();
		label = new JLabel("출입구");
		p_west = new JPanel();
		p_center = new JPanel();
		p_east = new JPanel();
		
		p_north.setPreferredSize(new Dimension(700, 35));
		p_north.setBackground(Color.BLACK);
		
		label.setPreferredSize(new Dimension(80, 35));
		label.setBackground(Color.ORANGE);
		label.setOpaque(true);
		label.setForeground(new Color(34, 61, 75));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		p_west.setPreferredSize(new Dimension(300, 415));
		p_west.setBackground(Color.pink);
		p_west.setLayout(new FlowLayout(WIDTH, 0, 0));
		
		p_center.setSize(new Dimension(100, 415));
		p_center.setBackground(Color.BLACK);
		
		p_east.setPreferredSize(new Dimension(300, 415));
		p_east.setBackground(Color.orange);
		p_east.setLayout(new FlowLayout(WIDTH,0,0));
		
		Dimension d = new Dimension(100, 80);
		
		for(int i=0;i<30;i++) {
			tableNO++;
			container.add(new DefaultTable(this));
			container.get(i).setPreferredSize(d);
			container.get(i).setLayout(new FlowLayout(WIDTH, 0, 0));
			if(i<15) {
				p_west.add(container.get(i));
			}else {
				p_east.add(container.get(i));
			}
			container.get(i).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int selectedIndex = container.indexOf(e.getSource());
					System.out.println(TAG+" "+(selectedIndex+1)+"번테이블 선택");
					getTableNo();
					String ip = getTableIp(selectedIndex);
					System.out.println(selectedIP);
					showOption(ip);
				}
			});
		}
		
		//attach semi-parents container
		p_north.add(label);
		add(p_north,BorderLayout.NORTH);		
		add(p_west, BorderLayout.WEST);
		add(p_center, BorderLayout.CENTER);
		add(p_east, BorderLayout.EAST);
		
		/////////////////////////////////////////////////////////	     
		
		//setSize(new Dimension(700, 435));
		setPreferredSize(new Dimension(700, 435));
		//setResizable(false);
		setBackground(Color.BLACK);
		setVisible(true);
	}//end of constructor
	
	//tableNo
	public void getTableNo() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select table_no from table_info";
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();
			rs.beforeFirst();
			for(int i=0;i<total;i++) {
				rs.next();
				arrTableNo.add(rs.getInt("table_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//선택 테이블의 ip주소를 반환할 메서드
	public String getTableIp(int clicked) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select table_ip from table_info where table_no=?";
		//System.out.println(arrTableNo.isEmpty());
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, arrTableNo.get(clicked));
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				selectedIP = rs.getString("table_ip");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return selectedIP;
	}
	
	public void showOption(String tableIp) {
		System.out.println("방금 누른 테이블의 ip주소는 "+tableIp);
		//TableOption.java를 불러옴
		tableOption = new TableOption(this.chatMain,this,tableIp);
	}
	
}
