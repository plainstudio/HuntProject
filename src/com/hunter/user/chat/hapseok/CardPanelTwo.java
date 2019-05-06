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

public class CardPanelTwo extends JPanel{
	AskJoin cardMain;
	JPanel p_center;
	JPanel p_south;
	JLabel la_text;
	JButton bt_yes;
	JButton bt_no;
	public CardPanelTwo(AskJoin cardMain) {
		this.cardMain = cardMain;
		this.cardMain.setTitle("�ռ���û ��������");
		setLayout(new BorderLayout());
		p_center = new JPanel();
		la_text = new JLabel("������ ������ ��ٸ��� �ֽ��ϴ�.");
		p_south = new JPanel();
		bt_yes = new JButton("Ȯ��");
		bt_no = new JButton("���");
		
		//ũ��,����
		Color c1 = new Color(238,238,238);
		p_center.setPreferredSize(new Dimension(390, 200));
		la_text.setPreferredSize(new Dimension(390, 200));
		la_text.setBackground(c1);
		la_text.setOpaque(true);
		p_south.setPreferredSize(new Dimension(390, 90));
		bt_yes.setPreferredSize(new Dimension(180,90));
		bt_yes.setBackground(c1);
		bt_no.setPreferredSize(new Dimension(180,90));
		bt_no.setBackground(c1);
		
		//����
		p_center.add(la_text);
		p_south.add(bt_yes);
		p_south.add(bt_no);
		add(p_center);
		add(p_south,BorderLayout.SOUTH);
		
		//�̺�Ʈ ������
		bt_yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardPanelTwo.this.cardMain.getCardLayout().show(CardPanelTwo.this.cardMain.getContentPane(), "Three");
			}
		});
		bt_no.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(CardPanelTwo.this, "����ϼ̽��ϴ�.");
				CardPanelTwo.this.cardMain.dispose();
			}
		});
		
		setBackground(new Color(255, 153, 154));
		setPreferredSize(new Dimension(390, 290));
		setVisible(true);
	}
}
