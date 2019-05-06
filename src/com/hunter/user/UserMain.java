package com.hunter.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hunter.db.ConnectionManager;
import com.hunter.user.home.HomeMain;

import com.hunter.user.join.JoinMain;
import com.hunter.user.login.LoginMain;
import com.hunter.user.regist.RegistMain;

public class UserMain extends JFrame{
	User user;
	

	HomeMain homeMain;
	LoginMain loginMain;
	JoinMain joinMain;
	RegistMain registMain;
	
	String[] mainTitle = { "homeMain", "loginMain", "joinMain","registMain" };
	JPanel[] pages = new JPanel[mainTitle.length];
	
	
	JPanel container; 
	
	JPanel p_guest, p_login, p_join;
	JLabel la_guest, la_login, la_join;
	
	private Connection con;
	ConnectionManager connectionManager;
	
	public UserMain() {
		connectionManager = new ConnectionManager();
		con = connectionManager.connect();
		user=new User();
		// �޸𸮿� �ø���
		
		container = new JPanel();
		p_guest = new JPanel();
		p_guest.setBounds(162, 300, 350, 350);
		p_login = new JPanel();
		p_login.setBounds(517, 300, 350, 350);
		p_join = new JPanel();
		p_join.setBounds(872, 300, 350, 350);
		la_guest = new JLabel("GUEST");
		la_login = new JLabel("LOGIN");
		la_join = new JLabel("JOIN");
		
		container.setPreferredSize(new Dimension(1300, 400));
		//this.setBorder(BorderFactory.createEmptyBorder(300, 0, 0, 0));
		Dimension d = new Dimension(350, 350);
		p_guest.setPreferredSize(d);
		p_login.setPreferredSize(d);
		p_join.setPreferredSize(d);
		// ���� ������ ����
		Dimension f = new Dimension(200, 350);
		la_guest.setPreferredSize(f);
		la_guest.setHorizontalAlignment(SwingConstants.CENTER);
		la_login.setPreferredSize(f);
		la_login.setHorizontalAlignment(SwingConstants.CENTER);
		la_join.setPreferredSize(f);
		la_join.setHorizontalAlignment(SwingConstants.CENTER);

		// ���� ����
		la_guest.setFont(new Font("Verdana", Font.BOLD, 50));
		la_guest.setForeground(Color.WHITE);
		la_login.setFont(new Font("Verdana", Font.BOLD, 50));
		la_login.setForeground(Color.WHITE);
		la_join.setFont(new Font("Verdana", Font.BOLD, 50));
		la_join.setForeground(Color.WHITE);

		// �г� ����
		container.setBackground(Color.black);
		p_guest.setBackground(Color.blue);
		p_login.setBackground(Color.GREEN);
		p_join.setBackground(Color.CYAN);

		// ���ں�>�г�>�����̳� �г� ����

		p_guest.add(la_guest);
		p_login.add(la_login);
		p_join.add(la_join);
		container.setLayout(null);

		container.add(p_guest);
		container.add(p_login);
		container.add(p_join);
		
		getContentPane().add(container);
		
		
		setBackground(Color.black);

		// ������ ������ �����ϱ�!!
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				connectionManager.closeDB(con);
				System.exit(0);
			}
		});
		
		// p_guest ������ ����
				p_guest.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						user.getGuest().addGuest();
						openRegist();		
					}

					public void mouseEntered(MouseEvent e) {
						p_guest.setBackground(Color.DARK_GRAY);
					}

					public void mouseExited(MouseEvent e) {
						p_guest.setBackground(Color.BLUE);
					}
				});
				// p_login�� ������ ����
				p_login.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						user.getMemeber().addMember();
						openLogin();
					}

					public void mouseEntered(MouseEvent e) {
						p_login.setBackground(Color.DARK_GRAY);
					}

					public void mouseExited(MouseEvent e) {
						p_login.setBackground(Color.GREEN);
					}
				});
				// p_join�� ������ ����
				p_join.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						openJoin();
					}

					public void mouseEntered(MouseEvent e) {
						p_join.setBackground(Color.DARK_GRAY);
					}

					public void mouseExited(MouseEvent e) {
						p_join.setBackground(Color.CYAN);
					}
				});
		
		setSize(1400, 1000);
		setLocationRelativeTo(null);
		setVisible(true);// â����
	}
	
	public Connection getCon() {
		return con;
	}
	
	public void openRegist() {
		registMain=new RegistMain(this,user);
	}
	public void openLogin() {
		loginMain=new LoginMain(this,user);
	}
	public void openJoin() {
		joinMain=new JoinMain(this,user);
	}
	public static void main(String[] args) {
		new UserMain();
	}
	
}
