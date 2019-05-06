package com.hunter.user.chat.hapseok;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CardPanelFive extends JPanel{
	AskJoin cardMain;
	JButton bt_host;
	JButton bt_guest;
	public CardPanelFive(AskJoin cardMain) {
		this.cardMain = cardMain;
		this.cardMain.setTitle("���� ��ġ�Ǳ��~^^");
		
		bt_host = new JButton("00�� ���̺�(Host)�� ��ĥ�Կ�~");
		bt_guest = new JButton("00�� ���̺�(Guest)�� ��ĥ�Կ�~");
		
		//ũ��,����
		Color c1 = new Color(238,238,238);
		bt_host.setPreferredSize(new Dimension(180,100));
		bt_host.setBackground(c1);
		bt_guest.setPreferredSize(new Dimension(180,100));
		bt_host.setBackground(c1);
		
		//����
		add(bt_host);
		add(bt_guest);
		
		//�̺�Ʈ ������
		bt_host.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paymentInfo();
			}
		});
		bt_guest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paymentInfo();
			}
		});
		
		setBackground(new Color(255, 153, 154));
		setPreferredSize(new Dimension(390, 290));
		setVisible(true);
	}
	
	public void paymentInfo() {
		this.cardMain.getCardLayout().show(this.cardMain.getContentPane(), "Six");
	}
}
