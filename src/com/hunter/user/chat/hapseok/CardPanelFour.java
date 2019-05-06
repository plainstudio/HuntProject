package com.hunter.user.chat.hapseok;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CardPanelFour extends JPanel{
	AskJoin cardMain;
	JPanel p_center;
	JPanel p_south;
	JLabel la_text;
	JButton bt_confirm;
	
	public CardPanelFour(AskJoin cardMain) {
		this.cardMain = cardMain;
		this.cardMain.setTitle("�Ф�");
		setLayout(new BorderLayout());
		p_center = new JPanel();
		la_text = new JLabel("������ �ռ��� �����߽��ϴ�.");
		p_south = new JPanel();
		bt_confirm = new JButton("Ȯ�� �� ���̺���");
		
		Color c1 = new Color(238,238,238);
		p_center.setPreferredSize(new Dimension(390, 200));
		la_text.setPreferredSize(new Dimension(390, 200));
		la_text.setBackground(c1);
		la_text.setOpaque(true);
		p_south.setPreferredSize(new Dimension(390, 90));
		bt_confirm.setPreferredSize(new Dimension(180,90));
		bt_confirm.setBackground(c1);
		
		//����
		p_center.add(la_text);
		p_south.add(bt_confirm);
		add(p_center);
		add(p_south,BorderLayout.SOUTH);
		
		//�̺�Ʈ ������
		bt_confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardPanelFour.this.cardMain.dispose();
			}
		});
		
		setBackground(new Color(255, 153, 154));
		setPreferredSize(new Dimension(390, 290));
		setVisible(true);
	}
}
