package com.hunter.user.order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.hunter.user.User;
import com.hunter.user.UserMain;

public class OrderMain extends JPanel{
	UserMain main;
	User user;
	JPanel adver; // ������ �г�
	JPanel container; // ���ַ�/�ַ�/���̵� �г� ���� �����̳�
	Bag bag; // ��ٱ��� / �ֹ����� ���� �г�
	int table_no;
	public OrderMain(UserMain main,User user) {
		this.main=main;
		this.user=user;
		//System.out.println("���� ���̺� ��ȣ�� "+user.getTableNum());
		table_no = user.getTableNum();
		setLayout(new BorderLayout());
		adver = new Animation();
		bag = new Bag(main,this);
		container = new OrderList(main,bag);

		add(adver,BorderLayout.WEST);
		add(container);
		add(bag,BorderLayout.EAST);
		this.setBackground(Color.PINK);
		this.setPreferredSize(new Dimension(1400,900));
	}
}