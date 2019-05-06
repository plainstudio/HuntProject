package com.hunter.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.hunter.user.chat.ChatMain;
import com.hunter.user.game.GameMain;
import com.hunter.user.home.BillMain;
import com.hunter.user.home.HomeMain;
import com.hunter.user.order.OrderMain;

public class ShowAll extends JPanel {
	UserMain userMain;
	User user;

	OrderMain orderMain;
	ChatMain chatMain;
	GameMain gameMain;
	BillMain billMain;
	HomeMain homeMain;

	// ȭ����ȯ
	String[] mainTitle = { "orderMain", "chatMain", "gameMain", "billMain" };
	JPanel[] pages = new JPanel[mainTitle.length];
	// ������
	JPanel container; // ��ü�� ���δ� ����
	JLabel la_table, la_user;// ��� ���� ����_���� ���� ����
	JPanel p_order, p_chat, p_game, p_check;
	JLabel la_order, la_chat, la_game, la_check;
	JButton bt_exit;

	public ShowAll(UserMain userMain, User user, HomeMain homeMain) {
		this.userMain = userMain;
		this.user = user;
		this.homeMain = homeMain;
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(1400, 900));

		container = new JPanel();

		p_order = new JPanel();
		p_order.setBounds(84, 110, 300, 700);
		p_chat = new JPanel();
		p_chat.setBounds(389, 110, 300, 700);
		p_game = new JPanel();
		p_game.setBounds(694, 110, 300, 700);
		p_check = new JPanel();
		p_check.setBounds(999, 110, 300, 700);
		la_order = new JLabel("Order");
		la_chat = new JLabel("Chat");
		la_game = new JLabel("Game");
		la_check = new JLabel("Check");

		// ������ �����ϱ�
		Dimension d = new Dimension(300, 800);

		p_order.setPreferredSize(d);
		p_chat.setPreferredSize(new Dimension(300, 800));
		p_game.setPreferredSize(d);
		p_check.setPreferredSize(d);
		container.setBackground(Color.BLACK);
		p_order.setBackground(Color.BLUE);
		p_chat.setBackground(Color.GRAY);
		p_game.setBackground(Color.PINK);
		p_check.setBackground(Color.GREEN);

		// ��Ʈ �����ϱ�
		Dimension df = new Dimension(300, 800);

		la_order.setPreferredSize(new Dimension(300, 700));
		la_chat.setPreferredSize(new Dimension(300, 700));
		la_game.setPreferredSize(new Dimension(300, 700));
		la_check.setPreferredSize(new Dimension(300, 700));
		la_order.setHorizontalAlignment(SwingConstants.CENTER);
		la_chat.setHorizontalAlignment(SwingConstants.CENTER);
		la_game.setHorizontalAlignment(SwingConstants.CENTER);
		la_check.setHorizontalAlignment(SwingConstants.CENTER);
		la_order.setFont(new Font("�߰��", Font.BOLD, 50));
		la_chat.setFont(new Font("�߰��", Font.BOLD, 50));
		la_game.setFont(new Font("�߰��", Font.BOLD, 50));
		la_check.setFont(new Font("�߰��", Font.BOLD, 50));
		la_order.setForeground(Color.WHITE);
		la_chat.setForeground(Color.WHITE);
		la_game.setForeground(Color.WHITE);
		la_check.setForeground(Color.WHITE);

		// �����ϱ�
		p_order.add(la_order);
		p_chat.add(la_chat);
		p_game.add(la_game);
		p_check.add(la_check);

		container.setLayout(null);
		container.add(p_order);
		container.add(p_chat);
		container.add(p_game);
		container.add(p_check);
		add(container);

		// ��ư�� ������ ����
		p_order.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				homeMain.showPage(1);
			}

			public void mouseEntered(MouseEvent e) {
				p_order.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_order.setBackground(Color.BLUE);
			}
		});
		p_chat.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				homeMain.showPage(2);
			}

			public void mouseEntered(MouseEvent e) {
				p_chat.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_chat.setBackground(Color.GRAY);
			}
		});
		p_game.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				homeMain.showPage(3);
			}

			public void mouseEntered(MouseEvent e) {
				p_game.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_game.setBackground(Color.PINK);
			}
		});
		p_check.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				homeMain.getBillMain().getRecords(); // ���̺� �ҷ�����
				homeMain.getBillMain().setUserInfo(); // �������� ����
				homeMain.getBillMain().tableDesign(); // ���̺� ������ ����
				homeMain.showPage(4);
			}

			public void mouseEntered(MouseEvent e) {
				p_check.setBackground(Color.DARK_GRAY);
			}

			public void mouseExited(MouseEvent e) {
				p_check.setBackground(Color.GREEN);
			}
		});

		setVisible(true);
	}
}
