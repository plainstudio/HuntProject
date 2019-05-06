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

public class TableMap_expire190208 extends JPanel{
	JPanel p_north;
	JLabel label;
	JPanel p_west;
	ArrayList<JPanel> container = new ArrayList<>(30);
	JPanel p_center;
	JPanel p_east;
	ChatMain chatMain;
	UserMain userMain;
	User user;
	TableOption tableOption;
	ArrayList<String> tablePK = new ArrayList<>(); 
	int tableNo=0;//테이블 번호 찍기 위한 변수
	int selectedIndex = 0;//몇 번 테이블을 클릭했는지 인덱스 담기 위한 변수
	String selectedIP;//찍힌 테이블 ip주소가 뭐냐
	
	public TableMap_expire190208(ChatMain chatMain,UserMain userMain,User user) {
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
			tableNo=i;
			//container.add(new DefaultTable(this,this.user));
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
					//selectedIndex = container.indexOf(e.getSource());
					System.out.println("테이블 선택혔냐");
					selectedIndex = container.indexOf(e.getSource());
					System.out.println(selectedIndex+1+"번테이블");
					getTablePK();
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
	
	//뒤죽박죽 된 table_info테이블의 pk 얻어 배열에 정갈하게 담는 메서드
	public void getTablePK() {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select table_info_id from table_info";
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();

			rs.last();
			int total = rs.getRow();
			System.out.println("테이블 행의 수: "+total);
			rs.beforeFirst();
			for(int i=0;i<total;i++) {
				rs.next();
				String seq_table_info = rs.getString("table_info_id");
				tablePK.add(seq_table_info);
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
	public String getTableIp(int selected) {
		Connection con = userMain.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select table_ip from table_info where table_info_id=?";
		try {
			pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pstmt.setString(1, tablePK.get(selected));
			rs = pstmt.executeQuery();
			
			rs.last();
			int total = rs.getRow();
			rs.beforeFirst();
			for(int i=0;i<total;i++) {
				rs.next();
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
		
		/*
		String tableIp="";
		try {
			tableIp = Inet4Address.getLocalHost().getHostAddress();
				//실험적으로 찍어보는 tableIp를 구하는 메서드를 변수에 담음
				//얘를 db에 저장된 table_info_id에 매칭되는 table_ip컬럼의 레코드값으로 대체해야함
			System.out.println(tableIp);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return tableIp;
		 */
		return selectedIP;
	}
	
	public void showOption(String tableIp) {
		System.out.println("방금 누른 테이블의 ip주소는 "+tableIp);
		//TableOption.java를 불러옴
		//tableOption = new TableOption(this.chatMain,this,tableIp);
	}
	
}
