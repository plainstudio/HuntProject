package com.hunter.user.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hunter.user.ShowAll;
import com.hunter.user.User;
import com.hunter.user.UserMain;
import com.hunter.user.chat.ChatMain;
import com.hunter.user.game.GameMain;
import com.hunter.user.order.OrderMain;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class HomeMain extends JFrame {
	// ��� Ŭ����
	UserMain userMain;
	User user;

	OrderMain orderMain;
	ChatMain chatMain;
	GameMain gameMain;
	BillMain billMain;

	// ȭ����ȯ
	String[] mainTitle = { "home", "orderMain", "chatMain", "gameMain", "billMain" };
	JPanel[] pages = new JPanel[mainTitle.length];

	// ������
	JPanel container; // ��ü�� ���δ� ����
	JPanel tab_area; // ��� ����
	JLabel la_table, la_user;// ��� ���� ����_���� ���� ����
	JButton bt_exit;

	JPanel tab_order;
	JLabel la_tab_order;
	JPanel tab_chat;
	JLabel la_tab_chat;
	JPanel tab_game;
	JLabel la_tab_game;
	JPanel tab_check;
	JLabel la_tab_check;
	JPanel tab_home;
	JLabel la_tab_home;
	JPanel tab_table;

	public HomeMain(UserMain userMain, User user) {
		setSize(new Dimension(1400, 1000));
		getContentPane().setBackground(Color.BLACK);
		this.userMain = userMain;
		this.user = user;

		setSize(1400, 1000);
		// �޸𸮿� �ø���
		container = new JPanel();

		// �ʱ�ȭ ȭ�� �޸𸮿� �ø���
		pages[0] = new ShowAll(userMain, user, this);
		pages[1] = new OrderMain(userMain, user);
		pages[2] = new ChatMain(userMain, user);
		pages[3] = new GameMain();
		pages[4] = new BillMain(userMain, user);

		// ������ �����ϱ�
		Dimension d = new Dimension(300, 800);
		container.setPreferredSize(new Dimension(1400, 900));
		container.setBackground(Color.BLACK);

		// ��Ʈ �����ϱ�
		Dimension df = new Dimension(300, 800);
		getContentPane().setLayout(new BorderLayout(0, 0));

		// page[]�迭 ����
		container.add(pages[0]);
		container.add(pages[1]);
		container.add(pages[2]);
		container.add(pages[3]);
		container.add(pages[4]);
		getContentPane().add(container, BorderLayout.CENTER);
		container.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// ��ܿ���
		tab_area = new JPanel();
		tab_area.setVisible(false);
		getContentPane().add(tab_area, BorderLayout.SOUTH);
		tab_area.setPreferredSize(new Dimension(1400, 100));
		tab_area.setBackground(Color.BLACK);
		tab_area.setLayout(new GridLayout(0, 7, 0, 0));

		tab_table = new JPanel();
		tab_table.setBackground(Color.BLACK);
		tab_area.add(tab_table);
		la_table = new JLabel();
		tab_table.add(la_table);

		la_table.setFont(new Font("�߰��", Font.BOLD, 50));
		la_table.setForeground(Color.black);
		la_user = new JLabel();
		tab_table.add(la_user);
		la_user.setFont(new Font("�߰��", Font.BOLD, 50));
		la_user.setForeground(Color.blue);

		tab_home = new JPanel();
		tab_home.setBackground(Color.ORANGE);
		tab_area.add(tab_home);

		la_tab_home = new JLabel("HOME");
		la_tab_home.setPreferredSize(new Dimension(250, 100));
		la_tab_home.setHorizontalAlignment(SwingConstants.CENTER);
		la_tab_home.setForeground(Color.WHITE);
		la_tab_home.setFont(new Font("Dialog", Font.BOLD, 40));
		la_tab_home.setBounds(new Rectangle(84, 0, 300, 100));
		la_tab_home.setAlignmentX(0.5f);
		tab_home.add(la_tab_home);

		tab_order = new JPanel();
		tab_order.setBackground(Color.BLUE);
		tab_area.add(tab_order);

		la_tab_order = new JLabel("ORDER");
		la_tab_order.setFont(new Font("Dialog", Font.BOLD, 40));
		la_tab_order.setForeground(Color.WHITE);
		la_tab_order.setHorizontalAlignment(SwingConstants.CENTER);
		la_tab_order.setPreferredSize(new Dimension(250, 100));
		la_tab_order.setAlignmentX(Component.CENTER_ALIGNMENT);
		la_tab_order.setBounds(new Rectangle(84, 0, 300, 100));
		tab_order.add(la_tab_order);

		tab_chat = new JPanel();
		tab_chat.setBackground(Color.GRAY);
		tab_area.add(tab_chat);

		la_tab_chat = new JLabel("CHAT");
		la_tab_chat.setPreferredSize(new Dimension(250, 100));
		la_tab_chat.setHorizontalAlignment(SwingConstants.CENTER);
		la_tab_chat.setForeground(Color.WHITE);
		la_tab_chat.setFont(new Font("Dialog", Font.BOLD, 40));
		la_tab_chat.setBounds(new Rectangle(84, 0, 300, 100));
		la_tab_chat.setAlignmentX(0.5f);
		tab_chat.add(la_tab_chat);

		tab_game = new JPanel();
		tab_game.setBackground(Color.PINK);
		tab_area.add(tab_game);

		la_tab_game = new JLabel("GAME");
		la_tab_game.setPreferredSize(new Dimension(250, 100));
		la_tab_game.setHorizontalAlignment(SwingConstants.CENTER);
		la_tab_game.setForeground(Color.WHITE);
		la_tab_game.setFont(new Font("Dialog", Font.BOLD, 40));
		la_tab_game.setBounds(new Rectangle(84, 0, 300, 100));
		la_tab_game.setAlignmentX(0.5f);
		tab_game.add(la_tab_game);

		tab_check = new JPanel();
		tab_check.setBackground(Color.GREEN);
		tab_area.add(tab_check);

		la_tab_check = new JLabel("CHECK");
		la_tab_check.setPreferredSize(new Dimension(250, 100));
		la_tab_check.setHorizontalAlignment(SwingConstants.CENTER);
		la_tab_check.setForeground(Color.WHITE);
		la_tab_check.setFont(new Font("Dialog", Font.BOLD, 40));
		la_tab_check.setBounds(new Rectangle(84, 0, 300, 100));
		la_tab_check.setAlignmentX(0.5f);
		tab_check.add(la_tab_check);
		bt_exit = new JButton("EXIT");
		bt_exit.setFont(new Font("Dialog", Font.BOLD, 40));
		tab_area.add(bt_exit);

		// ��ư�� ������ ����
		bt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// userMain.showPage(0);
			}
		});

		tab_home.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(0);
			}

			public void mouseEntered(MouseEvent e) {
				tab_home.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				tab_home.setBackground(Color.ORANGE);
			}
		});
		tab_order.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(1);
			}

			public void mouseEntered(MouseEvent e) {
				tab_order.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				tab_order.setBackground(Color.BLUE);
			}
		});
		tab_chat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(2);
			}

			public void mouseEntered(MouseEvent e) {
				tab_chat.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				tab_chat.setBackground(Color.GRAY);
			}
		});
		tab_game.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showPage(3);
			}

			public void mouseEntered(MouseEvent e) {
				tab_game.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				tab_game.setBackground(Color.PINK);
			}
		});
		tab_check.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				getBillMain().getRecords(); // ���̺� �ҷ�����
				getBillMain().setUserInfo(); // �������� ����
				getBillMain().tableDesign(); // ���̺� ������ ����
				showPage(4);
			}

			public void mouseEntered(MouseEvent e) {
				tab_check.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				tab_check.setBackground(Color.GREEN);
			}
		});

		pages[0].setVisible(true);// orderMainâ
		pages[1].setVisible(false);// chatMainâ
		pages[2].setVisible(false);// gameMainâ
		pages[3].setVisible(false);// BillMainâ
		pages[4].setVisible(false);// BillMainâ

		setLocationRelativeTo(null);
		setVisible(true);// â����
	}

	public void showPage(int page) {
		for (int i = 0; i < pages.length; i++) {
			if (i == page) {
				pages[i].setVisible(true);
				if (i == 0) {
					tab_area.setVisible(false);
				} else {
					tab_area.setVisible(true);
				}
			} else {
				pages[i].setVisible(false);
			}

		}
	}

	// �α����� ����� ���̺��ȣ, ���̵� �����ֱ�
	public void setUserInfo() {
		la_table.setText("Table " + Integer.toString(user.getTableNum()) + "    ");
		String name = "";

		if (user.getFlag()) {
			name = user.getMemeber().getId() + " ��";
			la_user.setText(name);

		} else {
			name = "Guest " + user.getGuest().getGuestId();
			la_user.setText(name);

		}
	}

	// billMain ��ȯ�ϴ� �޼���
	public BillMain getBillMain() {
		Object page = pages[4];
		BillMain billMain = (BillMain) page;
		return billMain;
	}

	// chatMain ��ȯ �޼���
	public ChatMain getChatMain() {
		Object page = pages[0];
		ChatMain chatMain = (ChatMain) page;
		return chatMain;
	}
}
